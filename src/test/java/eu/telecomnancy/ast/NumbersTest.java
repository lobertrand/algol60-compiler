package eu.telecomnancy.ast;

import static eu.telecomnancy.ast.Helper.parse;
import static org.junit.Assert.assertEquals;

import eu.telecomnancy.Algol60Parser;
import eu.telecomnancy.tools.IOUtils;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;
import org.junit.Test;

public class NumbersTest {

    @Test
    public void testIntegers() throws RecognitionException {
        Tree root = parse("begin a := 42; b := -42 end");
        Tree block = root.getChild(0);
        Tree aDec = block.getChild(0);
        Tree bDec = block.getChild(1);
        Tree aNum = aDec.getChild(1);
        Tree bNum = bDec.getChild(1);
        assertEquals("42 should be an INT", Algol60Parser.INT, aNum.getType());
        assertEquals("-42 should be an INT", Algol60Parser.INT, bNum.getType());
        assertEquals("\"42\" should be the text", "42", aNum.getText());
        assertEquals("\"-42\" should be the text", "-42", bNum.getText());
    }

    @Test
    public void testReals() throws RecognitionException {
        Tree root = parse("begin a := 3.14; b := -3.14 end");
        Tree block = root.getChild(0);
        Tree aDec = block.getChild(0);
        Tree bDec = block.getChild(1);
        Tree aNum = aDec.getChild(1);
        Tree bNum = bDec.getChild(1);
        assertEquals("3.14 should be a REAL", Algol60Parser.REAL, aNum.getType());
        assertEquals("-3.14 should be a REAL", Algol60Parser.REAL, bNum.getType());
        assertEquals("\"3.14\" should be the text", "3.14", aNum.getText());
        assertEquals("\"-3.14\" should be the text", "-3.14", bNum.getText());
    }

    @Test
    public void testRealPow10() throws RecognitionException {
        Tree root = parse("begin a := 3.14#5; b := -3.14#5 end");
        Tree block = root.getChild(0);
        Tree aDec = block.getChild(0);
        Tree bDec = block.getChild(1);
        Tree aNum = aDec.getChild(1);
        Tree bNum = bDec.getChild(1);
        assertEquals("3.14#5 should be a POW_10", Algol60Parser.POW_10, aNum.getType());
        assertEquals("-3.14#5 should be a POW_10", Algol60Parser.POW_10, bNum.getType());
        assertEquals("3.14#5 should have two children", 2, aNum.getChildCount());
        assertEquals("-3.14#5 should have two children", 2, bNum.getChildCount());
        Tree aLeft = aNum.getChild(0);
        Tree aRight = aNum.getChild(1);
        Tree bLeft = bNum.getChild(0);
        Tree bRight = bNum.getChild(1);
        assertEquals("\"3.14\" should be the text", "3.14", aLeft.getText());
        assertEquals("\"5\" should be the text", "5", aRight.getText());
        assertEquals("\"-3.14\" should be the text", "-3.14", bLeft.getText());
        assertEquals("\"5\" should be the text", "5", bRight.getText());
    }

    @Test
    public void testIntegerPow10() throws RecognitionException {
        Tree root = parse("begin a := 3#5; b := -3#5 end");
        Tree block = root.getChild(0);
        Tree aDec = block.getChild(0);
        Tree bDec = block.getChild(1);
        Tree aNum = aDec.getChild(1);
        Tree bNum = bDec.getChild(1);
        assertEquals("3#5 should be a POW_10", Algol60Parser.POW_10, aNum.getType());
        assertEquals("-3#5 should be a POW_10", Algol60Parser.POW_10, bNum.getType());
        assertEquals("3#5 should have two children", 2, aNum.getChildCount());
        assertEquals("-3#5 should have two children", 2, bNum.getChildCount());
        Tree aLeft = aNum.getChild(0);
        Tree aRight = aNum.getChild(1);
        Tree bLeft = bNum.getChild(0);
        Tree bRight = bNum.getChild(1);
        assertEquals("\"3\" should be the text", "3", aLeft.getText());
        assertEquals("\"5\" should be the text", "5", aRight.getText());
        assertEquals("\"-3\" should be the text", "-3", bLeft.getText());
        assertEquals("\"5\" should be the text", "5", bRight.getText());
    }

    @Test
    public void testAllNumbersCombined() throws Exception {
        String content = IOUtils.loadString("/semantics/unit_tests/all_numbers.alg");
        parse(content); // Should compile without exception
    }
}
