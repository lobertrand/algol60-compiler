package eu.telecomnancy.symbols;

import java.util.List;

public class Procedure extends Symbol {

    private SymbolTable symbolTable;
    private List<Type> parameterTypes;

    public Procedure(String identifier, Type returnType, List<Type> parameterTypes) {
        super(identifier, returnType, Kind.PROCEDURE);
        this.parameterTypes = parameterTypes;
    }

    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    @Override
    public String toString() {
        return String.format("Procedure: %s %s%s", getType(), getIdentifier(), parameterTypes);
    }
}
