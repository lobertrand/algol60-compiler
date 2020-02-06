package eu.telecomnancy.symbols;

import org.antlr.runtime.tree.Tree;

public class OrphanGoto extends Symbol {

    private Tree tree;
    private SymbolTable symbolTable;

    public OrphanGoto(String idf, SymbolTable symbolTable, Tree tree) {
        super(idf, Type.VOID, Kind.ORPHAN_GOTO);
        this.tree = tree;
        this.symbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public Tree getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return String.format("Orphan goto: %s (line %d)", getIdentifier(), tree.getLine());
    }
}
