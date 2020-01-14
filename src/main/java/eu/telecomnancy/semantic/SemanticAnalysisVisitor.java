package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.*;
import eu.telecomnancy.tools.ASTTools;
import eu.telecomnancy.symbols.Label;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import eu.telecomnancy.symbols.UndeclaredLabel;
import eu.telecomnancy.symbols.Variable;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalysisVisitor implements ASTVisitor<Type> {

    private List<SemanticException> exceptions;
    private SymbolTable currentSymbolTable;
    private List<UndeclaredLabel> undeclaredLabels;

    public SemanticAnalysisVisitor(SymbolTable symbolTable) {
        if (symbolTable == null) {
            throw new IllegalArgumentException("Symbol table cannot be null");
        }
        this.currentSymbolTable = symbolTable;
        this.exceptions = new ArrayList<>();
        this.undeclaredLabels = new ArrayList<>();
    }

    public List<SemanticException> getExceptions() {
        return exceptions;
    }

    public List<UndeclaredLabel> getUndeclaredLabels() {
        return undeclaredLabels;
    }

    private void removeUndeclaredLabelsWithName(String name) {
        for (int i = undeclaredLabels.size() - 1; i >= 0; i--) {
            if (undeclaredLabels.get(i).getIdentifier().equals(name)) {
                undeclaredLabels.remove(i);
            }
        }
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
        // Ending operations
        for (UndeclaredLabel l : undeclaredLabels) {
            SymbolNotDeclaredException e =
                    new SymbolNotDeclaredException(
                            String.format(
                                    "Label '%s' is used but never declared in scope",
                                    l.getIdentifier()),
                            l.getTree());
            exceptions.add(e);
        }
        return Type.VOID;
    }

    @Override
    public Type visit(BlockAST ast) {
        currentSymbolTable = currentSymbolTable.createChild();
        for (DefaultAST t : ast) {
            try {
                t.accept(this);
            } catch (SemanticException e) {
                exceptions.add(e);
            }
        }
        currentSymbolTable = currentSymbolTable.getParent();
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
        int n = ast.getChildCount();
        String procname = null;
        Procedure proc;
        List<Type> args = new ArrayList<Type>();
        DefaultAST dst = null;
        Type type = Type.VOID;
        if (n == 2) {
            dst = ast.getChildAST(0);
        } else if (n == 3) {
            dst = ast.getChildAST(1);
            type = Type.fromString(ast.getChild(0).getText());
        }
        int nbre = dst.getChild(2).getChildCount();
        List<Symbol> variables = new ArrayList<>();
        for (int i = 0; i < nbre; i++) {
            for (int j = 0; j < dst.getChild(2).getChild(i).getChild(1).getChildCount(); j++) {
                args.add(Type.fromString(dst.getChild(2).getChild(i).getChild(0).getText()));
                variables.add(
                        new Variable(
                                dst.getChild(2).getChild(i).getChild(1).getChild(j).toString(),
                                Type.fromString(
                                        dst.getChild(2).getChild(i).getChild(0).getText())));
            }
        }
        proc = new Procedure(procname, type, args);
        currentSymbolTable.define(proc);

        currentSymbolTable = currentSymbolTable.createChild();
        for (int u = 0; u < variables.size(); u++) {
            currentSymbolTable.define(variables.get(u));
        }

        // currentSymbolTable = currentSymbolTable.getParent();
        ASTTools.depthFirstSearch(ast);

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
        return Type.VOID;
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
        return Type.VOID;
    }

    @Override
    public Type visit(StrAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(IntDivAST ast) {
        return Type.VOID;
    }

    @Override
    public Type visit(AddAST ast) {
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
        removeUndeclaredLabelsWithName(name);

        return Type.VOID;
    }

    @Override
    public Type visit(GoToAST ast) {
        String name = ast.getChild(0).getText();
        if (currentSymbolTable.resolve(name) == null) {
            UndeclaredLabel uLabel = new UndeclaredLabel(name, ast);
            // We don't know yet the scope of this label
            undeclaredLabels.add(uLabel);
        }
        return Type.VOID;
    }
}
