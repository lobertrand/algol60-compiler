package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ForClauseTest {

    @Test
    public void testSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer index;");
        c.line("  for index := 45 step 1 until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real i;");
        c.line("  for i := 3.14 step 1 until 20 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testStringInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string s;");
        c.line("  for s := \"index\" step 1 until 20 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testProcCallInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer procedure startIndex;");
        c.line("  begin");
        c.line("    startIndex := 3");
        c.line("  end;");
        c.line("  string s;");
        c.line("  for s := startIndex() step 1 until 20 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testStringStep() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step \"step\" until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testRealStep() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2.5 until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealUntil() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2 until 23.3 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testBooleanUntil() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2 until true do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }
}
