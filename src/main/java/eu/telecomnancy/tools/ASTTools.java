package eu.telecomnancy.tools;

import static eu.telecomnancy.tools.StringTools.*;

import eu.telecomnancy.Algol60Parser;
import org.antlr.runtime.tree.Tree;

public class ASTTools {

    private static void depthFirstSearch(Tree tree, String space) {
        String nodeType = Algol60Parser.tokenNames[tree.getType()];
        if (nodeType.equals(tree.toString())) {
            System.out.println(space + CYAN + tree.toString() + RESET);
        } else {
            System.out.println(space + tree.toString() + YELLOW + " : " + nodeType + RESET);
        }
        for (int i = 0; i < tree.getChildCount(); i++) {
            depthFirstSearch(tree.getChild(i), space + "  ");
        }
    }

    public static void depthFirstSearch(Tree tree) {
        depthFirstSearch(tree, "");
    }
}
