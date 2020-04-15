package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static eu.telecomnancy.tools.IOUtils.*;
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
    public void testForUntilNested() throws Exception {
        Result result = parse(loadString("/codegen/unit_tests/for_until_nested.alg"));
        assertEquals("+3+5+7+9", result.output.replaceAll("\n", ""));
    }

    @Test
    public void testForEnum() throws Exception {
        Result result = parse("begin integer i; for i:=1, 2, 4, 5, 9 do outinteger(1,i) end");
        String s = "+1\n+2\n+4\n+5\n+9";
        assertEquals(s, result.output);
    }

    @Test
    public void testForEnumBlock() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "integer i, a; "
                                + "for i := 1, 2, 4, 5, 9 do "
                                + "  begin outinteger(1,i) end "
                                + "end");
        String s = "+1+2+4+5+9";
        assertEquals(s, result.output.replaceAll("\n", ""));
    }

    @Test
    public void testForEnumNested() throws Exception {
        Result result = parse(loadString("/codegen/unit_tests/for_enum_nested.alg"));
        String s = "+3+4+5";
        assertEquals(s, result.output.replaceAll("\n", ""));
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

    @Test
    public void testForWhileNested() throws Exception {
        Result result = parse(loadString("/codegen/unit_tests/for_while_nested.alg"));
        String s = "+1+2+3+4+5+6";
        assertEquals(s, result.output.replaceAll("\n", ""));
    }
}
