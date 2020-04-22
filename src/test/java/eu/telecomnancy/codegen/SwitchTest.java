package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class SwitchTest {

    @Test
    public void testBasicSwitch() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/switch.alg"));
        assertEquals("first third", result.output);
    }

    @Test
    public void testBasicSwitch2() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/switch2.alg"));
        assertEquals("second third", result.output);
    }

    @Test
    public void testBasicSwitchOutOfBounds() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/switch3.alg"));
        assertEquals("Error: Index out of bounds", result.output);
    }
}
