package eu.telecomnancy.symbols;

import eu.telecomnancy.ast.DefaultAST;

public class OrphanSwitch {

    private DefaultAST tree;
    private SymbolTable symbolTable;
    private Switch aSwitch;

    public OrphanSwitch(Switch aSwitch, SymbolTable symbolTable, DefaultAST tree) {
        this.tree = tree;
        this.symbolTable = symbolTable;
        this.aSwitch = aSwitch;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public DefaultAST getTree() {
        return tree;
    }

    @Override
    public String toString() {
        return String.format("Orphan switch: %s (line %d)", aSwitch, tree.getLine());
    }
}
