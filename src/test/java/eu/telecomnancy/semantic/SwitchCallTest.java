package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class SwitchCallTest {

    @Test
    public void testSwitchBase() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch new := a,b,r ;");
        c.line("   a: ;");
        c.line(" r: ");
        c.line(" b: ;");
        c.line("integer q ;");
        c.line("ininteger(q,6);");
        c.line(" new[2]");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }
}
