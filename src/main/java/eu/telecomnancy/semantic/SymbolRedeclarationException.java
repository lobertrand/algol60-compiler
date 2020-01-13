package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class SymbolRedeclarationException extends SemanticException {

    public SymbolRedeclarationException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Symbol redeclaration at line %d: %s", line, getMessage());
    }
}
