package eu.telecomnancy.symbols;

public abstract class Symbol {
    private String identifier;
    private Type type;
    private Kind kind;
    private Mode mode;

    public Symbol(String idf, Type type, Kind kind) {
        this.identifier = idf;
        this.type = type;
        this.kind = kind;
        this.mode = null;
    }

    public boolean isParameter() {
        return mode == Mode.VALUE || mode == Mode.REFERENCE;
    }

    public boolean isResultValue() {
        return mode == Mode.PROCEDURE_RESULT;
    }

    public Mode getMode() {
        return mode;
    }

    public void setMode(Mode mode) {
        this.mode = mode;
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

    protected String modeToString() {
        if (mode == null) return "";
        return mode.toString();
    }

    public enum Mode {
        PROCEDURE_RESULT("proc. result"),
        VALUE("value param"),
        REFERENCE("ref. param");

        String description;

        Mode(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "(" + description + ")";
        }
    }
}
