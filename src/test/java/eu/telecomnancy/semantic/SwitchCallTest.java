package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class SwitchCallTest {

    @Test
    public void testSwitchBase() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  switch new := a,b,r ;");
        c.line("   a: ;");
        c.line(" b: ;");
        c.line(" r: ;");
        c.line(" goto new[1];");
        c.line(" v: ;");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void testSwitchIf() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("integer n ;");
        c.line(" switch new := a,b,r ;");
        c.line(" n := 3 ;");
        c.line("b: ;");
        c.line("r:;");
        c.line("if(n <2) then");
        c.line("begin");
        c.line("integer e;");
        c.line(" goto new[1];");
        c.line("end;");
        c.line("a: ;");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }
    @Test
    public void testSwitchUndeclared() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer n ;");
        c.line("  n := 3 ;");
        c.line("  goto new[1];");
        c.line("  if(n < 5) then");
        c.line("  begin");
        c.line("    switch new := b,r ;");
        c.line("    b: ;");
        c.line("    r:;");
        c.line("  end;");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(4, SymbolNotDeclaredException.class, result);
    }

    @Test
    public void testSwitchOutOfBound() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("switch s := ww,rt ;");
        c.line("    ww: ;");
        c.line("    rt:;");
        c.line("goto s[3];");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(5, OutOfBoundException.class, result);
    }

    @Test
    public void testSwitchWrongType() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("    integer a; ");
        c.line("switch s := a,b ;");
        c.line("    b:;");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
    }

    @Test
    public void testSwitchWrongIndex() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("switch sw := a,b ;");
        c.line("  y:;");
        c.line("goto  sw[y];");
        c.line("end");
        Result result = checkSemantics(c);
        assertExceptionQuantity(1, result);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
    }
}
