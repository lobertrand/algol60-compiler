package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.parse;
import static eu.telecomnancy.tools.IOUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DemoTest {

    @Test
    public void testBubbleSort() throws Exception {
        Helper.Result result = parse(loadString("/codegen/demo/bubble_sort.alg"));
        String expected = "Original vector:\n3 8 2 7 1 4 \nBubble-sorted:\n1 2 3 4 7 8";
        assertEquals(expected, result.output);
    }

    @Test
    public void testFactorial() throws Exception {
        Helper.Result result = parse(loadString("/codegen/demo/factorial.alg"));
        String expected = "6! = 720 4! = 24 7! = 5040 3! = 6";
        assertEquals(expected, result.withLinesAsSpaces());
    }
}
