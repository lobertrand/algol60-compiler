package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eu.telecomnancy.symbols.Label;
import eu.telecomnancy.symbols.SymbolTable;
import java.util.Set;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class LabelGotoTest {

    @Test
    public void testSimple() throws RecognitionException {
        String content = "begin a: end";

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 1 sub table", 1, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
        assertTrue("a should be a Label", first.resolve("a") instanceof Label);
    }

    @Test
    public void testInnerBlockDeclaredAfter() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  begin");
        c.line("    goto a");
        c.line("  end;");
        c.line("  a:");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
    }

    @Test
    public void testInnerBlockDeclaredBefore() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  a:;");
        c.line("  begin");
        c.line("    goto a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
    }

    @Test
    public void testInnerBlockUsedBefore() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  goto a;");
        c.line("  begin");
        c.line("    a:");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(2, SymbolNotDeclaredException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testInnerBlockUsedAfter() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  begin");
        c.line("    a:");
        c.line("  end;");
        c.line("  goto a");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(5, SymbolNotDeclaredException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testRedeclaration() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  a:;");
        c.line("  a:;");
        c.line("  begin");
        c.line("    a:;");
        c.line("    a:");
        c.line("  end;");
        c.line("  b:");
        c.line("end");

        Result result = checkSemantics(c);
        Set<Integer> lines = getLines(result.exceptions);
        assertEquals("There should be 2 exceptions", 2, result.exceptions.size());
        assertTrue("There should be an exception at line 3", lines.contains(3));
        assertTrue("There should be an exception at line 6", lines.contains(6));
    }

    @Test
    public void testGotoNotALabel() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  goto a");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }
}
