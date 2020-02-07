package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ArrayCallTest {
    @Test
    public void testCallSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10];");
        c.line("  a := myArray[5]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testCallOutOfBoundSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10];");
        c.line("  a := myArray[15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, OutOfBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testCallMultiDimensionSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[5,15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testCallMultiDimensionOutOfBoundSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[5,50]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, OutOfBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testCallNotIntegerIndice() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  string b ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[b,15]");
        c.line("end");
        ;

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testCallToUndeclaredVariable() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  a := myArray[5,15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolNotDeclaredException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testCallToNonArrayType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("real myArray;");
        c.line("  a := myArray[2,15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testCallWithToManyIndices() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[5,10,15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, IncompatibleBoundException.class, result);
        assertExceptionQuantity(1, result);
    }

    public void testAssignementWithTofewIndices() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[5]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, IncompatibleBoundException.class, result);
        assertExceptionQuantity(1, result);
    }
}
