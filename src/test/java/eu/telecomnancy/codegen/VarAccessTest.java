package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class VarAccessTest {

    @Test
    public void testNonLocalValue() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_value.alg"));
        assertEquals("+2+3#+2+3+4+6#+2+3+4+6+5+7", result.output.replaceAll("\n", ""));
    }

    @Test
    public void testNonLocalValueProcedure() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_proc.alg"));
        assertEquals("+7", result.output.replaceAll("\n", ""));
    }

    @Test
    public void testNonLocalValueNestedProcedure() throws Exception {
        Result result =
                parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_nested_proc.alg"));
        assertEquals("+14", result.output.replaceAll("\n", ""));
    }

    @Test
    public void testNonLocalValueStore() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_store.alg"));
        assertEquals("+11+22", result.output.replaceAll("\n", ""));
    }
}
