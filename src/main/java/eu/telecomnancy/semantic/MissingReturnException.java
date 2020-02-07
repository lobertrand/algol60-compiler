package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class MissingReturnException extends SemanticException {

    public MissingReturnException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Missing return at line %d: %s", line, getMessage());
    }
}
