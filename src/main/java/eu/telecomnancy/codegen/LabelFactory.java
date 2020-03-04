package eu.telecomnancy.codegen;

import java.util.HashMap;
import java.util.Map;

public class LabelFactory {

    private static Map<String, Integer> usedNames = new HashMap<>();

    public static String fromName(String symbolName) {
        String labelName;
        if (usedNames.containsKey(symbolName)) {
            int n = usedNames.get(symbolName);
            labelName = symbolName + n;
            usedNames.put(symbolName, n + 1);
        } else {
            usedNames.put(symbolName, 1);
            labelName = symbolName;
        }
        return labelName;
    }
}
