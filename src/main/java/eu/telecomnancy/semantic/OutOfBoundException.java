package eu.telecomnancy.semantic;

import org.antlr.runtime.tree.Tree;

public class OutOfBoundException extends SemanticException {
    public OutOfBoundException(String message, Tree tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Indice out of bound at line %d: %s", line, getMessage());
    }
}
