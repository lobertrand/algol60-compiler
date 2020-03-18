package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import eu.telecomnancy.Content;
import java.util.Set;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class AssignementTest {

    @Test
    public void testIntegerConstant() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  a := 3");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 1 sub table", 1, subTables(result.symbolTable));
    }

    @Test
    public void testIntegerConstantNested() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  a := 3;");
        c.line("  begin");
        c.line("    integer b;");
        c.line("    b := a");
        c.line("  end");
        c.line("end");
        Result result = checkSemantics(c);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub tables", 2, subTables(result.symbolTable));
    }

    @Test
    public void testSimpleBooleans() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  b := true;");
        c.line("  b := false;");
        c.line("  b := b");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be no exceptions", 0, result.exceptions.size());
    }

    @Test
    public void testBooleansFail() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  boolean b;");
        c.line("  b := 12;");
        c.line("  b := 12.5;");
        c.line("  b := \"str\";");
        c.line("  b := r;");
        c.line("  b := s;");
        c.line("  b := i");
        c.line("end");

        Result result = checkSemantics(c);
        assertEquals("There should be 6 exceptions", 6, result.exceptions.size());
        assertTrue("There should and exception at line 3", result.exceptionAt(3));
        assertTrue("There should and exception at line 4", result.exceptionAt(4));
        assertTrue("There should and exception at line 5", result.exceptionAt(5));
        assertTrue("There should and exception at line 6", result.exceptionAt(6));
        assertTrue("There should and exception at line 7", result.exceptionAt(7));
        assertTrue("There should and exception at line 8", result.exceptionAt(8));
    }

    @Test
    public void testRealIntegerType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  begin");
        c.line("    real c;");
        c.line("    c := a;");
        c.line("    a := c");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        Set<Integer> lines = getLines(result.exceptions);
        assertTrue("There should be an exception at line 6", lines.contains(6));
        assertEquals("There should be 1 exception", 1, result.exceptions.size());
    }

    @Test
    public void testWrongType2() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a, b;");
        c.line("  string  s;");
        c.line("  s := \"coucou\" ;");
        c.line("  b := s ;");
        c.line("  begin");
        c.line("    real c;");
        c.line("    c := a;");
        c.line("    a := c");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertTrue("There should be an exception at line 5", result.exceptionAt(5));
        assertTrue("There should be an exception at line 9", result.exceptionAt(9));
        assertEquals("There should be 2 exceptions", 2, result.exceptions.size());
    }
}
