package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public abstract class SemanticException extends RuntimeException {

    protected int line, column;
    protected String token;

    public SemanticException(String message, Tree tree) {
        super(message);
        this.line = tree.getLine();
        this.column = tree.getCharPositionInLine();
        this.token = tree.getText();
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    @Override
    public abstract String toString();
}
