package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eu.telecomnancy.symbols.Symbol;
import eu.telecomnancy.symbols.SymbolTable;
import eu.telecomnancy.symbols.Type;
import java.util.Set;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class VarDecTest {

    @Test
    public void testSimple() throws RecognitionException {
        String content = "begin integer a end";

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 1 sub table", 1, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
    }

    @Test
    public void testInnerBlock() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  begin real a, b end");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
        assertTrue("a should be declared in second scope", second.isDeclaredInScope("a"));
        assertTrue("b should be declared in second scope", second.isDeclaredInScope("b"));
    }

    @Test
    public void testTypes() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  begin real a; string b end");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        SymbolTable second = first.getChildren().get(0);
        assertTrue("a should be declared in first scope", first.isDeclaredInScope("a"));
        assertTrue("a should be declared in second scope", second.isDeclaredInScope("a"));
        assertTrue("b should be declared in second scope", second.isDeclaredInScope("b"));

        Symbol a1 = first.resolve("a");
        Symbol a2 = second.resolve("a");
        Symbol b = second.resolve("b");
        assertEquals("a in first scope should be an integer", a1.getType(), Type.INTEGER);
        assertEquals("a in second scope should be a real", a2.getType(), Type.REAL);
        assertEquals("b should be a string", b.getType(), Type.STRING);
    }

    @Test
    public void testRedeclaration() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  begin");
        c.line("    integer a;");
        c.line("    integer a");
        c.line("  end;");
        c.line("  integer b");
        c.line("end");

        Result result = checkSemantics(c);
        Set<Integer> lines = exceptionLines(result.exceptions);
        assertEquals("There should be 2 exceptions", 2, result.exceptions.size());
        assertTrue("There should be an exception at line 5", lines.contains(5));
        assertTrue("There should be an exception at line 7", lines.contains(7));
    }
}
