package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ArithmeticExprTest {

    @Test
    public void testOperationsWithConstants() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  real r;");
        c.line("  i := 6 + 5;");
        c.line("  i := 6 - 5;");
        c.line("  i := 6 * 5;");
        c.line("  r := 6.5 * 5;");
        c.line("  r := 6.5 * 5.5;");
        c.line("  r := 6 * 5.5;");
        c.line("  r := 6 / 5;");
        c.line("  r := 6.5 / 5;");
        c.line("  r := 6.5 / 5.5;");
        c.line("  r := 6 / 5.5;");
        c.line("  i := 6 // 5;");
        c.line("  i := 6 ** 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be no exception", 0, result.exceptions.size());
    }

    @Test
    public void testRealResultFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  i := 6 / 5;");
        c.line("  i := 6.5 + 5;");
        c.line("  i := 6.5 - 5;");
        c.line("  i := 6.5 / 5;");
        c.line("  i := 6.5 * 5;");
        c.line("  i := 6 + 5.5;");
        c.line("  i := 6 - 5.5;");
        c.line("  i := 6 / 5.5;");
        c.line("  i := 6 * 5.5");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be 9 exceptions", 9, result.exceptions.size());
        assertTrue("There should and exception at line 3", result.exceptionAt(3));
        assertTrue("There should and exception at line 4", result.exceptionAt(4));
        assertTrue("There should and exception at line 5", result.exceptionAt(5));
        assertTrue("There should and exception at line 6", result.exceptionAt(6));
        assertTrue("There should and exception at line 7", result.exceptionAt(7));
        assertTrue("There should and exception at line 8", result.exceptionAt(8));
        assertTrue("There should and exception at line 9", result.exceptionAt(9));
        assertTrue("There should and exception at line 10", result.exceptionAt(10));
        assertTrue("There should and exception at line 11", result.exceptionAt(11));
    }

    @Test
    public void testRealVariablesFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  real r;");
        c.line("  i := r + 5;");
        c.line("  i := r - 5;");
        c.line("  i := r / 5;");
        c.line("  i := r * 5;");
        c.line("  i := 6 + r;");
        c.line("  i := 6 - r;");
        c.line("  i := 6 / r;");
        c.line("  i := 6 * r");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be 8 exceptions", 8, result.exceptions.size());
        assertTrue("There should and exception at line 4", result.exceptionAt(4));
        assertTrue("There should and exception at line 5", result.exceptionAt(5));
        assertTrue("There should and exception at line 6", result.exceptionAt(6));
        assertTrue("There should and exception at line 7", result.exceptionAt(7));
        assertTrue("There should and exception at line 8", result.exceptionAt(8));
        assertTrue("There should and exception at line 9", result.exceptionAt(9));
        assertTrue("There should and exception at line 10", result.exceptionAt(10));
        assertTrue("There should and exception at line 11", result.exceptionAt(11));
    }

    @Test
    public void testRealComposed() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real r;");
        c.line("  r := 3 * 6 * r / 5 + 6.2;");
        c.line("  r := r + r * r;");
        c.line("  r := r * r + r;");
        c.line("  r := 12.5 / r;");
        c.line("  r := 12.5 ** r");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be no exception", 0, result.exceptions.size());
    }

    @Test
    public void testOperationsWithConstantsFailing() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  a := 6 + \"str\";");
        c.line("  a := 6 - \"str\";");
        c.line("  a := 6 * \"str\";");
        c.line("  a := 6 / \"str\";");
        c.line("  a := 6 // \"str\";");
        c.line("  a := 6 ** \"str\";");
        c.line("  a := \"str\" + 5;");
        c.line("  a := \"str\" - 5;");
        c.line("  a := \"str\" * 5;");
        c.line("  a := \"str\" / 5;");
        c.line("  a := \"str\" // 5;");
        c.line("  a := \"str\" ** 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be 12 exceptions", 12, result.exceptions.size());
        assertTrue("There should and exception at line 3", result.exceptionAt(3));
        assertTrue("There should and exception at line 4", result.exceptionAt(4));
        assertTrue("There should and exception at line 5", result.exceptionAt(5));
        assertTrue("There should and exception at line 6", result.exceptionAt(6));
        assertTrue("There should and exception at line 7", result.exceptionAt(7));
        assertTrue("There should and exception at line 8", result.exceptionAt(8));
        assertTrue("There should and exception at line 9", result.exceptionAt(9));
        assertTrue("There should and exception at line 10", result.exceptionAt(10));
        assertTrue("There should and exception at line 11", result.exceptionAt(11));
        assertTrue("There should and exception at line 12", result.exceptionAt(12));
        assertTrue("There should and exception at line 13", result.exceptionAt(13));
        assertTrue("There should and exception at line 14", result.exceptionAt(14));
    }
}
