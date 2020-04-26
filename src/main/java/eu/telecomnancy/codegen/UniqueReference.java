package eu.telecomnancy.codegen;

import java.util.HashMap;
import java.util.Map;

public final class UniqueReference {

    private Map<String, Integer> usedLabelNames = new HashMap<>();

    public String forLabel(String symbolName) {
        if (usedLabelNames.containsKey(symbolName)) {
            int n = usedLabelNames.get(symbolName);
            usedLabelNames.put(symbolName, n + 1);
            return symbolName + '_' + n;
        } else {
            usedLabelNames.put(symbolName, 1);
            return symbolName + '_';
        }
    }

    private Map<String, String> definedStrings = new HashMap<>();

    public String forString(String content) {
        return definedStrings.computeIfAbsent(content, c -> forLabel("STR"));
    }

    static class StringRef {
        boolean alreadyDefined;
    }
}
