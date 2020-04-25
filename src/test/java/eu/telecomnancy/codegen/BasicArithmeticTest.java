package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BasicArithmeticTest {

    @Test
    public void testBasicCalculus() throws Exception {
        Result result =
                parse(
                        "begin integer a, b; a:=1; b:=2; outinteger(1, a+b); space();"
                                + "outinteger(1, a-b); space();"
                                + "outinteger(1, a*b); space();"
                                + "outinteger(1, a//b); space();"
                                + "outinteger(1, (b*b*b*b*b+a*(b*b+a)+b-a)//b) end");
        String s = "3 -1 2 0 19";
        assertEquals(s, result.output);
    }

    @Test
    public void testDiv() throws Exception {
        Result result = parse("begin integer a, b; a:=3; b:=2; outreal(1, a/b) end");
        String s = "1";
        assertEquals(s, result.output);
    }

    @Test
    public void testPowPow() throws Exception {
        Result result = parse("begin integer a, b; a:=2; b:=2;" + "outreal(1, a**2);" + "end");
        String s = "4";
        assertEquals(s, result.output);
    }

    @Test
    public void testPow10() throws Exception {
        Result result = parse("begin outreal(1, 2#2) end");
        String s = "200";
        assertEquals(s, result.output);
    }
}
