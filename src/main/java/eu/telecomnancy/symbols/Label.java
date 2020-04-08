package eu.telecomnancy.symbols;

import eu.telecomnancy.codegen.UniqueReference;

public class Label extends Symbol {
    private String asmLabel;

    public Label(String idf) {
        super(idf, Type.VOID, Kind.LABEL);
    }

    public String getAsmLabel() {
        return asmLabel;
    }

    public Label withAsmLabel(UniqueReference uniqueReference) {
        this.asmLabel = uniqueReference.forLabel(getIdentifier());
        return this;
    }

    @Override
    public String toString() {
        return String.format("Label: %s", getIdentifier());
    }
}
