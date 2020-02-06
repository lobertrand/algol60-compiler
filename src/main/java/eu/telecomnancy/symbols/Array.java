package eu.telecomnancy.symbols;

public class Array extends Symbol {

    private int start;
    private int end;

    public Array(String idf, Type type, Range r) {
        super(idf, type, Kind.VARIABLE);
        this.start = r.start;
        this.end = r.end;
    }

    public String toString() {
        return String.format("Array: %s %s ", getType(), getIdentifier());
    }

    private static class Range {
        final int start;
        final int end;

        private Range(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}
