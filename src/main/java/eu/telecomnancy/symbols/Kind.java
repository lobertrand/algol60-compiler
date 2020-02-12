package eu.telecomnancy.symbols;

public enum Kind {
    PROCEDURE,
    VARIABLE,
    LABEL,
    ORPHAN_GOTO,
    ARRAY,
    PARAMETER,
    SWITCH;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public String withPronoun() {
        return (this == ORPHAN_GOTO ? "an " : "a ") + name().toLowerCase();
    }

    public boolean isAssignable() {
        return this == VARIABLE || this == PARAMETER;
    }
}
