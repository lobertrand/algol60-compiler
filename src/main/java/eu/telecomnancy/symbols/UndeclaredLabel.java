package eu.telecomnancy.symbols;

public class UndeclaredLabel extends Symbol {

    private int line;

    public UndeclaredLabel(String idf, int line) {
        super(idf, Type.VOID, Kind.LABEL);
        this.line = line;
    }

    public int getLine() {
        return line;
    }

    @Override
    public String toString() {
        return String.format("UndeclaredLabel: %s (line %d)", getIdentifier(), line);
    }

    //    @Override
    //    public boolean equals(Object o) {
    //        if (this == o) return true;
    //        if (o == null || getClass() != o.getClass()) return false;
    //        if (!super.equals(o)) return false;
    //        UndeclaredLabel that = (UndeclaredLabel) o;
    //        return line == that.line;
    //    }
    //
    //    @Override
    //    public int hashCode() {
    //        return Objects.hash(super.hashCode(), line);
    //    }
}
