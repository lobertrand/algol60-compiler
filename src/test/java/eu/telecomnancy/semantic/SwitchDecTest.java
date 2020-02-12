package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;
import static org.junit.Assert.*;

import eu.telecomnancy.symbols.*;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class SwitchDecTest {
    @Test
    public void testOneLabel() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch b:= a;");
        c.line("  a:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testMultiLabel() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch e:= a,b,c,d;");
        c.line("  a:;");
        c.line("  b:;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testRedeclaration() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch a:= a,b,c,d;");
        c.line("  a:;");
        c.line("  b:;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolRedeclarationException.class, result);
        assertExceptionAtLine(2, TypeMismatchException.class, result);
        assertExceptionQuantity(2, result);
    }

    @Test
    public void testRedeclaration2() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  switch a:= b,c,d;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolRedeclarationException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testWrongType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer b;");
        c.line("  switch a:= b,c,d;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void testScope() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("    begin");
        c.line("        switch a:= c,d;");
        c.line("    end;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testScope2() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("switch a:= c,d;");
        c.line("    begin");
        c.line("         c:;");
        c.line("         d:");
        c.line("    end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(2, SymbolNotDeclaredException.class, result);
        assertExceptionQuantity(2, result);
    }

    @Test
    public void testUndeclaredLabel() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch f:= a,b,c,d,e;");
        c.line("  a:;");
        c.line("  b:;");
        c.line("  c:;");
        c.line("  d:");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(2, SymbolNotDeclaredException.class, result);
    }
}
