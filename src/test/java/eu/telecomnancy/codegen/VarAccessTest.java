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
        assertEquals("23 # 2346 # 234657", result.output);
    }

    @Test
    public void testNonLocalValueProcedure() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_proc.alg"));
        assertEquals("7", result.output);
    }

    @Test
    public void testNonLocalValueNestedProcedure() throws Exception {
        Result result =
                parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_nested_proc.alg"));
        assertEquals("14", result.output);
    }

    @Test
    public void testNonLocalValueStore() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_value_store.alg"));
        assertEquals("11 22", result.output);
    }

    @Test
    public void testNonLocalComplex() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_complex.alg"));
        assertEquals("5", result.output);
    }

    @Test
    public void testNonLocalRecursive() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/non_local_recursive.alg"));
        assertEquals("24 24 24 24", result.output);
    }
}
