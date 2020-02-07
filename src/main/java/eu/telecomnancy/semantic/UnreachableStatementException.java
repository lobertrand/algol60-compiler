package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class UnreachableStatementException extends SemanticException {

    public UnreachableStatementException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Unreachable statement at line %d: %s", line, getMessage());
    }
}
