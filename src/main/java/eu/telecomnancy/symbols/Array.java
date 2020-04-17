package eu.telecomnancy.symbols;

import eu.telecomnancy.codegen.UniqueReference;
import java.util.List;
import java.util.stream.Collectors;

public class Array extends Symbol {
    private String asmLabel;
    private int size;

    public String getAsmLabel() {
        return asmLabel;
    }

    public Array withAsmLabel(UniqueReference uniqueReference) {
        this.asmLabel = uniqueReference.forLabel(getIdentifier());
        return this;
    }

    private List<Range> ranges;

    public Array(String idf, Type type, List<Range> r) {
        super(idf, type, Kind.ARRAY);
        ranges = r;
        size = 2 + r.size() * 4;
    }

    public List<Range> getRanges() {
        return ranges;
    }

    public String toString() {
        String rangesStr = ranges.stream().map(Range::toString).collect(Collectors.joining(", "));
        return String.format(
                "Array: %s %s[%s] %s %d",
                getType(), getIdentifier(), rangesStr, modeToString(), getShift());
    }

    public static class Range {
        final Integer start;
        final Integer end;

        public Range(Integer start, Integer end) {
            this.start = start;
            this.end = end;
        }

        public boolean isInRange(int n) {
            boolean inRange;
            if (start == null && end == null) {
                inRange = true;
            } else if (start == null) {
                inRange = n <= end;
            } else if (end == null) {
                inRange = n >= start;
            } else {
                inRange = (n >= start && n <= end);
            }
            return inRange;
        }

        public Integer getStart() {
            return start;
        }

        public Integer getEnd() {
            return end;
        }

        @Override
        public String toString() {
            return (start == null ? "?" : start) + ":" + (end == null ? "?" : end);
        }
    }

    @Override
    public int getSize() {
        return size;
    }
}
