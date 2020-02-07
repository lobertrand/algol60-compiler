package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class SymbolNotDeclaredException extends SemanticException {

    public SymbolNotDeclaredException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Symbol not declared at line %d: %s", line, getMessage());
    }
}
