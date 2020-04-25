package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class IfExpressionTest {

    @Test
    public void testIfExpression() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/if_expression.alg"));
        assertEquals("START 4 32 END", result.output);
    }

    @Test
    public void testIfExpression2() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/if_expression2.alg"));
        assertEquals("START 4 20000 END", result.output);
    }
}
