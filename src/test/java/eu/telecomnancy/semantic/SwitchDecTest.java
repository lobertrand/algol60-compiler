package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.*;

import eu.telecomnancy.symbols.*;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class SwitchDecTest {
    @Test
    public void testNoParam() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  a:;");
        c.line("  switch b:= a;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }
}
