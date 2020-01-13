package eu.telecomnancy.symbols;

import eu.telecomnancy.tools.StringTools;
import java.util.*;

public class SymbolTable {

    private Map<String, Symbol> symbols;
    private SymbolTable parent;
    private List<SymbolTable> children;
    private int tableNumber;
    private int level;

    private static int nextTableNumber = 0;

    public SymbolTable() {
        // LinkedHashMap keeps the keys in the order they are stored
        symbols = new LinkedHashMap<>();
        parent = null;
        children = new ArrayList<>();
        tableNumber = nextTableNumber++;
        level = 0;
    }

    public void define(Symbol symbol) {
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public boolean isDeclaredInScope(String idf) {
        boolean idfInScope = false;
        Symbol result = symbols.get(idf);
        if (result != null) {
            idfInScope = true;
        }
        return idfInScope;
    }

    public Symbol resolve(String identifier) {
        Symbol result = symbols.get(identifier);
        if (result != null) {
            return result;
        } else if (parent != null) {
            return parent.resolve(identifier);
        } else {
            return null;
        }
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

    public SymbolTable createChild() {
        SymbolTable child = new SymbolTable();
        child.level = this.level + 1;
        child.setParent(this);
        children.add(child);
        return child;
    }

    public int getLevel() {
        return level;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    @Override
    public String toString() {
        // Table header
        String header = "level: " + level + ", tableNumber: " + tableNumber;

        // Width of the table content
        int width = header.length();
        for (Symbol s : symbols.values()) {
            width = Math.max(s.toString().length(), width);
        }
        String line = StringTools.repeat("─", width);
        String indent = StringTools.repeat(" ", level * 4);

        // Making of the table
        StringBuilder res = new StringBuilder();
        res.append(String.format("%s┌─%s─┐\n", indent, line));
        res.append(String.format("%s│ %-" + width + "s │\n", indent, header));
        res.append(String.format("%s├─%s─┤\n", indent, line));
        for (Symbol s : symbols.values()) {
            res.append(String.format("%s│ %-" + width + "s │\n", indent, s));
        }
        res.append(String.format("%s└─%s─┘\n", indent, line));

        // Making of the children
        for (SymbolTable child : children) {
            res.append(child.toString());
        }
        return res.toString();
    }
}
