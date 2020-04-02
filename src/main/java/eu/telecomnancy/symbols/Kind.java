package eu.telecomnancy.symbols;

public enum Kind {
    PROCEDURE,
    VARIABLE,
    LABEL,
    ARRAY,
    SWITCH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public String withPronoun() {
        return (this == ARRAY ? "an " : "a ") + name().toLowerCase();
    }
}
