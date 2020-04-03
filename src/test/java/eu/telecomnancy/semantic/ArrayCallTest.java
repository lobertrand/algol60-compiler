package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import eu.telecomnancy.Content;
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
        assertExceptionAtLine(4, IndexOutOfBoundsException.class, result);
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
        assertExceptionAtLine(4, IndexOutOfBoundsException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testCallNotIntegerIndex() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("  string b ;");
        c.line("  integer array myArray[1:10,5:25];");
        c.line("  a := myArray[b,15]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
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

    @Test
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

    @Test
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

    @Test
    public void testAssignmentWithToFewIndices() throws RecognitionException {
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

    @Test
    public void testRealIndexVariable() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real i;");
        c.line("  integer res;");
        c.line("  integer array myArray[1:10];");
        c.line("  res := myArray[i]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealIndexInBounds() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer res;");
        c.line("  integer array myArray[1:10];");
        c.line("  res := myArray[10.45]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealIndexOutOfBounds() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer res;");
        c.line("  integer array myArray[1:10];");
        c.line("  res := myArray[10.55]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, IndexOutOfBoundsException.class, result);
        assertExceptionQuantity(1, result);
    }
}
