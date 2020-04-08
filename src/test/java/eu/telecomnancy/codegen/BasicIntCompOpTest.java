package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class BasicIntCompOpTest {

    @Test
    public void testBasicOperators() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/int_comparison_op.alg"));
        String s =
                "true false false "
                        + "true true false"
                        + "true false false "
                        + "true true false"
                        + "true false"
                        + "true false";
        assertEquals(s.replaceAll(" ", ""), result.output);
    }
}
