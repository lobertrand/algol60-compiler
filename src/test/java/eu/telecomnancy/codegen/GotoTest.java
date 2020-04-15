package eu.telecomnancy.codegen;

import static eu.telecomnancy.codegen.Helper.Result;
import static eu.telecomnancy.codegen.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class GotoTest {

    @Test
    public void testBasicGoto() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/basic_goto.alg"));
        assertEquals("HELLO 2 3 END", result.output);
    }

    @Test
    public void testGoto2() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/goto.alg"));
        assertEquals("HELLO HELLO HELLO HELLO HELLO END", result.output);
    }

    @Test
    public void testGoto_imbrique() throws Exception {
        Result result = parse(IOUtils.loadString("/codegen/unit_tests/goto_imbrique.alg"));
        assertEquals("start boucle boucle boucle boucle boucle endlabel", result.output);
    }
}
