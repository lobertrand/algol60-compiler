package eu.telecomnancy.symbols;

import java.util.List;

public class Array extends Symbol {

    private List<Integer> starts;
    private List<Integer> ends;

    public Array(String idf, Type type, List<Range> r) {
        super(idf, type, Kind.VARIABLE);
        for (Range i : r) {
            starts.add(i.start);
            ends.add(i.end);
        }
    }

    public String toString() {
        return String.format("Array: %s %s ", getType(), getIdentifier());
    }

    public static class Range {
        final Integer start;
        final Integer end;

        public Range(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }
    }
}
