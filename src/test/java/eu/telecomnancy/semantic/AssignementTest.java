package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class AssignementTest {

    @Test
    public void testSimple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a ;");
        c.line("a := 3");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 1 sub table", 1, subTables(result.symbolTable));
    }

    @Test
    public void testTypes() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a  ;");
        c.line("  a := 3 ;");
        c.line("  begin integer b;");
        c.line("    b := a ");
        c.line("  end");
        c.line("end");
        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());

        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));
    }

    @Test
    public void testWrongType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  begin");
        c.line("    real c;");
        c.line("    c := a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        Set<Integer> lines = getLines(result.exceptions);
        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("There should be an exception at line 5", lines.contains(5));
    }

    @Test
    public void test() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  string  s;");
        c.line("  s := \"coucou\" ;");
        c.line("  b := s ;");
        c.line("  begin");
        c.line("    real c;");
        c.line("    c := a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        Set<Integer> lines = getLines(result.exceptions);
        assertEquals("There should be 2 exceptions", 2, result.exceptions.size());
        assertTrue("There should be an exception at line 5", lines.contains(5));
        assertTrue("There should be an exception at line 8", lines.contains(8));
    }
}
