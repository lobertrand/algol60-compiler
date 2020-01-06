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
                return VOID;
        }
    }
}
