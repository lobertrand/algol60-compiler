package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ArrayAssignementTest {
    @Test
    public void testAssignementSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10];");
        c.line("  myArray[5] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testAssignementOutOfBoundSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10];");
        c.line("  myArray[12] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, OutOfBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testAssignementMultiDimensionSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10,5:20];");
        c.line("  myArray[5,12] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testAssignementMultiDimensionOutOfBoundSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10,5:20];");
        c.line("  myArray[5,22] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, OutOfBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testAssignementNotIntegerIndice() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string b;");
        c.line("  integer array myArray[1:10];");
        c.line("  myArray[b] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testAssignementToUndeclaredVariable() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  myArray[5] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(2, SymbolNotDeclaredException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testAssignementToNonArrayType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string b;");
        c.line("  b[5] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testAssignementWithToManyIndices() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10,5:20];");
        c.line("  myArray[5,22,12] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, IncompatibleBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testAssignementWithTofewIndices() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer array myArray[1:10,5:20];");
        c.line("  myArray[5] := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, IncompatibleBoundException.class, result);
        assertExceptionQuantity(1, result);
    }
}
