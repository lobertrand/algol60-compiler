package eu.telecomnancy.ast;

import eu.telecomnancy.Algol60Lexer;
import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.ANTLRStringStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.RecognitionException;
import org.antlr.runtime.tree.Tree;

public class Helper {

    public static Tree parse(String content) throws RecognitionException {
        ANTLRStringStream stream = new ANTLRStringStream(content);
        Algol60Lexer lexer = new Algol60Lexer(stream);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        parser.setTreeAdaptor(new ASTAdaptor());
        return parser.prog().getTree();
    }
}
