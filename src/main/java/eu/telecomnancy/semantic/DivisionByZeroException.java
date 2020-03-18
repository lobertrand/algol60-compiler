package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class DivisionByZeroException extends SemanticException {

    public DivisionByZeroException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Division by zero at line %d: %s", line, getMessage());
    }
}
