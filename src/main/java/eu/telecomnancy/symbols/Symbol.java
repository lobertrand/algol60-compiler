package eu.telecomnancy.symbols;

public abstract class Symbol {
    private String identifier;
    private Type type;
    private Kind kind;

    public Symbol(String idf, Type t) {
        this.identifier = idf;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }

    public Kind getKind() {
        return kind;
    }
}
