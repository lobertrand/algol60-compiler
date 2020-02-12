package eu.telecomnancy.syntax;

import org.antlr.runtime.IntStream;
import org.antlr.runtime.RecognitionException;

public class InvalidNumberException extends RecognitionException {

    public String message;

    public InvalidNumberException(String message, IntStream input) {
        super(input);
        this.message = message;
    }
}
