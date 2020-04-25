package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class Niveau1Test {

    @Test
    public void testNiveau1() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/niveau1.alg"));
        assertEquals("012345", result.output);
    }
}
