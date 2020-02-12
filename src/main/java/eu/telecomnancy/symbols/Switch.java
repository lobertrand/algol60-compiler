package eu.telecomnancy.symbols;

public class Switch extends Symbol {

    public Switch(String idf) {
        super(idf, Type.VOID, Kind.SWITCH);
    }

    @Override
    public String toString() {
        return String.format("Switch: %s", getIdentifier());
    }
}
