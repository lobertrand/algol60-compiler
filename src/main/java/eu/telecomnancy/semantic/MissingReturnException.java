package eu.telecomnancy.semantic;

public class MissingReturnException extends SemanticException {

    public MissingReturnException(String message, int line) {
        super(message, line);
    }

    @Override
    public String toString() {
        return String.format("Missing return at line %d: %s", line, getMessage());
    }
}
