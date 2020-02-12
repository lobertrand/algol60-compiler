package eu.telecomnancy.semantic;

import static java.lang.Integer.*;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.symbols.Label;
import eu.telecomnancy.symbols.OrphanGoto;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import eu.telecomnancy.symbols.Variable;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalysisVisitor implements ASTVisitor<Type> {

    private List<SemanticException> exceptions;
    private SymbolTable currentSymbolTable;
    private List<OrphanGoto> orphanGotos;

    public SemanticAnalysisVisitor(SymbolTable symbolTable) {
        if (symbolTable == null) {
            throw new IllegalArgumentException("Symbol table cannot be null");
        }
        this.currentSymbolTable = symbolTable;
        this.exceptions = new ArrayList<>();
        this.orphanGotos = new ArrayList<>();
    }

    public List<SemanticException> getExceptions() {
        return exceptions;
    }

    public boolean hasExceptions() {
        return !exceptions.isEmpty();
    }

    private void pushTable() {
        currentSymbolTable = currentSymbolTable.createChild();
    }

    private void popTable() {
        if (currentSymbolTable.getParent() == null) {
            throw new IllegalStateException("Did more popTable() than pushTable()");
        }
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
        checkDeclarationOfEveryLabelUsedWithGoto();
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
                exceptions.add(
                        new SymbolRedeclarationException(
                                String.format("Variable '%s' is already declared in scope", name),
                                t));
            } else {
                Variable variable = new Variable(name, type);
                currentSymbolTable.define(variable);
            }
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
        for (DefaultAST args : ast.getChild(1)) {
            Type type = args.accept(this);
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
                exceptions.add(
                        new TypeMismatchException(
                                String.format(
                                        "Parameter #%d of procedure '%s' expects %s but it received %s",
                                        i + 1,
                                        procCallName,
                                        paramType.withPronoun(),
                                        actualType.withPronoun()),
                                paramAST));
            }
        }

        return s.getType();
    }

    @Override
    public Type visit(IfExpressionAST ast) {
        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        Type conditionType = condition.accept(this);
        if (conditionType != Type.BOOLEAN) {
            exceptions.add(
                    new TypeMismatchException(
                            "If condition expects a boolean expression but got "
                                    + conditionType.withPronoun(),
                            ifDef));
        }

        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        Type thenType = Type.UNDEFINED;
        try {
            thenType = thenDef.getChild(0).accept(this);
            if (thenType == Type.VOID) {
                throw new TypeMismatchException(
                        "Members of an if-expression must return a value", thenDef);
            }
        } catch (SemanticException e) {
            exceptions.add(e);
        }

        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);
        Type elseType = Type.UNDEFINED;
        try {
            elseType = elseDef.getChild(0).accept(this);
            if (elseType == Type.VOID) {
                throw new TypeMismatchException(
                        "Expression types of an if-expression must return a value", elseDef);
            }
        } catch (SemanticException e) {
            exceptions.add(e);
        }

        if (Types.cannotFindCommonType(thenType, elseType)) {
            exceptions.add(
                    new TypeMismatchException(
                            String.format(
                                    "Cannot infer type of if-expression " + "(then: %s / else: %s)",
                                    thenType, elseType),
                            thenDef));
            return Type.UNDEFINED;
        } else {
            return Types.getMostSpecificCommonType(thenType, elseType);
        }
    }

    @Override
    public Type visit(IfStatementAST ast) {
        DefaultAST ifDef = ast.findFirst(Algol60Parser.IF_DEF);
        DefaultAST condition = ifDef.getChild(0);
        Type conditionType = condition.accept(this);
        if (conditionType != Type.BOOLEAN) {
            exceptions.add(
                    new TypeMismatchException(
                            "If condition expects a boolean expression but got "
                                    + conditionType.withPronoun(),
                            condition));
        }

        DefaultAST thenDef = ast.findFirst(Algol60Parser.THEN_DEF);
        try {
            thenDef.accept(this);
        } catch (SemanticException e) {
            exceptions.add(e);
        }
        DefaultAST elseDef = ast.findFirst(Algol60Parser.ELSE_DEF);

        if (elseDef != null) {
            try {
                elseDef.accept(this);
            } catch (SemanticException e) {
                exceptions.add(e);
            }
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
        DefaultAST init = ast.findFirst(Algol60Parser.INIT);
        init.accept(this);

        DefaultAST whileClause = ast.findFirst(Algol60Parser.WHILE);
        if (whileClause != null) {
            try {
                Type conditionType = whileClause.getChild(0).accept(this);
                if (conditionType != Type.BOOLEAN) {
                    exceptions.add(
                            new TypeMismatchException(
                                    "While condition expected a boolean but received "
                                            + conditionType.withPronoun(),
                                    whileClause));
                }
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }

        DefaultAST step = ast.findFirst(Algol60Parser.STEP);
        if (step != null) {
            try {
                Type stepValue = step.getChild(0).accept(this);
                if (stepValue != Type.INTEGER && stepValue != Type.REAL) {
                    exceptions.add(
                            new TypeMismatchException(
                                    String.format(
                                            "Expected integer or real but got %s instead",
                                            stepValue.withPronoun()),
                                    step));
                }
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }

        DefaultAST until = ast.findFirst(Algol60Parser.UNTIL);
        if (until != null) {
            try {
                Type untilValue = until.getChild(0).accept(this);
                if (untilValue != Type.INTEGER && untilValue != Type.REAL) {
                    exceptions.add(
                            new TypeMismatchException(
                                    String.format(
                                            "Expected integer or real but got %s instead",
                                            untilValue.withPronoun()),
                                    until));
                }
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }
        DefaultAST action = ast.findFirst(Algol60Parser.DO).getChild(0);

        if (action.getType() == Algol60Parser.LABEL_DEC) {
            exceptions.add(
                    new UnreachableStatementException(
                            "Cannot declare a label inside a one-line for loop", action));
        } else {
            action.accept(this);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(InitAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        String leftName = leftPart.getText();
        Type leftType = Type.UNDEFINED;
        try {
            leftType = leftPart.accept(this);
            if (leftType != Type.INTEGER && leftType != Type.REAL) {
                exceptions.add(
                        new TypeMismatchException(
                                String.format("Cannot iterate on %s type", leftType), leftPart));
            }
        } catch (SemanticException e) {
            exceptions.add(e);
        }

        int nbChildren = ast.getChildCount();
        for (int i = 1; i < nbChildren; i++) {
            DefaultAST rightPart = ast.getChild(i);
            Type rightType = rightPart.accept(this);
            if (Types.cannotAssign(leftType, rightType)) {
                exceptions.add(
                        new TypeMismatchException(
                                String.format(
                                        "Cannot assign %s to '%s' of type %s",
                                        rightType.withPronoun(), leftName, leftType),
                                rightPart));
            }
        }
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
                    ast);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(ArrayDecAST ast) {
        Type type = Type.fromString(ast.getChild(0).getText());
        DefaultAST id = ast.getChild(1);
        DefaultAST boundList = ast.getChild(2);
        List<Array.Range> ranges = new ArrayList<Array.Range>();
        if (currentSymbolTable.isDeclaredInScope(id.toString())) {
            throw new SymbolRedeclarationException(
                    String.format("Variable '%s' is already declared in scope", id.toString()), id);
        }
        for (DefaultAST bound : boundList) {
            DefaultAST first = bound.getChild(0);
            DefaultAST last = bound.getChild(1);
            Type firstType = first.accept(this);
            Type lastType = last.accept(this);
            Integer firstInt =
                    first.getType() == Algol60Parser.INT ? parseInt(first.getText()) : null;
            Integer lastInt = last.getType() == Algol60Parser.INT ? parseInt(last.getText()) : null;

            if (firstInt != null && lastInt != null && firstInt > lastInt) {
                throw new IncompatibleBoundException(
                        String.format(
                                "Bound start must be inferior or equal to bound end but %s > %s",
                                firstInt, lastInt),
                        bound);
            }
            if (Types.cannotAssign(Type.INTEGER, firstType)) {
                exceptions.add(
                        new IncompatibleBoundException(
                                String.format(
                                        "Bounds must be integers but bound start is %s",
                                        firstType.withPronoun()),
                                first));
            }
            if (Types.cannotAssign(Type.INTEGER, lastType)) {
                exceptions.add(
                        new IncompatibleBoundException(
                                String.format(
                                        "Bounds must be integers but bound end is %s",
                                        lastType.withPronoun()),
                                last));
            }

            ranges.add(new Array.Range(firstInt, lastInt));
        }
        Array a = new Array(id.toString(), type, ranges);
        currentSymbolTable.define(a);
        return Type.VOID;
    }

    @Override
    public Type visit(ArrayAssignmentAST ast) {
        DefaultAST id = ast.getChild(0);
        DefaultAST indices = ast.getChild(1);
        String name = id.getText();
        Symbol symbol = currentSymbolTable.resolve(name);
        if (symbol == null) {
            throw new SymbolNotDeclaredException(
                    String.format("Variable '%s' is not declared", name), id);
        }
        if (symbol.getKind() != Kind.ARRAY) {
            throw new TypeMismatchException(
                    String.format("Variable '%s' is not an array", name), id);
        }
        Array array = (Array) symbol;
        int nbIndicesDec = array.getRanges().size();
        int nbIndicesAss = indices.getChildCount();
        if (nbIndicesDec != nbIndicesAss) {
            throw new IncompatibleBoundException(
                    String.format(
                            "array '%s' expected %d indices but received %d",
                            name, nbIndicesDec, nbIndicesAss),
                    id);
        }
        int indiceCounter = 0;
        for (DefaultAST indice : indices) {
            Type indiceType = indice.accept(this);
            if (Types.cannotAssign(Type.INTEGER, indiceType)) {
                throw new TypeMismatchException(
                        String.format(
                                "Indices must be integer values but index '%s' is %s",
                                indice, indiceType.withPronoun()),
                        indice);
            }
            if (indice.getType() == Algol60Parser.INT) {
                int intIndice = parseInt(indice.getText());

                Array.Range range = array.getRanges().get(indiceCounter);
                if (!range.isInRange(intIndice)) {
                    throw new OutOfBoundException(
                            String.format(
                                    "Array index '%d' is out of bounds in '%s' with bounds %s",
                                    intIndice, id.getText(), range),
                            indice);
                }
            }
            indiceCounter++;
        }

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
        DefaultAST id = ast.getChild(0);
        String name = id.getText();
        Symbol symbol = currentSymbolTable.resolve(name);
        if (symbol == null) {
            throw new SymbolNotDeclaredException(
                    String.format(
                            "you are calling an element from an array but this array : %s is not declared in scope",
                            name),
                    id);
        }
        if (symbol.getKind() != Kind.ARRAY) {
            throw new TypeMismatchException(
                    String.format("variable '%s' is not an array", name), id);
        }

        DefaultAST paramList = ast.getChild(1);
        Array array = (Array) symbol;
        int nbIndicesDec = array.getRanges().size();
        int nbIndicesCall = paramList.getChildCount();
        if (nbIndicesDec != nbIndicesCall) {
            throw new IncompatibleBoundException(
                    String.format(
                            "the array %s expected %d indices and received %d",
                            name, nbIndicesDec, nbIndicesCall),
                    id);
        }
        int indiceCounter = 0;
        for (DefaultAST param : paramList) {
            Type indiceType = param.accept(this);
            if (indiceType != Type.INTEGER) {
                throw new TypeMismatchException(
                        String.format(
                                "indices must be integer but indice %s is %s",
                                param, indiceType.withPronoun()),
                        param);
            }
            if (param.getType() == Algol60Parser.INT) {
                int intIndice = parseInt(param.getText());

                Array.Range range = array.getRanges().get(indiceCounter);
                if (!range.isInRange(intIndice)) {
                    throw new OutOfBoundException(
                            String.format(
                                    "in array %s the indice %d is out of bound, bound %s",
                                    id.getText(), intIndice, range),
                            param);
                }
            }
            indiceCounter++;
        }
        Type t = symbol.getType();
        return t;
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
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);

        if (Types.cannotDoArithmeticOperation(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform arithmetic operation on operands of types %s and %s",
                            leftType, rightType),
                    rightPart.shiftCursorLeft());
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
        currentSymbolTable.define(new Label(name));
        return Type.VOID;
    }

    @Override
    public Type visit(GoToAST ast) {
        String name = ast.getChild(0).getText();
        orphanGotos.add(new OrphanGoto(name, currentSymbolTable, ast));
        return Type.VOID;
    }

    private void checkDeclarationOfEveryLabelUsedWithGoto() {
        for (OrphanGoto orphanGoto : orphanGotos) {
            SymbolTable table = orphanGoto.getSymbolTable();
            String identifier = orphanGoto.getIdentifier();
            DefaultAST ast = orphanGoto.getTree();
            Symbol symbol = table.resolve(orphanGoto.getIdentifier());
            if (symbol == null) {
                exceptions.add(
                        new SymbolNotDeclaredException(
                                String.format("Label '%s' is not declared", identifier), ast));
            } else if (symbol.getKind() != Kind.LABEL) {
                exceptions.add(
                        new TypeMismatchException(
                                String.format("'%s' is not a label identifier", identifier), ast));
            }
        }
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

    @Override
    public Type visit(NotAST ast) {
        DefaultAST child = ast.getChild(0);
        Type type = child.accept(this);
        if (Types.cannotNegate(type)) {
            String name = getStringRepresentation(child);
            throw new TypeMismatchException(
                    String.format("Cannot perform negation on '%s' of type %s", name, type), ast);
        }
        return Type.BOOLEAN;
    }

    public Type visit(AndAST ast) {
        return checkLogicalOperation(ast);
    }

    public Type visit(OrAST ast) {
        return checkLogicalOperation(ast);
    }

    public Type visit(ImplyAST ast) {
        return checkLogicalOperation(ast);
    }

    public Type visit(EquivalentAST ast) {
        return checkLogicalOperation(ast);
    }

    public Type visit(GreaterThanAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(LessThanAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(GreaterEqualAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(LessEqualAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(EqualAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(NotEqualAST ast) {
        return checkRelationalOperation(ast);
    }

    public Type visit(SwitchDecAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        Type leftType = leftPart.accept(this);
        for (DefaultAST child : rightPart) {
            child.accept(this);
        }
        return Type.VOID;
    }

    public Type visit(SwitchCallAST ast) {
        return Type.VOID;
    }

    private Type checkLogicalOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);
        if (Types.cannotDoLogicalOperation(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform boolean operation on operands of types %s and %s",
                            leftType, rightType),
                    rightPart.shiftCursorLeft());
        }
        return Type.BOOLEAN;
    }

    private Type checkRelationalOperation(DefaultAST ast) {
        DefaultAST leftPart = ast.getChild(0);
        DefaultAST rightPart = ast.getChild(1);
        Type leftType = leftPart.accept(this);
        Type rightType = rightPart.accept(this);
        if (Types.cannotDoRelationalOperation(leftType, rightType)) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot perform arithmetic operation on operands of types %s and %s",
                            leftType, rightType),
                    rightPart.shiftCursorLeft());
        }
        return Type.BOOLEAN;
    }
}
