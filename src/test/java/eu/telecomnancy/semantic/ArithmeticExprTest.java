package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;

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
        for (int i = 3; i <= 11; i++) {
            assertExceptionAtLine(i, TypeMismatchException.class, result);
        }
        assertEquals("There should be 9 exceptions", 9, result.exceptions.size());
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
        for (int i = 4; i <= 11; i++) {
            assertExceptionAtLine(i, TypeMismatchException.class, result);
        }
        assertEquals("There should be 8 exceptions", 8, result.exceptions.size());
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
        for (int i = 3; i <= 14; i++) {
            assertExceptionAtLine(i, TypeMismatchException.class, result);
        }
        assertEquals("There should be 12 exceptions", 12, result.exceptions.size());
    }
}
