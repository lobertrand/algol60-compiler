package eu.telecomnancy.semantic;

public abstract class SemanticException extends RuntimeException {

    protected int line = -1;

    public SemanticException(String message, int line) {
        super(message);
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    @Override
    public abstract String toString();
}
