package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class IndexOutOfBoundsException extends SemanticException {

    public IndexOutOfBoundsException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Index out of bounds at line %d: %s", line, getMessage());
    }
}
