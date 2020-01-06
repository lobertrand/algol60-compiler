package eu.telecomnancy.symbols;

public class Label extends Symbol {

    public Label(String idf) {
        super(idf, Type.VOID, Kind.LABEL);
    }

    @Override
    public String toString() {
        return String.format("Label: %s", getIdentifier());
    }
}
