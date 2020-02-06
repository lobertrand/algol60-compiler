package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class IncompatibleBoundException extends SemanticException {

    public IncompatibleBoundException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return (String.format("The boundaries are incompatible at line %d,%s", line, getMessage()));
    }
}
