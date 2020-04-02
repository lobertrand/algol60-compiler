package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class RecursivityTest {

    @Test
    public void testFactorial() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/factorial.alg"));
        assertEquals("+120", result.output);
    }
}
