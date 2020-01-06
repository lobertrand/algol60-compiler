package eu.telecomnancy.semantic;

public class SymbolNotDeclaredException extends SemanticException {

    public SymbolNotDeclaredException(String message, int line) {
        super(message, line);
    }

    @Override
    public String toString() {
        return String.format("Algol60> Variable not declared at line %d: %s", line, getMessage());
    }
}
