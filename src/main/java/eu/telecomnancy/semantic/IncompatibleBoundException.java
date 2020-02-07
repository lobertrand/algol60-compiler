package eu.telecomnancy.semantic;

import eu.telecomnancy.ast.DefaultAST;

public class IncompatibleBoundException extends SemanticException {

    public IncompatibleBoundException(String message, DefaultAST tree) {
        super(message, tree);
    }

    @Override
    public String toString() {
        return (String.format("Incompatible bound at line %d: %s", line, getMessage()));
    }
}
