package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eu.telecomnancy.symbols.Procedure;
import eu.telecomnancy.symbols.SymbolTable;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ProcDecTest {

    @Test
    public void testSimple() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p;");
        content.line("  begin");
        content.line("    integer a");
        content.line("  end");
        content.line("end");
        Result result = checkSemantics(content);

        System.out.println(result.symbolTable);

        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertTrue("p should be a Procedure", first.resolve("p") instanceof Procedure);
    }
}
