package eu.telecomnancy.semantic;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.symbols.Label;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import eu.telecomnancy.symbols.UndeclaredLabel;
import eu.telecomnancy.symbols.Variable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SemanticAnalysisVisitor implements ASTVisitor<Type> {

    private List<SemanticException> exceptions;
    private SymbolTable currentSymbolTable;
    private Set<UndeclaredLabel> undeclaredLabels;
    private Set<Label> declaredLabels;
    @Deprecated private Map<Type, Set<Type>> typeCompat;

    public SemanticAnalysisVisitor(SymbolTable symbolTable) {
        if (symbolTable == null) {
            throw new IllegalArgumentException("Symbol table cannot be null");
        }
        this.currentSymbolTable = symbolTable;
        this.exceptions = new ArrayList<>();
        this.undeclaredLabels = new LinkedHashSet<>();
        this.declaredLabels = new LinkedHashSet<>();
        this.typeCompat = new HashMap<>();
        typeCompat.put(Type.REAL, new HashSet<>(Arrays.asList(Type.INTEGER, Type.REAL)));
        typeCompat.put(Type.INTEGER, new HashSet<>(Arrays.asList(Type.INTEGER, Type.REAL)));
        typeCompat.put(Type.VOID, new HashSet<>(Collections.emptyList()));
        typeCompat.put(Type.STRING, new HashSet<>(Collections.singletonList(Type.STRING)));
    }

    public List<SemanticException> getExceptions() {
        return exceptions;
    }

    public boolean hasExceptions() {
        return !exceptions.isEmpty();
    }

    private void checkLabelDeclarations() {
        Set<String> declaredLabelIdentifiers =
                declaredLabels.stream().map(Symbol::getIdentifier).collect(Collectors.toSet());

        // Checking that all the undeclaredLabels have been declared later
        for (UndeclaredLabel undeclaredLabel : undeclaredLabels) {
            String identifier = undeclaredLabel.getIdentifier();
            if (!declaredLabelIdentifiers.contains(identifier)) {
                exceptions.add(
                        new SymbolNotDeclaredException(
                                String.format("Label '%s' is used but not declared.", identifier),
                                undeclaredLabel.getTree()));
            }
        }
    }

    private void pushTable() {
        currentSymbolTable = currentSymbolTable.createChild();
    }

    private void popTable() {
        currentSymbolTable = currentSymbolTable.getParent();
    }

    @Override
    public Type visit(DefaultAST ast) {
        for (DefaultAST t : ast) {
            t.accept(this);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(RootAST ast) {
        ast.getChild(0).accept(this);
        // Final operations
        checkLabelDeclarations();
        exceptions.sort(Comparator.comparingInt(SemanticException::getLine));
        return Type.VOID;
    }

    @Override
    public Type visit(BlockAST ast) {
        pushTable();
        for (DefaultAST t : ast) {
            try {
                t.accept(this);
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }
        popTable();
        return Type.VOID;
    }

    @Override
    public Type visit(VarDecAST ast) {
        Type type = Type.fromString(ast.getChild(0).getText());
        DefaultAST idList = ast.getChild(1);

        for (DefaultAST t : idList) {
            String name = t.getText();
            if (currentSymbolTable.isDeclaredInScope(name)) {
                throw new SymbolRedeclarationException(
                        String.format("Variable '%s' is already declared in scope", name), t);
            }
            Variable variable = new Variable(name, type);
            currentSymbolTable.define(variable);
        }

        return Type.VOID;
    }

    @Override
    public Type visit(ProcDecAST ast) {
        SymbolTable baseSymbolTable = currentSymbolTable;

        DefaultAST procHeading = ast.findFirst(Algol60Parser.PROC_HEADING);
        DefaultAST block = ast.findFirst(Algol60Parser.BLOCK);
        DefaultAST typeAST = ast.findFirst(Algol60Parser.TYPE);

        Type procType = typeAST != null ? Type.fromString(typeAST.getText()) : Type.VOID;
        String procName = procHeading.getChild(0).getText();

        Symbol conflicting = currentSymbolTable.resolveInScope(procName);
        if (conflicting != null) {
            String msg =
                    String.format(
                            "Identifier '%s' is already used by %s in this scope",
                            procName, conflicting.getKind().withPronoun());
            exceptions.add(new SymbolRedeclarationException(msg, procHeading));
        }

        pushTable(); // Enter symbol table of procedure

        DefaultAST paramPart = procHeading.findFirst(Algol60Parser.PARAM_PART);
        DefaultAST valuePart = procHeading.findFirst(Algol60Parser.VALUE_PART);
        DefaultAST specPart = procHeading.findFirst(Algol60Parser.SPEC_PART);

        // Collect procedure parameters in order
        List<String> paramNames = new ArrayList<>();
        List<DefaultAST> paramTrees = new ArrayList<>();
        if (paramPart != null) {
            for (DefaultAST idf : paramPart.getChild(0)) {
                paramNames.add(idf.getText());
                paramTrees.add(idf);
            }
        }

        // Collect types and corresponding names and trees
        List<Type> specTypes = new ArrayList<>();
        List<String> specNames = new ArrayList<>();
        List<DefaultAST> specTrees = new ArrayList<>();
        if (specPart != null) {
            for (DefaultAST argType : specPart) {
                Type parameterType = Type.fromString(argType.getChild(0).getText());
                for (DefaultAST idf : argType.getChild(1)) {
                    String parameterName = idf.getText();
                    specNames.add(parameterName);
                    specTypes.add(parameterType);
                    specTrees.add(idf);
                }
            }
        }

        // Check value part
        List<String> valueNames = new ArrayList<>();
        if (valuePart != null) {
            for (DefaultAST idf : valuePart.getChild(0)) {
                String name = idf.getText();
                if (!paramNames.contains(name)) {
                    String msg =
                            String.format(
                                    "Value '%s' is not a parameter of procedure '%s'",
                                    name, procName);
                    exceptions.add(new ParameterMismatchException(msg, idf));
                } else {
                    valueNames.add(idf.getText());
                }
            }
        }

        // Check specification part and define parameters
        List<Type> orderedTypes = new ArrayList<>();
        for (String name : paramNames) {
            int index = specNames.indexOf(name);
            Type type;
            if (index == -1) {
                String msg =
                        String.format(
                                "Parameter '%s' has no type specified in "
                                        + "specification part of procedure '%s'",
                                name, procName);
                exceptions.add(
                        new ParameterMismatchException(
                                msg, paramTrees.get(paramNames.indexOf(name))));
                type = Type.UNDEFINED;
            } else {
                type = specTypes.get(index);
                // Clear parameter from spec lists
                specNames.remove(index);
                specTypes.remove(index);
                specTrees.remove(index);
            }
            Parameter parameter = new Parameter(name, type);
            if (!valueNames.contains(name)) parameter.setByValue(false);
            orderedTypes.add(type);
            currentSymbolTable.define(parameter);
        }

        // Check for orphan declarations in specification part
        for (String name : specNames) {
            String msg = String.format("'%s' is not a parameter of procedure '%s'", name, procName);
            DefaultAST tree = specTrees.get(specNames.indexOf(name));
            exceptions.add(new ParameterMismatchException(msg, tree));
        }

        // Define procedure
        Procedure procedure = new Procedure(procName, procType, orderedTypes);
        procedure.setSymbolTable(currentSymbolTable);
        baseSymbolTable.define(procedure);

        // Define fictive result variable
        if (procType != Type.VOID) {
            Variable returnValue = new Variable(procName, procType);
            returnValue.setResultValue(true);
            currentSymbolTable.define(returnValue);
        }

        // Semantic controls on the procedure's block statements
        for (DefaultAST t : block) {
            try {
                t.accept(this);
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }

        if (procType != Type.VOID) {
            if (!block.containsReturnStatement(procName)) {
                String msg =
                        String.format(
                                "Procedure '%s' may never return %s",
                                procName, procType.withPronoun());
                exceptions.add(new MissingReturnException(msg, procHeading));
            }
        }

        popTable(); // Quit symbol table of procedure

        return procType;
    }

    @Override
    public Type visit(ProcCallAST ast) {
        String procCallName = ast.getChild(0).getText();
        Symbol s = currentSymbolTable.resolve(procCallName);
        if (s == null) {
            throw new SymbolNotDeclaredException(
                    String.format("Procedure '%s' is not declared", procCallName), ast);
        }

        if (s.getKind() != Kind.PROCEDURE) {
            if (s.getKind() == Kind.VARIABLE && ((Variable) s).isResultValue()) {
                // Allows recursive calls of procedure
                s = currentSymbolTable.getParent().resolve(procCallName);
            } else {
                throw new SymbolNotDeclaredException(
                        String.format(
                                "'%s' is %s, not a procedure",
                                procCallName, s.getKind().withPronoun()),
                        ast);
            }
        }

        List<Type> types = new ArrayList<>();
        for (DefaultAST defaultAST : ast.getChild(1)) {
            Type type = defaultAST.accept(this);
            types.add(type);
        }

        Procedure p = (Procedure) s;
        if (p.getParameterTypes().size() != types.size()) {
            throw new ParameterMismatchException(
                    String.format(
                            "Procedure '%s' expects %d parameters but received %d",
                            procCallName, p.getParameterTypes().size(), types.size()),
                    ast);
        }

        for (int i = 0; i < p.getParameterTypes().size(); i++) {
            Type paramType = p.getParameterTypes().get(i);
            Type actualType = types.get(i);
            if (Types.cannotAssign(paramType, actualType)) {
                DefaultAST paramAST = ast.getChild(1).getChild(i);
                throw new TypeMismatchException(
                        String.format(
                                "Parameter #%d of procedure '%s' expects %s but it received %s",
                                i + 1,
                                procCallName,
                                paramType.withPronoun(),
                                actualType.withPronoun()),
                        paramAST);
            }
        }

        return s.getType();
    }

    @Override
    public Type visit(IfStatementAST ast) {
        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST operator = ifDef.getChild(0);
        DefaultAST firstOperand = operator.getChild(0);
        DefaultAST secondOperand = operator.getChild(1);
        Type leftType = firstOperand.accept(this);
        Type rightType = secondOperand.accept(this);
        String leftName = getStringRepresentation(firstOperand);
        String rightName = getStringRepresentation(secondOperand);

        if (operator.getChildCount() == 2) {
            if (rightType == leftType) {
                if (rightType == Type.STRING) {
                    throw new TypeMismatchException(
                            String.format(
                                    "cannot compare strings '%s' and '%s'", leftName, rightName),
                            firstOperand);
                }
            } else {
                throw new TypeMismatchException(
                        String.format(
                                "'%s' and '%s' have different types ('%s' and '%s')",
                                leftName, rightName, leftType, rightType),
                        firstOperand);
            }
        }
        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        thenDef.accept(this);
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);

        if (elseDef != null) {
            elseDef.accept(this);
        }

        return Type.VOID;
    }

    private String getStringRepresentation(DefaultAST ast) {
        if (ast.getType() == Algol60Parser.PROC_CALL) {
            return ast.getChild(0).getText();
        }
        return ast.getText();
    }

    @Override
    public Type visit(ForClauseAST ast) {
        DefaultAST assignment = ast.getChild(0).getChild(0);
        DefaultAST init = assignment.getChild(0);
        if (currentSymbolTable.resolve(init.getText()) == null) {
            throw new SymbolNotDeclaredException(
                    String.format("Variable %s not declared.", init.getText()), init);
        } else {
            Type assignmentValue = init.accept(this);
            if (assignmentValue != Type.INTEGER) {
                throw new TypeMismatchException(
                        String.format("Cannot iterate on %s type.", assignmentValue), init);
            }
        }

        DefaultAST step = ast.getChild(1).getChild(0);
        Type stepValue = step.accept(this);
        if (stepValue != Type.INTEGER) {
            throw new TypeMismatchException(
                    String.format("Expected int but got %s instead.", stepValue), step);
        }
        DefaultAST until = ast.getChild(2).getChild(0);
        Type untilValue = until.accept(this);
        if (untilValue != Type.INTEGER) {
            throw new TypeMismatchException(
                    String.format("Expected int but got %s instead.", untilValue), until);
        }
        DefaultAST action = ast.getChild(3).getChild(0);
        if (action.getType() != Algol60Parser.BLOCK) {
            throw new TypeMismatchException(
                    String.format("Expected BLOCK but got %s instead.", action.getText()), action);
        } else {
            action.accept(this);
        }

        return Type.VOID;
    }

    @Override
    public Type visit(WhileClauseAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(AssignmentAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        String leftName = leftPart.getText();
        Type leftType = leftPart.accept(this);

        DefaultAST rightPart = ast.getChild(1);
        Type rightType = rightPart.accept(this);

        if (Types.cannotAssign(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot assign %s to '%s' of type %s",
                            rightType.withPronoun(), leftName, leftType),
                    rightPart);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(ArrayDecAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(ArrayAssignmentAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(MultAST ast) {
        PairOfTypes types = checkArithmeticOperation(ast);
        if (types.left == Type.REAL || types.right == Type.REAL) {
            return Type.REAL;
        } else {
            return Type.INTEGER;
        }
    }

    @Override
    public Type visit(DivAST ast) {
        checkArithmeticOperation(ast);
        return Type.REAL;
    }

    @Override
    public Type visit(ArrayCallAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(IntAST ast) {
        return Type.INTEGER;
    }

    @Override
    public Type visit(Pow10AST ast) {
        return Type.REAL;
    }

    @Override
    public Type visit(PowAST ast) {
        PairOfTypes types = checkArithmeticOperation(ast);
        if (types.left == Type.REAL || types.right == Type.REAL) {
            return Type.REAL;
        } else {
            return Type.INTEGER;
        }
    }

    private PairOfTypes checkArithmeticOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String leftName = getStringRepresentation(leftPart);
        String rightName = getStringRepresentation(rightPart);
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);

        if (Types.cannotDoArithmeticOperation(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform arithmetic operation on "
                                    + "operands '%s' and '%s' of types %s and %s",
                            leftName, rightName, leftType, rightType),
                    ast);
        }
        return new PairOfTypes(leftType, rightType);
    }

    private static class PairOfTypes {
        private final Type left;
        private final Type right;

        private PairOfTypes(Type left, Type right) {
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public Type visit(RealAST ast) {
        return Type.REAL;
    }

    @Override
    public Type visit(StrAST ast) {
        return Type.STRING;
    }

    @Override
    public Type visit(IntDivAST ast) {
        checkArithmeticOperation(ast);
        return Type.INTEGER;
    }

    @Override
    public Type visit(AddAST ast) {
        PairOfTypes types = checkArithmeticOperation(ast);
        if (types.left == Type.REAL || types.right == Type.REAL) {
            return Type.REAL;
        } else {
            return Type.INTEGER;
        }
    }

    @Override
    public Type visit(MinusAST ast) {
        PairOfTypes types = checkArithmeticOperation(ast);
        if (types.left == Type.REAL || types.right == Type.REAL) {
            return Type.REAL;
        } else {
            return Type.INTEGER;
        }
    }

    @Override
    public Type visit(LabelDecAST ast) {
        String name = ast.getChild(0).getText();

        if (currentSymbolTable.isDeclaredInScope(name)) {
            throw new SymbolRedeclarationException(
                    String.format("Label '%s' already declared in scope", name), ast);
        }

        Label label = new Label(name);
        currentSymbolTable.define(label);
        declaredLabels.add(label);

        return Type.VOID;
    }

    @Override
    public Type visit(GoToAST ast) {
        String name = ast.getChild(0).getText();
        // We don't know yet the scope of this label
        undeclaredLabels.add(new UndeclaredLabel(name, ast));
        return Type.VOID;
    }

    @Override
    public Type visit(IdentifierAST ast) {
        String identifier = ast.getText();
        Symbol symbol = currentSymbolTable.resolve(identifier);
        if (symbol == null) {
            throw new SymbolNotDeclaredException(
                    String.format("Variable '%s' is not declared", identifier), ast);
        }
        return symbol.getType();
    }

    public Type visit(LogicalValueAST ast) {
        return Type.BOOLEAN;
    }

    public Type visit(AndAST ast) {
        return checkBooleanTest(ast);
    }

    public Type visit(OrAST ast) {
        return checkBooleanTest(ast);
    }

    public Type visit(ImplyAST ast) {
        return checkBooleanTest(ast);
    }

    public Type visit(EquivalentAST ast) {
        return checkBooleanTest(ast);
    }

    public Type visit(GreaterThanAST ast) {
        return checkArithmeticTest(ast);
    }

    public Type visit(LessThanAST ast) {
        return checkArithmeticTest(ast);
    }

    public Type visit(GreaterEqualAST ast) {
        return checkArithmeticTest(ast);
    }

    public Type visit(LessEqualAST ast) {
        return checkArithmeticTest(ast);
    }

    public Type visit(EqualAST ast) {
        return checkArithmeticTest(ast);
    }

    public Type visit(NotEqualAST ast) {
        return checkArithmeticTest(ast);
    }

    private Type checkBooleanTest(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String leftName = getStringRepresentation(leftPart);
        String rightName = getStringRepresentation(rightPart);
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);
        if (Types.cannotDoBooleanTest(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform boolean operation on "
                                    + "operands '%s' and '%s' of types %s and %s",
                            leftName, rightName, leftType, rightType),
                    ast);
        }
        return Type.BOOLEAN;
    }

    private Type checkArithmeticTest(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        String leftName = getStringRepresentation(leftPart);
        String rightName = getStringRepresentation(rightPart);
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);
        if (Types.cannotDoArithmeticTest(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform arithmetic operation on "
                                    + "operands '%s' and '%s' of types %s and %s",
                            leftName, rightName, leftType, rightType),
                    ast);
        }
        return Type.BOOLEAN;
    }
}
