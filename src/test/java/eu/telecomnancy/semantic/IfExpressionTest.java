package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class IfExpressionTest {

    @Test
    public void test_IfExpression_base() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n;");
        c.line("integer v ;");
        c.line("    n := 3 ; ");
        c.line("    v := 3 ; ");
        ;
        c.line("  if n+v > 0 then v := 3;");
        c.line("  end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }
}
