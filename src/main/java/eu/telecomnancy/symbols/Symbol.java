package eu.telecomnancy.symbols;

public abstract class Symbol {
    private String identifier;
    private Type type;
    private Kind kind;

    public Symbol(String idf, Type type, Kind kind) {
        this.identifier = idf;
        this.type = type;
        this.kind = kind;
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

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o) return true;
    //        if (o == null || getClass() != o.getClass()) return false;
    //        Symbol symbol = (Symbol) o;
    //        return Objects.equals(identifier, symbol.identifier) &&
    //                type == symbol.type &&
    //                kind == symbol.kind;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(identifier, type, kind);
    //    }
}
