package eu.telecomnancy.symbols;

public enum Kind {
    PROCEDURE,
    VARIABLE,
    LABEL,
    UNDECLARED_LABEL,
    PARAMETER;

    @Override
    public String toString() {
        return name().toLowerCase();
    }

    public String withPronoun() {
        return (this == UNDECLARED_LABEL ? "an " : "a ") + name().toLowerCase();
    }

    public boolean isAssignable() {
        return this == VARIABLE || this == PARAMETER;
    }
}
