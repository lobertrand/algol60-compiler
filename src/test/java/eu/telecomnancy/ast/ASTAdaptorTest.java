package eu.telecomnancy.ast;

import static org.junit.Assert.*;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.CommonTree;
import org.antlr.runtime.tree.Tree;
import org.junit.Test;

public class ASTAdaptorTest {

    private Tree parse(String content) throws RecognitionException {
        ANTLRStringStream stream = new ANTLRStringStream(content);
        Algol60Lexer lexer = new Algol60Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        return parser.prog().getTree();
    }

    @Test
    public void testRoot() throws RecognitionException {
        Tree root = parse("begin a := 5 end");
        assertTrue("ROOT should be a RootAST", root instanceof RootAST);
        assertEquals("ROOT should have 1 child", 1, root.getChildCount());
    }

    @Test
    public void testBlock() throws RecognitionException {
        Tree root = parse("begin a := 5 end");
        Tree block = root.getChild(0);
        assertTrue("BLOCK should be a BlockAST", block instanceof BlockAST);
        assertEquals("BLOCK should have 1 child", 1, block.getChildCount());
    }

    @Test
    public void testVarDec() throws RecognitionException {
        Tree root = parse("begin integer a, b end");
        Tree block = root.getChild(0);
        Tree varDec = block.getChild(0);
        assertTrue("Child should be a VarDecAST", varDec instanceof VarDecAST);
        assertEquals("VAR_DEC should have 2 children", 2, varDec.getChildCount());

        assertTrue(
                "VAR_DEC child 0 should be a CommonTree", varDec.getChild(0) instanceof CommonTree);
        assertTrue(
                "VAR_DEC child 1 should be a DefaultAST", varDec.getChild(1) instanceof DefaultAST);

        Tree idList = varDec.getChild(1);
        assertEquals("ID_LIST should have 2 children", 2, idList.getChildCount());
        assertTrue(
                "ID_LIST child 0 should be a DefaultAST", idList.getChild(0) instanceof DefaultAST);
        assertTrue(
                "ID_LIST child 1 should be a DefaultAST", idList.getChild(1) instanceof DefaultAST);
    }

    @Test
    public void testProcDec() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  integer procedure max(a, b);\n";
        content += "  value a, b;\n";
        content += "  begin\n";
        content += "    if a > b then max := a\n";
        content += "    else max := b\n";
        content += "  end\n";
        content += "end\n";
        Tree root = parse(content);
        Tree block = root.getChild(0);
        Tree procDec = block.getChild(0);
        assertTrue("Child should be a ProcDecAST", procDec instanceof ProcDecAST);
        assertEquals("PROC_DEC should have 3 children", 3, procDec.getChildCount());
        Tree procedureBlock = procDec.getChild(2);
        assertTrue(
                "Third child should be a BlockAST for the procedure",
                procedureBlock instanceof BlockAST);
    }
}
