package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.*;

import eu.telecomnancy.symbols.*;
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

        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertTrue("p should be a Procedure", first.resolve("p") instanceof Procedure);
    }

    @Test
    public void testRedeclaredParameters() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p(a,b);");
        content.line("  value a; integer a;real b;");
        content.line("  begin");
        content.line("    integer a");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);
        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Exception should be at line 5", result.exceptionAt(5));
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertTrue("p should be a Procedure", first.resolve("p") instanceof Procedure);
    }

    @Test
    public void testRedeclaredProcedureName() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p(a,b);");
        content.line("  value a; integer a;real b;");
        content.line("  begin");
        content.line("    integer p");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable firstTable = result.symbolTable.getChild(0);
        Symbol firstP = firstTable.resolveInScope("p");
        assertNotNull("p should be declared in first scope", firstP);
        assertSame("p should be a Procedure", firstP.getKind(), Kind.PROCEDURE);

        SymbolTable secondTable = firstTable.getChild(0);
        Symbol secondP = secondTable.resolveInScope("p");
        assertNotNull("p should be declared in second scope", secondP);
        assertSame("p should be a Variable", secondP.getKind(), Kind.VARIABLE);
    }

    @Test
    public void testRedeclaredProcedureNameReturn() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  real procedure p(a,b);");
        content.line("  value a; integer a; real b;");
        content.line("  begin");
        content.line("    integer p ; ");
        content.line("    p := 3.3");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);
        assertEquals("There should be an exception", 1, result.exceptions.size());
        assertTrue("There should be an exception at line 5", result.exceptionAt(5));
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertTrue("p should be a Procedure", first.resolve("p") instanceof Procedure);
    }

    @Test
    public void testMultipleProc() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p(a,b);");
        content.line("  value a; integer a;real b;");
        content.line("  begin");
        content.line("    procedure k;");
        content.line("    begin");
        content.line("      integer l");
        content.line("    end");
        content.line("  end");
        content.line("end");
        Result result = checkSemantics(content);

        assertTrue("There should be no exception", result.exceptions.isEmpty());
        assertEquals("There should be 3 sub table", 3, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChildren().get(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertTrue("p should be a Procedure", first.resolve("p") instanceof Procedure);
    }

    @Test
    public void testSpecMismatch() throws RecognitionException {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p(a);");
        content.line("  value a; integer a, c;");
        content.line("  begin");
        content.line("    real x");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be an exception", 1, result.exceptions.size());
        assertTrue("There should be an exception at line 3", result.exceptionAt(3));
        assertEquals("There should be 2 sub table", 2, subTables(result.symbolTable));

        SymbolTable first = result.symbolTable.getChild(0);
        assertTrue("p should be declared in first scope", first.isDeclaredInScope("p"));
        assertSame("p should be a Procedure", first.resolve("p").getKind(), Kind.PROCEDURE);

        SymbolTable second = first.getChild(0);
        assertTrue("x should be declared in second scope", second.isDeclaredInScope("x"));
        assertSame("x should be a Variable", second.resolve("x").getKind(), Kind.VARIABLE);
    }

    @Test
    public void testReturn() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line(" integer procedure p ;");
        content.line("  begin");
        content.line("    p := 2 ");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);
        assertTrue("There should be no exception", result.exceptions.isEmpty());
    }

    @Test
    public void testMissingReturn() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line(" integer procedure p ;");
        content.line("  begin");
        content.line("    real x   ");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be an exception", 1, result.exceptions.size());
    }

    @Test
    public void testVoidReturn() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  procedure p ;");
        content.line("  begin");
        content.line("    p := 1  ");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be an exception", 1, result.exceptions.size());
        assertTrue("There should be an exception at line 4", result.exceptionAt(4));
        assertTrue("Type mismatch", result.exceptions.get(0) instanceof TypeMismatchException);
    }

    @Test
    public void testNestedReturnsValid() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f(n);");
        content.line("  value n; integer n;");
        content.line("  begin");
        content.line("    if n <= 1 then");
        content.line("      if n = 1 then");
        content.line("        f := 1");
        content.line("      else");
        content.line("        f := 1");
        content.line("    else");
        content.line("      f := n * f(n-1)");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be no exception", 0, result.exceptions.size());
    }

    @Test
    public void testNestedReturnsMissingElse() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f(n);");
        content.line("  value n; integer n;");
        content.line("  begin");
        content.line("    if n <= 1 then");
        content.line("      if n = 1 then");
        content.line("        f := 1");
        content.line("    else");
        content.line("      f := n * f(n-1)");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Missing return", result.exceptions.get(0) instanceof MissingReturnException);
    }

    @Test
    public void testReturnMissingInThen() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f;");
        content.line("  begin");
        content.line("    if 5 > 3 then");
        content.line("      integer a");
        content.line("    else");
        content.line("      f := 2");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Missing return", result.exceptions.get(0) instanceof MissingReturnException);
    }

    @Test
    public void testReturnMissingInElse() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f;");
        content.line("  begin");
        content.line("    if 5 > 3 then");
        content.line("      f := 5");
        content.line("    else");
        content.line("      integer a");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Missing return", result.exceptions.get(0) instanceof MissingReturnException);
    }

    @Test
    public void testReturnMissingAutoAssignment() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f;");
        content.line("  begin");
        content.line("    if 5 > 3 then");
        content.line("      f := f");
        content.line("    else");
        content.line("      f := 2");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Missing return", result.exceptions.get(0) instanceof MissingReturnException);
    }

    @Test
    public void testReturnRecursiveAssignment() throws Exception {
        Content content = new Content();
        content.line("begin");
        content.line("  integer procedure f;");
        content.line("  begin");
        content.line("    if 5 > 3 then");
        content.line("      f := f()");
        content.line("    else");
        content.line("      f := 2");
        content.line("  end");
        content.line("end");

        Result result = checkSemantics(content);

        assertEquals("There should be 1 exception", 1, result.exceptions.size());
        assertTrue("Missing return", result.exceptions.get(0) instanceof MissingReturnException);
    }
}
