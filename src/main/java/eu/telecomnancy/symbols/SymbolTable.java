package eu.telecomnancy.symbols;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SymbolTable {

    private HashMap<String, Symbol> symbols;
    private SymbolTable parent;
    private List<SymbolTable> children;

    public SymbolTable() {
        symbols = new HashMap<>();
        parent = null;
        children = new ArrayList<>();
    }

    public void define(Symbol symbol) {
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public Symbol resolve(String identifier) {
        return symbols.get(identifier);
    }

    public SymbolTable getParent() {
        return parent;
    }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
    }

    public boolean hasParent() {
        return parent != null;
    }

    public List<SymbolTable> getChildren() {
        return children;
    }

    public void addChild(SymbolTable child) {
        children.add(child);
    }
}
