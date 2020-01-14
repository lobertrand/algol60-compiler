package eu.telecomnancy.symbols;

import org.antlr.runtime.tree.Tree;

public class UndeclaredLabel extends Symbol {

    private Tree tree;

    public UndeclaredLabel(String idf, Tree tree) {
        super(idf, Type.VOID, Kind.LABEL);
        this.tree = tree;
    }

    public Tree getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return String.format("UndeclaredLabel: %s (line %d)", getIdentifier(), tree.getLine());
    }
}
