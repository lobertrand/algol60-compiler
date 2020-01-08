package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.*;
import eu.telecomnancy.symbols.Label;
import eu.telecomnancy.symbols.Symbol;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import eu.telecomnancy.symbols.UndeclaredLabel;
import java.util.ArrayList;
import java.util.List;

public class SemanticAnalysisVisitor implements ASTVisitor<Type> {

    private List<SemanticException> exceptions;
    private SymbolTable currentSymbolTable;

    public SemanticAnalysisVisitor(SymbolTable symbolTable) {
        if (symbolTable == null) {
            throw new IllegalArgumentException("Symbol table cannot be null");
        }
        this.currentSymbolTable = symbolTable;
        this.exceptions = new ArrayList<>();
    }

    public List<SemanticException> getExceptions() {
        return exceptions;
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

        return Type.VOID;
    }

    @Override
    public Type visit(ProcDecAST ast) {
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
                    String.format("Label %s already declared", name), ast.getLine());
        }
        Label label = new Label(name);
        currentSymbolTable.define(label);

        return Type.VOID;
    }

    @Override
    public Type visit(GoToAST ast) {
        if (currentSymbolTable.resolve(ast.getText()) == null) {
            Symbol symbol = new UndeclaredLabel(ast.getChildAST(0).getText());
            currentSymbolTable.define(symbol);
        }
        return Type.VOID;
    }
}
