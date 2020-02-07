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


}
