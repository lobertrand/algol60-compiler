package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import eu.telecomnancy.Content;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class BooleanExprTest {

    @Test
    public void testBooleanConstants() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  b := true;");
        c.line("  b := false");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testCombinedBooleanConstants() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  b := false /\\ true;");
        c.line("  b := false \\/ true;");
        c.line("  b := false <=> true;");
        c.line("  b := false => true;");
        c.line("  b := ~true;");
        c.line("  b := ~false;");
        c.line("  b := false <=> ~true;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testVariablesAndConstants() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  integer i;");
        c.line("  b := 1 > i;");
        c.line("  b := 1 < i;");
        c.line("  b := 1 <= i;");
        c.line("  b := 1 >= i;");
        c.line("  b := 1 = i;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testComplexExpressions() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  integer i;");
        c.line("  b := 12 > 3 /\\ 6 <= 2;");
        c.line("  b := true <=> 12 > 3 /\\ 6 <= 2;");
        c.line("  b := 5*6-4 > i \\/ true <=> 12 > 3 /\\ 6 <= 2;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testParentheses() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  integer i;");
        c.line("  b := 12 > 3 /\\ (6 <= (2 + 2));");
        c.line("  b := ((true)) <=> ((12 > 3) /\\ 6 <= 2);");
        c.line("  b := (5*6-4 > i \\/ true) <=> 12 > 3 /\\ 6 <= 2;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testComplexExpressionsFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  integer i;");
        c.line("  b := 12 > 3 /\\ 6 \\/ 2;");
        c.line("  b := ~true + 9 <=> 12 > 3 /\\ 6 <= 2;");
        c.line("  b := ~true + 9 <=> 12 > 3 /\\ 6 <= 2;");
        c.line("  b := true > 2;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionAtLine(6, TypeMismatchException.class, result);
        assertExceptionAtLine(7, TypeMismatchException.class, result);
        assertExceptionQuantity(4, result);
    }

    @Test
    public void testCannotNegate() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  b := ~45;");
        c.line("  b := ~\"str\";");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(2, result);
    }
}
