package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class MissingReturnException extends SemanticException {

    public MissingReturnException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Missing return at line %d: %s", line, getMessage());
    }
}
