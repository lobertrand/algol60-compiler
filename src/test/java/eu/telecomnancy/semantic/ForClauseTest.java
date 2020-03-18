package eu.telecomnancy.semantic;

import static eu.telecomnancy.semantic.Helper.*;

import eu.telecomnancy.Content;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class ForClauseTest {

    /*
      for i := 1 step 1 until 10 do
    */

    @Test
    public void test_StepUntilDo_Simple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer index;");
        c.line("  for index := 45 step 1 until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StepUntilDo_RealInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real i;");
        c.line("  for i := 3.14 step 1 until 20 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StepUntilDo_ProcCallInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer procedure startIndex;");
        c.line("  begin");
        c.line("    startIndex := 3");
        c.line("  end;");
        c.line("  integer s;");
        c.line("  for s := startIndex() step 1 until 20 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StepUntilDo_StringStep() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step \"step\" until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void test_StepUntilDo_RealStep() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2.5 until 89 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StepUntilDo_RealUntil() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2 until 23.3 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_StepUntilDo_BooleanUntil() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 step 2 until true do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void test_StepUntilDo_MultipleErrors() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  for i := 0 step 2 < a until 4 * a >= 2 do");
        c.line("  begin");
        c.line("    integer a");
        c.line("  end");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolNotDeclaredException.class, result);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(3, result);
    }

    /*
      for i := 1 while i < 10 do
    */

    @Test
    public void test_WhileDo_Simple() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 0 while i < 10 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_WhileDo_RealInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real i;");
        c.line("  for i := 3.14 while i < 10 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_WhileDo_ProcCallInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer procedure p; begin p := 5 end;");
        c.line("  integer i;");
        c.line("  for i := p() while i < 10 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_WhileDo_WrongTypeWhile() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 1 while i + 2 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    @Test
    public void test_WhileDo_MultipleErrors() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  for i := 1 while a + 2 do");
        c.line("    a := true;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolNotDeclaredException.class, result);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(3, result);
    }

    @Test
    public void test_WhileDo_StringInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string i;");
        c.line("  for i := \"hello\" while true do");
        c.line("    i := \"ok\";");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }

    /*
      for i := 1, 2, 3 do
    */

    @Test
    public void test_Do_OneValue() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 1 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_Do_MultipleValues() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := 1, 2, 3 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_Do_RealValues() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  real i;");
        c.line("  for i := 1.0, 2.0, 3.0 do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_Do_ProcCallInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer procedure p; begin p := 5 end;");
        c.line("  integer i;");
        c.line("  for i := p(), 42, p() do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionQuantity(0, result);
    }

    @Test
    public void test_Do_WrongTypeValues() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer i;");
        c.line("  for i := \"hello\", 3, 3.5, true do");
        c.line("    i := i + 1;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(3, result);
    }

    @Test
    public void test_Do_MultipleErrors() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  integer a;");
        c.line("  for i := 1 do");
        c.line("    a := true;");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, SymbolNotDeclaredException.class, result);
        assertExceptionAtLine(4, TypeMismatchException.class, result);
        assertExceptionQuantity(2, result);
    }

    @Test
    public void test_Do_StringInit() throws RecognitionException {
        Content c = new Content();
        c.line("begin");
        c.line("  string i;");
        c.line("  for i := \"hello\" do");
        c.line("    i := \"ok\";");
        c.line("end");

        Result result = checkSemantics(c);
        assertExceptionAtLine(3, TypeMismatchException.class, result);
        assertExceptionQuantity(1, result);
    }
}
