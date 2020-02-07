package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class TypeMismatchException extends SemanticException {

    public TypeMismatchException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Type mismatch at line %d: %s", line, getMessage());
    }
}
