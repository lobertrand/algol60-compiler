package eu.telecomnancy.symbols;

import eu.telecomnancy.codegen.UniqueReference;
import java.util.List;
import java.util.stream.Collectors;

public class Procedure extends Symbol {

    private SymbolTable symbolTable;
    private List<Type> parameterTypes;
    private String asmLabel;

    public Procedure(String identifier, Type returnType, List<Type> parameterTypes) {
        super(identifier, returnType, Kind.PROCEDURE);
        this.parameterTypes = parameterTypes;
        this.asmLabel = UniqueReference.forLabel(identifier);
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

    public String getAsmLabel() {
        return asmLabel;
    }

    @Override
    public String toString() {
        String parameters =
                parameterTypes.stream().map(Type::toString).collect(Collectors.joining(", "));
        return String.format(
                "Procedure: %s %s(%s) %s", getType(), getIdentifier(), parameters, modeToString());
    }
}
