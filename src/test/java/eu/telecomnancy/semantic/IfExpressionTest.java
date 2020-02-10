package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class IfExpressionTest {

    @Test
    public void testIntegers() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  integer v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 2 else 3;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testStrings() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  string v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then \"one\" else \"two\";");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealAndInteger() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  real v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 42 else 3.14;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRealAndIntegerFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  integer v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 42 else 3.14;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testIntegerAndStringFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  string v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 42 else \"str\";");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testBoolean() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  boolean v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then true else false;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testNestedIfExpressions() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  integer v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 30");
        c.line("       else if n < 3 then 40");
        c.line("       else 50");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testNestedIfExpressionsFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  integer v;");
        c.line("  n := 3;");
        c.line("  v := if n > 0 then 30");
        c.line("       else if n < 3 then 40.0");
        c.line("       else 50");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }
}
