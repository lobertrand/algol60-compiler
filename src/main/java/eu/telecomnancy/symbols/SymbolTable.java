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
    private int currentVariableShift = -4;
    private int currentParameterShift = 4;
    private int localVariableSize = 0;
    private int parameterSize = 0;

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
        if (symbol.getKind() == Kind.VARIABLE || symbol.getKind() == Kind.ARRAY) {
            if (symbol.isParameter()) {
                symbol.setShift(this.currentParameterShift);
                this.currentParameterShift -= symbol.getSize();
            } else {
                symbol.setShift(this.currentVariableShift);
                this.currentVariableShift -= symbol.getSize();

                localVariableSize += symbol.getSize();
            }
        }
        symbols.put(symbol.getIdentifier(), symbol);
    }

    public void setParameterSize(int parameterSize) {
        this.parameterSize = parameterSize;
        this.currentParameterShift = parameterSize + 2;
    }

    public void setParent(SymbolTable parent) {
        this.parent = parent;
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

    public SymbolTable whereIsDeclared(String identifier) {
        Symbol result = symbols.get(identifier);
        if (result != null) {
            return this;
        } else if (parent != null) {
            return parent.whereIsDeclared(identifier);
        } else {
            return null;
        }
    }

    public SymbolTable whereIsDeclared(String identifier, Class<?> clazz) {
        Symbol result = symbols.get(identifier);
        if (result != null && result.getClass() == clazz) {
            return this;
        } else if (parent != null) {
            return parent.whereIsDeclared(identifier, clazz);
        } else {
            return null;
        }
    }

    @SuppressWarnings("unchecked")
    public <T extends Symbol> T resolve(String identifier, Class<T> clazz) {
        Symbol result = symbols.get(identifier);
        if (result != null && result.getClass() == clazz) {
            return (T) result;
        } else if (parent != null) {
            return parent.resolve(identifier, clazz);
        } else {
            return null;
        }
    }

    public Symbol resolve(String identifier, Kind kind) {
        Symbol result = symbols.get(identifier);
        if (result != null && result.getKind() == kind) {
            return result;
        } else if (parent != null) {
            return parent.resolve(identifier, kind);
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

    public SymbolTable getChildWithNumber(int n) {
        for (SymbolTable child : children) if (child.getTableNumber() == n) return child;
        throw new RuntimeException(
                "Symbol table #" + n + " is not a child of table #" + tableNumber);
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
