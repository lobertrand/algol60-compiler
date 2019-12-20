package eu.telecomnancy;

import eu.telecomnancy.tools.IOUtils;
import org.antlr.runtime.ANTLRInputStream;
import org.antlr.runtime.CommonTokenStream;
import org.antlr.runtime.tree.Tree;

public class App {

    public static void main(String[] args) throws Exception {
        ANTLRInputStream input = new ANTLRInputStream(System.in);
        Algol60Lexer lexer = new Algol60Lexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        Algol60Parser parser = new Algol60Parser(tokens);
        Algol60Parser.prog_return pr = parser.prog();

        Tree tree = pr.getTree();
        IOUtils.generateDotTree(tree, "AST");

        // parcours(tree, "");
    }

    public static void parcours(Tree tree, String space) {
        System.out.println(space + tree.toString() + " : " + tree.getLine());
        for (int i = 0; i < tree.getChildCount(); i++) {
            parcours(tree.getChild(i), space + "  ");
        }
    }
}
