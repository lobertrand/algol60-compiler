package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public abstract class SemanticException extends RuntimeException {

    protected int line, column;
    protected String token;
    protected DefaultAST ast;

    public SemanticException(String message, DefaultAST ast) {
        super(message);
        this.line = ast.getLine();
        this.column = ast.getCharPositionInLine();
        this.token = ast.getText();
        this.ast = ast;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public DefaultAST getAst() {
        return ast;
    }

    @Override
    public abstract String toString();
}
