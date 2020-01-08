package eu.telecomnancy.symbols;

public class UndeclaredLabel extends Symbol {

    public UndeclaredLabel(String idf) {
        super(idf, Type.VOID, Kind.LABEL);
    }

    @Override
    public String toString() {
        return String.format("Label: %s", getIdentifier());
    }
}
