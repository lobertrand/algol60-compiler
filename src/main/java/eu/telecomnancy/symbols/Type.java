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
    INTEGER_ARRAY,
    REAL_ARRAY,
    STRING_ARRAY,
    BOOLEAN_ARRAY,
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
            case "integer_array":
                return INTEGER_ARRAY;
            case "real_array":
                return REAL_ARRAY;
            case "string_array":
                return STRING_ARRAY;
            case "boolean_array":
                return BOOLEAN_ARRAY;
            default:
                return UNDEFINED;
        }
    }

    public boolean isArrayType() {
        switch (this) {
            case INTEGER_ARRAY:
            case REAL_ARRAY:
            case STRING_ARRAY:
            case BOOLEAN_ARRAY:
            case UNDEFINED:
                return true;
            default:
                return false;
        }
    }

    public Type toArrayContentType() {
        switch (this) {
            case INTEGER_ARRAY:
                return INTEGER;
            case REAL_ARRAY:
                return REAL;
            case STRING_ARRAY:
                return STRING;
            case BOOLEAN_ARRAY:
                return BOOLEAN;
            case UNDEFINED:
                return UNDEFINED;
            default:
                throw new UnsupportedOperationException(this + " is not an array type");
        }
    }

    public Type toArrayType() {
        switch (this) {
            case INTEGER:
                return INTEGER_ARRAY;
            case REAL:
                return REAL_ARRAY;
            case STRING:
                return STRING_ARRAY;
            case BOOLEAN:
                return BOOLEAN_ARRAY;
            case UNDEFINED:
                return UNDEFINED;
            default:
                throw new UnsupportedOperationException(this + " is not an array content type");
        }
    }

    public String withPronoun() {
        return pronoun() + " " + name().toLowerCase().replace('_', ' ');
    }

    private String pronoun() {
        switch (this) {
            case INTEGER:
            case INTEGER_ARRAY:
                return "an";
            case UNDEFINED:
                return "";
            default:
                return "a";
        }
    }

    @Override
    public String toString() {
        return name().toLowerCase().replace('_', ' ');
    }
}
