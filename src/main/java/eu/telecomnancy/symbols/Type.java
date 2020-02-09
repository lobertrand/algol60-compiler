package eu.telecomnancy.symbols;

public enum Type {
    VOID,
    INTEGER,
    REAL,
    STRING,
    BOOLEAN,
    /**
     * Use during semantic analysis, along with and exception, when a type is not specified. It
     * avoids redundant exceptions in other statements. Every type is considered compatible with
     * UNDEFINED. Symbol tables shouldn't contain UNDEFINED variables if the code compiled
     * successfully.
     */
    UNDEFINED,
    ;

    public static Type fromString(String typeToken) {
        switch (typeToken) {
            case "integer":
                return INTEGER;
            case "real":
                return REAL;
            case "string":
                return STRING;
            case "boolean":
                return BOOLEAN;
            default:
                return UNDEFINED;
        }
    }

    public String withPronoun() {
        return (this == INTEGER ? "an " : this == UNDEFINED ? "" : "a ") + name().toLowerCase();
    }

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
