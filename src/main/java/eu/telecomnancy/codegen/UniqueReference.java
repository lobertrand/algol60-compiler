package eu.telecomnancy.codegen;

import java.util.HashMap;
import java.util.Map;

public final class UniqueReference {

    private static Map<String, Integer> usedLabelNames = new HashMap<>();

    public static String forLabel(String symbolName) {
        if (usedLabelNames.containsKey(symbolName)) {
            int n = usedLabelNames.get(symbolName);
            usedLabelNames.put(symbolName, n + 1);
            return symbolName + '_' + n;
        } else {
            usedLabelNames.put(symbolName, 1);
            return symbolName;
        }
    }

    private static int stringNumber = 0;

    public static String forString() {
        return "STR_" + (stringNumber++);
    }
}
