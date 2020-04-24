package eu.telecomnancy.symbols;

import eu.telecomnancy.codegen.UniqueReference;
import java.util.List;
import java.util.stream.Collectors;

public class Procedure extends Symbol {

    @Deprecated private SymbolTable symbolTable;
    private List<Type> parameterTypes;
    private String asmLabel;
    private Variable returnValue;

    public Procedure(String identifier, Type returnType, List<Type> parameterTypes) {
        super(identifier, returnType, Kind.PROCEDURE);
        this.parameterTypes = parameterTypes;
    }

    public int sizeOfParameters() {
        return parameterTypes.stream().mapToInt(Type::getSize).sum();
    }

    public boolean returnsAValue() {
        return getType() != Type.VOID;
    }

    public Variable getReturnValue() {
        return returnValue;
    }

    public void setReturnValue(Variable returnValue) {
        this.returnValue = returnValue;
    }

    @Deprecated
    public void setSymbolTable(SymbolTable symbolTable) {
        this.symbolTable = symbolTable;
    }

    @Deprecated
    public SymbolTable getSymbolTable() {
        return symbolTable;
    }

    public List<Type> getParameterTypes() {
        return parameterTypes;
    }

    public String getAsmLabel() {
        return asmLabel;
    }

    public Procedure withAsmLabel(UniqueReference uniqueReference) {
        this.asmLabel = uniqueReference.forLabel(getIdentifier());
        return this;
    }

    @Override
    public String toString() {
        String parameters =
                parameterTypes.stream().map(Type::toString).collect(Collectors.joining(", "));
        return String.format(
                "Procedure: %s %s(%s) %s", getType(), getIdentifier(), parameters, modeToString());
    }
}
