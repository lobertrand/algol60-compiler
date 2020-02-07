package eu.telecomnancy.symbols;

import eu.telecomnancy.ast.DefaultAST;

public class OrphanGoto extends Symbol {

    private DefaultAST tree;
    private SymbolTable symbolTable;

    public OrphanGoto(String idf, SymbolTable symbolTable, DefaultAST tree) {
        super(idf, Type.VOID, Kind.ORPHAN_GOTO);
        this.tree = tree;
        this.symbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public DefaultAST getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return String.format("Orphan goto: %s (line %d)", getIdentifier(), tree.getLine());
    }
}
