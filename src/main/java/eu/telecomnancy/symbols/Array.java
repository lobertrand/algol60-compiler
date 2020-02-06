package eu.telecomnancy.symbols;

import java.util.List;
import java.util.stream.Collectors;

public class Array extends Symbol {

    private List<Range> ranges;

    public Array(String idf, Type type, List<Range> r) {
        super(idf, type, Kind.VARIABLE);
        ranges = r;
    }

    public String toString() {
        String rangesStr = ranges.stream().map(Range::toString).collect(Collectors.joining(", "));
        return String.format("Array: %s %s[%s] ", getType(), getIdentifier(), rangesStr);
    }

    public static class Range {
        final Integer start;
        final Integer end;

        public Range(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return (start == null ? "?" : start) + ":" + (end == null ? "?" : end);
        }
    }
}
