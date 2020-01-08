package eu.telecomnancy.semantic;

public class SymbolRedeclarationException extends SemanticException {

    public SymbolRedeclarationException(String message, int line) {
        super(message, line);
    }

    @Override
    public String toString() {
        return String.format("Algol60> Symbol already declared at line %d: %s", line, getMessage());
    }
}
