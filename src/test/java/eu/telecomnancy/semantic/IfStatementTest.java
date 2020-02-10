package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class IfStatementTest {

    @Test
    public void test_IfStatement_base() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("integer v ;");
        c.line("    n := 3 ; ");
        c.line("  if n  > 0 then v := 3;");
        c.line("  end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_IfStatement2() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("integer v ;");
        c.line("    n := 3 ; ");
        c.line("   v:= 4;");
        c.line("  if n <> v then a:  ;");
        c.line("   goto a ");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_IfStatementBlock() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("   i := 3;");
        c.line("  if i < 5 then ");
        c.line("    begin");
        c.line("     integer a");
        c.line("    end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_IfElseStatement() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  i := 5 ;");
        c.line("  if i <> 3 then x:");
        c.line("   else b: ");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_IfTypeMismatch() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real r;");
        c.line("  if r  then x:");
        c.line("   else b: ");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
    }

    @Test
    public void test_IfSymbolNotDeclared() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real e;");
        c.line("  if e <> a then x:");
        c.line("   else b: ");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(3, SymbolNotDeclaredException.class, result);
    }

    @Test
    public void test_IfImbrique() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("  n := 3 ;");
        c.line("  if n < 6 then ");
        c.line("   if n > 2 then a: ");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StringIfCondition() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  if \"true\" then");
        c.line("    a := 5");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void test_ErrorInThen() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  if true then");
        c.line("    a := \"str\"");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void test_ErrorInThenAndElse() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  if true then");
        c.line("    a := \"str\"");
        c.line("  else");
        c.line("    a := \"str\"");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionAtLine(6, TypeMismatchException.class, result);
        assertExceptionQuantity(2, result);
    }

    @Test
    public void test_ErrorInConditionThenAndElse() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  if 1 then");
        c.line("    a := \"str\"");
        c.line("  else");
        c.line("    a := \"str\"");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionAtLine(6, TypeMismatchException.class, result);
        assertExceptionQuantity(3, result);
    }
}
