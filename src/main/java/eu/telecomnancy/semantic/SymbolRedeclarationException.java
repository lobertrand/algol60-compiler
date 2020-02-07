package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class SymbolRedeclarationException extends SemanticException {

    public SymbolRedeclarationException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Symbol redeclaration at line %d: %s", line, getMessage());
    }
}
