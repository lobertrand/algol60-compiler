import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.junit.Test;

public class Algol60ParserTest {

    private void parse(String content) throws RecognitionException {
        ANTLRStringStream stream = new ANTLRStringStream(content);
        Algol60Lexer lexer = new Algol60Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.prog();
    }

    @Test
    public void testVarDecThreeIdentifiers() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  integer alpha, beta, gamma\n";
        content += "end\n" + "";
        parse(content);
    }

    @Test(expected = RecognitionException.class)
    public void testVarDecNoIdentifier() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  integer\n";
        content += "end\n" + "";
        parse(content);
    }

    @Test
    public void testVarDecOneIdentifier() throws RecognitionException {
        String content = "";
        content += "begin\n";
        content += "  string alpha\n";
        content += "end\n" + "";
        parse(content);
    }
}
