package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.*;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.*;

import eu.telecomnancy.Content;
import org.junit.Test;

public class BasicTest {

    @Test
    public void testOutintegerNegative() throws Exception {
        Result result = parse("begin outinteger(1, -12) end");
        assertEquals("-12", result.output);
    }

    @Test
    public void testOutintegerPositive() throws Exception {
        Result result = parse("begin outinteger(1, 42) end");
        assertEquals("+42", result.output);
    }

    @Test
    public void testOutintegerTwoTimes() throws Exception {
        Content c = new Content();
        c.line("begin");
        c.line("  outinteger(1, 42);");
        c.line("  outinteger(1, 13)");
        c.line("end");
        Result result = parse(c);

        assertEquals("+42\n+13", result.output);
    }
}
