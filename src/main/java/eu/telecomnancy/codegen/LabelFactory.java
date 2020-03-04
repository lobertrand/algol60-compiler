package eu.telecomnancy.codegen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LabelFactory {

    private static Map<String, Integer> usedNames = new HashMap<>();

    public static String fromName(String symbolName) {
        if (usedNames.containsKey(symbolName)) {
            int n = usedNames.get(symbolName);
            usedNames.put(symbolName, n + 1);
            return symbolName + '_' + n;
        } else {
            usedNames.put(symbolName, 1);
            return symbolName;
        }
    }

    public static List<String> getUsedNames() {
        return new ArrayList<>(usedNames.keySet());
    }
}
