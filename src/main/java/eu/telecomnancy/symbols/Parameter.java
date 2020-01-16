package eu.telecomnancy.symbols;

public class Parameter extends Symbol {

    public Parameter(String idf, Type type) {
        super(idf, type, Kind.PARAMETER);
    }

    @Override
    public String toString() {
        return "Parameter: " + getType() + " " + getIdentifier();
    }
}
