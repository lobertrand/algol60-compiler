package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ArrayDecTest {

    @Test
    public void testDeclarationSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testDeclarationNegativeBounds() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[-20:12]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(2, SemanticException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testDeclarationInvertedBounds() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[12:6]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(2, SemanticException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testDeclarationVariables() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  integer array myArray[a:b];");
        c.line("  a := 6; b := 9");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, SemanticException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testDeclaration2D() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10, 5:42]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testDeclaration3D() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10, 5:42, 12:6]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testDeclarationReal() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real array myArray[1:10]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testDeclarationString() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string array myArray[1:10]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRedeclaration() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string array myArray[1:10];");
        c.line("  string array myArray[3:2]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SemanticException.class, result);
        assertExceptionQuantity(1, result);
    }
}
