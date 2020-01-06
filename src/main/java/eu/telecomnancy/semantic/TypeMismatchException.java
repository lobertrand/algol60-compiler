package eu.telecomnancy.semantic;

public class TypeMismatchException extends SemanticException {

    public TypeMismatchException(String message, int line) {
        super(message, line);
    }

    @Override
    public String toString() {
        return String.format("Algol60> Type mismatch at line %d: %s", line, getMessage());
    }
}
