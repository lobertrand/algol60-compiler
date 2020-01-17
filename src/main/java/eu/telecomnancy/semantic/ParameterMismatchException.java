package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class ParameterMismatchException extends SemanticException {

    public ParameterMismatchException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Parameter mismatch at line %d: %s", line, getMessage());
    }
}
