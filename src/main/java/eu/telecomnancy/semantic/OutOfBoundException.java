package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class OutOfBoundException extends SemanticException {

    public OutOfBoundException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return String.format("Indice out of bound at line %d: %s", line, getMessage());
    }
}
