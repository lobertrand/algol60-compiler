package eu.telecomnancy.symbols;

@Deprecated
public class Parameter extends Symbol {

    private boolean byValue;

    public Parameter(String idf, Type type) {
        super(idf, type, Kind.PARAMETER);
        byValue = true;
    }

    public void setByValue(boolean byValue) {
        this.byValue = byValue;
    }

    public boolean isByValue() {
        return byValue;
    }

    @Override
    public String toString() {
        return String.format(
                "Parameter: %s %s (%s)",
                getType(), getIdentifier(), byValue ? "value" : "reference");
    }
}
