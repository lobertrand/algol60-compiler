package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class SymbolNotDeclaredException extends SemanticException {

    public SymbolNotDeclaredException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Symbol not declared at line %d: %s", line, getMessage());
    }
}
