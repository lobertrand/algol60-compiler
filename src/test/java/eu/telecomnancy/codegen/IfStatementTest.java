package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class IfStatementTest {

    @Test
    public void testNestedIfElse() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/nested_if_else.alg"));
        assertEquals("START B B2 END", result.output);
    }
}
