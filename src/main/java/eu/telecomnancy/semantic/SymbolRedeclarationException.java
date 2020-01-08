package eu.telecomnancy.semantic;

public class SymbolRedeclarationException extends SemanticException {

    public SymbolRedeclarationException(String message, int line) {
        super(message, line);
    }

    @Override
    public String toString() {
        return String.format("Symbol redeclaration at line %d: %s", line, getMessage());
    }
}
