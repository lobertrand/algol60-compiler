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

    public SemanticAnalysisVisitor(SymbolTable symbolTable) {
        if (symbolTable == null) {
            throw new IllegalArgumentException("Symbol table cannot be null");
        }
        this.currentSymbolTable = symbolTable;
        this.exceptions = new ArrayList<>();
        this.undeclaredLabels = new LinkedHashSet<>();
        this.declaredLabels = new LinkedHashSet<>();
    }

    public List<SemanticException> getExceptions() {
        return exceptions;
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
        ast.getChildAST(0).accept(this);
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
        DefaultAST idList = ast.getChildAST(1);

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
            if (index == -1) {
                String msg =
                        String.format(
                                "Parameter '%s' has no type specified in "
                                        + "specification part of procedure '%s'",
                                name, procName);
                exceptions.add(
                        new ParameterMismatchException(
                                msg, paramTrees.get(paramNames.indexOf(name))));
            } else {
                Type type = specTypes.get(index);
                Parameter parameter = new Parameter(name, type);
                if (!valueNames.contains(name)) parameter.setByValue(false);
                orderedTypes.add(type);
                currentSymbolTable.define(parameter);

                // Clear parameter from spec lists
                specNames.remove(index);
                specTypes.remove(index);
                specTrees.remove(index);
            }
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

        popTable(); // Quit symbol table of procedure

        return Type.VOID;
    }

    @Override
    public Type visit(ProcCallAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(IfStatementAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(ForClauseAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(WhileClauseAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(AssignmentAST ast) {
        String leftName = ast.getChild(0).getText();
        Symbol leftSymbol = currentSymbolTable.resolve(leftName);
        if (leftSymbol == null) {
            throw new SymbolNotDeclaredException(
                    String.format("'%s' is not declared", leftName), ast);
        }
        if (!leftSymbol.getKind().isAssignable()) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot assign to '%s' which is %s",
                            leftName, leftSymbol.getKind().withPronoun()),
                    ast);
        }

        Type rightType = null;
        if (ast.getChildAST(1).getType() == Algol60Parser.IDENTIFIER) {
            String rightName = ast.getChild(1).getText();
            Symbol rightSymbol = currentSymbolTable.resolve(rightName);
            if (rightSymbol == null) {
                throw new SymbolNotDeclaredException(
                        String.format("Variable '%s' is not declared.", rightName), ast);
            }
            rightType = rightSymbol.getType();

        } else {
            rightType = ast.getChildAST(1).accept(this);
        }
        if (leftSymbol.getType() != rightType) {
            throw new TypeMismatchException(
                    String.format(
                            "Cannot assign %s to '%s' of type %s",
                            rightType.withPronoun(), leftName, leftSymbol.getType()),
                    ast.getChild(1));
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
        return Type.VOID;
    }

    @Override
    public Type visit(DivAST ast) {
        return Type.VOID;
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
        return Type.VOID;
    }

    @Override
    public Type visit(PowAST ast) {
        return Type.VOID;
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
        return Type.VOID;
    }

    @Override
    public Type visit(AddAST ast) {
        String leftPart = ast.getChildAST(0).getText();
        String rightPart = ast.getChildAST(1).getText();
        Symbol leftSymbol = currentSymbolTable.resolve(leftPart);
        Symbol rightSymbol = currentSymbolTable.resolve(rightPart);
        if (leftSymbol == null) {
            if (ast.getChildAST(0).getType() != Algol60Parser.STR) {
                if (rightSymbol == null) {
                    if (ast.getChildAST(1).getType() != Algol60Parser.STR) {
                        //
                    }
                }
            }
        }

        return Type.VOID;
    }

    @Override
    public Type visit(MinusAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(TermAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(LabelDecAST ast) {
        String name = ast.getChildAST(0).getText();

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
}
