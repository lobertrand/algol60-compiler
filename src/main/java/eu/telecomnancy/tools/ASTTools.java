package eu.telecomnancy.tools;

import static eu.telecomnancy.tools.StringTools.*;

import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.tree.Tree;

public class ASTTools {

    private static void print(Tree tree, String space) {
        if (tree.getChildCount() == 0) {
            String nodeType = Algol60Parser.tokenNames[tree.getType()];
            System.out.println(space + tree.toString() + YELLOW + " : " + nodeType + RESET);
        } else {
            System.out.println(space + CYAN + tree.toString() + RESET);
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            print(tree.getChild(i), space + "  ");
        }
    }

    public static void print(Tree tree) {
        print(tree, "");
    }
}
