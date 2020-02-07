package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class MissingStatementException extends SemanticException {

    public MissingStatementException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Missing statement at line %d: %s", line, getMessage());
    }
}
