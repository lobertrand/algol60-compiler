package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class UnreachableStatementException extends SemanticException {

    public UnreachableStatementException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Unreachable statement at line %d: %s", line, getMessage());
    }
}
