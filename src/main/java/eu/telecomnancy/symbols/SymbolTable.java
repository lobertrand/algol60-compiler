package eu.telecomnancy.symbols;

import eu.telecomnancy.tools.StringTools;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class SymbolTable {

    private Map<String, Symbol> symbols;
    private SymbolTable parent;
    private List<SymbolTable> children;
    private int tableNumber;
    private int level;
    private AtomicInteger numberOfTables;
    private int currentVariableShift = 0;
    private int currentParameterShift = 0;
    /** This constructor is used to create the root of a SymbolTable tree */
    public SymbolTable() {
        // LinkedHashMap keeps the keys in the order they are stored
        symbols = new LinkedHashMap<>();
        children = new ArrayList<>();
        numberOfTables = new AtomicInteger(0);
        tableNumber = numberOfTables.getAndIncrement();
        parent = null;
        level = 0;
    }

    private SymbolTable(SymbolTable parent) {
        this.parent = parent;
        parent.children.add(this);
        symbols = new LinkedHashMap<>();
        children = new ArrayList<>();
        numberOfTables = parent.numberOfTables;
        level = parent.level + 1;
        tableNumber = numberOfTables.getAndIncrement();
    }

    public SymbolTable createChild() {
        return new SymbolTable(this);
    }

    public void define(Symbol symbol) {
        if (symbol.isParameter()) {
            this.currentParameterShift -= symbol.getShift();
            symbol.SetShift(this.currentParameterShift);
        } else {
            this.currentVariableShift += symbol.getShift();
            symbol.SetShift(this.currentVariableShift);
        }
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public boolean isDeclaredInScope(String idf) {
        return symbols.containsKey(idf);
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

    public Symbol resolveWithKind(String identifier, Kind kind) {
        Symbol result = symbols.get(identifier);
        if (result != null && result.getKind() == kind) {
            return result;
        } else if (parent != null) {
            return parent.resolveWithKind(identifier, kind);
        } else {
            return null;
        }
    }

    public Symbol resolveInScope(String identifier) {
        return symbols.get(identifier);
    }

    public SymbolTable getParent() {
        return parent;
    }

    public List<SymbolTable> getChildren() {
        return children;
    }

    public SymbolTable getChild(int i) {
        return i < children.size() ? children.get(i) : null;
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
