package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class BasicBoolOperatorsTest {

    @Test
    public void testBasicOperators() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/basic_bool_op.alg"));
        String s =
                "false true true false "
                        + "false true true true"
                        + "false false false true"
                        + "true true false true"
                        + "true false false true";
        assertEquals(s.replaceAll(" ", ""), result.output);
    }
}
