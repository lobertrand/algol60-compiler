package eu.telecomnancy.tools;

import org.antlr.runtime.tree.Tree;

public class ASTTools {

    private static void depthFirstSearch(Tree tree, String space) {
        System.out.println(space + tree.toString() + ": " + tree.getClass().getSimpleName());
        for (int i = 0; i < tree.getChildCount(); i++) {
            depthFirstSearch(tree.getChild(i), space + "  ");
        }
    }

    public static void depthFirstSearch(Tree tree) {
        depthFirstSearch(tree, "");
    }
}
