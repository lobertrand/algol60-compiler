package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.parse;
import static eu.telecomnancy.tools.IOUtils.*;
import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class DemoTest {

    @Test
    public void testBubbleSort() throws Exception {
        Helper.Result result = parse(loadString("/codegen/demo/bubble_sort.alg"));
        String expected = "Original vector: 3 8 2 7 1 4 Bubble-sorted: 1 2 3 4 7 8";
        assertEquals(expected, result.withLinesAsSpaces());
    }
}
