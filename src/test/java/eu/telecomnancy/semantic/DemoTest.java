package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.*;

import eu.telecomnancy.tools.IOUtils;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class DemoTest {

    @Test
    @Parameters({
        "01_correct.alg",
        "02_correct.alg",
        "03_correct.alg",
    })
    public void testCorrect(String testName) throws Exception {
        String content = IOUtils.loadString("/semantics/demo/" + testName);
        Result result = checkSemantics(content);
        assertExceptionQuantity(0, result);
    }

    @Test
    @Parameters({
        "01_broken.alg",
        "02_broken.alg",
        "03_broken.alg",
    })
    public void testBroken(String testName) throws Exception {
        String content = IOUtils.loadString("/semantics/demo/" + testName);
        Result result = checkSemantics(content);
        assertFalse(result.exceptions.isEmpty());
    }
}
