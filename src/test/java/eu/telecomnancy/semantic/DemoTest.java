package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import eu.telecomnancy.tools.IOUtils;
import org.junit.Test;

public class DemoTest {

    @Test
    public void testDemo1() throws Exception {
        String content = IOUtils.loadString("/semantics/demo/valid_procedure.alg");
        checkSemantics(content);
    }
}
