package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class TypeMismatchException extends SemanticException {

    public TypeMismatchException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Type mismatch at line %d: %s", line, getMessage());
    }
}
