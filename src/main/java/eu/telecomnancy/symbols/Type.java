package eu.telecomnancy.symbols;

public enum Type {
    VOID,
    INTEGER,
    REAL,
    STRING;

    public static Type fromString(String typeToken) {
        switch (typeToken) {
            case "integer":
                return INTEGER;
            case "real":
                return REAL;
            case "string":
                return STRING;
            default:
                return null;
        }
    }

    public String withPronoun() {
        return (this == INTEGER ? "an " : "a ") + name().toLowerCase();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
