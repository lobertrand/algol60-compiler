package eu.telecomnancy.symbols;

import java.util.ArrayList;
import java.util.List;

public class Switch extends Symbol {
    private List<String> names;

    public Switch(String idf) {
        super(idf, Type.VOID, Kind.SWITCH);
        this.names = new ArrayList<>();
    }

    @Override
    public String toString() {
        String values = String.join(", ", names);
        return String.format("Switch: %s [%s]", getIdentifier(), values);
    }

    public void add(String string) {
        this.names.add(string);
    }

    public int getSize() {
        return names.size();
    }

    public List<String> getNames() {
        return names;
    }
}
