package eu.telecomnancy.symbols;

public class Variable extends Symbol {

    public Variable(String idf, Type type) {
        super(idf, type, Kind.VARIABLE);
    }

    @Override
    public String toString() {
        return "Variable{" + "identifier=" + getIdentifier() + ", type=" + getType() + '}';
    }
}
