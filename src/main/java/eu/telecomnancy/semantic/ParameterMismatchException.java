package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class ParameterMismatchException extends SemanticException {

    public ParameterMismatchException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Parameter mismatch at line %d: %s", line, getMessage());
    }
}
