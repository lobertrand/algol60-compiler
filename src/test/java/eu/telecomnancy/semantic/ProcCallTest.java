package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ProcCallTest {

    @Test
    public void testNoParam() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f;");
        c.line("  begin integer a end;");
        c.line("  f()");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testNoParamFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f;");
        c.line("  begin integer a end;");
        c.line("  f(12)");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, ParameterMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testIntParamConstant() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f(n);");
        c.line("  integer n;");
        c.line("  begin n := 5 end;");
        c.line("  f(12)");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testIntParamVariable() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer param;");
        c.line("  procedure f(n);");
        c.line("  integer n;");
        c.line("  begin n := 5 end;");
        c.line("  param := 12;");
        c.line("  f(param)");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testIntParamProcedure() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer res;");
        c.line("  integer procedure f(n);");
        c.line("  integer n;");
        c.line("  begin f := 5 end;");
        c.line("  res := f(f(f(12)))");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testMultipleTypes() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f(a, b, c);");
        c.line("  integer a; real b; string c;");
        c.line("  begin a := 5 end;");
        c.line("  f(10, 3.14, \"hello\")");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testMultipleTypesFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f(a, b, c);");
        c.line("  integer a; real b; string c;");
        c.line("  begin a := 5 end;");
        c.line("  f(10.5, 3.14, 6)");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(2, result);
    }

    @Test
    public void testNotEnoughParams() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f(a, b);");
        c.line("  integer a; real b;");
        c.line("  begin a := 5 end;");
        c.line("  f(6)");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, ParameterMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testTooManyParams() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  procedure f(a, b);");
        c.line("  integer a; real b;");
        c.line("  begin a := 5 end;");
        c.line("  f(6, 7.9, \"test\")");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, ParameterMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testReturnTypeMismatch() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real res;");
        c.line("  boolean procedure f;");
        c.line("  begin f := true end;");
        c.line("  res := f()");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }
}
