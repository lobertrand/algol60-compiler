package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ForTest {

    @Test
    public void testForUntil() throws Exception {
        Result result = parse("begin integer i; for i:= 1 step 1 until 20 do outinteger(1,i) end");
        String s = "";
        for (int i = 1; i < 20; i++) {
            s = s + "+" + i + "\n";
        }
        s = s + "+20";
        assertEquals(s, result.output);
    }

    @Test
    public void testForEnum() throws Exception {
        Result result = parse("begin integer i; for i:=1, 2, 4, 5, 9 do outinteger(1,i) end");
        String s = "+1\n+2\n+4\n+5\n+9";
        assertEquals(s, result.output);
    }

    @Test
    public void testForWhile() throws Exception {
        Result result =
                parse(
                        "begin integer i, n; n := 0; for i:=1 while n<=10 do begin outinteger(1,n); n := n+1; end; end");
        String s = "";
        for (int i = 0; i < 10; i++) {
            s = s + "+" + i + "\n";
        }
        s = s + "+10";
        assertEquals(s, result.output);
    }
}
