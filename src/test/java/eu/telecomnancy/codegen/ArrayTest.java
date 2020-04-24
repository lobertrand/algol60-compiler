package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static eu.telecomnancy.tools.IOUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ArrayTest {

    @Test
    public void testArrayProcCall() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "  integer array a[1:3];"
                                + "  procedure test(v); integer array v;"
                                + "  begin "
                                + "    v[1]:=2;"
                                + "    v[2]:=4;"
                                + "    v[3]:=6;"
                                + "    outinteger(1, v[1]);"
                                + "    outinteger(1, v[2]);"
                                + "    outinteger(1, v[3]);"
                                + "  end;"
                                + "  test(a);"
                                + "end");
        assertEquals("2 4 6", result.output.replaceAll("\n", " "));
    }

    @Test
    public void testArrayDeclaration() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "integer array nArr[1:5]; "
                                + "nArr[1] := 1; "
                                + "nArr[5] := 5; "
                                + "outinteger(1, nArr[5]); "
                                + "outinteger(1, nArr[3]) "
                                + "end");
        String s = "5 0";
        assertEquals(s, result.output.replaceAll("\n", " "));
    }

    @Test
    public void testArrayAssignment() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "integer array nArr[1:10]; "
                                + "integer i; "
                                + "for i := 1 step 1 until 10 do "
                                + "begin nArr[i] := i*i; "
                                + "outinteger (1, nArr[i]) "
                                + "end ; "
                                + "end");
        String s = "1 4 9 16 25 36 49 64 81 100";
        assertEquals(s, result.output.replaceAll("\n", " "));
    }

    @Test
    public void testTwoDimArray() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "  real array r[1:10, 1:10]; "
                                + "  integer i, j; "
                                + "  for i := 1 step 1 until 10 do "
                                + "  begin "
                                + "    for j := 1 step 1 until 10 do "
                                + "    begin "
                                + "      r[i,j] := i*j;"
                                + "      outreal(1, r[i,j]);"
                                + "    end "
                                + "  end "
                                + "end ");
        String s = "";
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                s = s + (i * j) + " ";
            }
        }
        assertEquals(s.trim(), result.output.replaceAll("\n", " "));
    }

    @Test
    public void testTwoDimArrayNoOuterBlock() throws Exception {
        Result result =
                parse(
                        "begin "
                                + "  real array r[1:10, 1:10]; "
                                + "  integer i, j; "
                                + "  for i := 1 step 1 until 10 do "
                                + "    for j := 1 step 1 until 10 do "
                                + "    begin "
                                + "      r[i,j] := i*j;"
                                + "      outreal(1, r[i,j]);"
                                + "    end "
                                + "end ");
        String s = "";
        for (int i = 1; i <= 10; i++) {
            for (int j = 1; j <= 10; j++) {
                s = s + (i * j) + " ";
            }
        }
        assertEquals(s.trim(), result.output.replaceAll("\n", " "));
    }

    @Test
    public void testStringArray() throws Exception {
        Result result = parse(loadString("/codegen/unit_tests/basic_string_array.alg"));

        String s = "i like potatoes";
        assertEquals(s, result.output);
    }

    @Test
    public void testBoolArray() throws Exception {
        Result result = parse(loadString("/codegen/unit_tests/basic_bool_array.alg"));
        String s = "truefalsetrue";
        assertEquals(s, result.output);
    }
}
