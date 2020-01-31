package eu.telecomnancy.symbols;

import static eu.telecomnancy.symbols.Type.*;

import java.util.*;

public final class Types {

    private static final Map<Type, Set<Type>> assignableTypes = new HashMap<>();
    private static final Map<Type, Set<Type>> operationTypes = new HashMap<>();

    static {
        assignableTypes.put(REAL, setOf(INTEGER, REAL));
        assignableTypes.put(INTEGER, setOf(INTEGER));
        assignableTypes.put(STRING, setOf(STRING));
        assignableTypes.put(BOOLEAN, setOf(BOOLEAN));

        operationTypes.put(REAL, setOf(INTEGER, REAL));
        operationTypes.put(INTEGER, setOf(INTEGER, REAL));
    }

    private Types() {}

    public static boolean cannotAssign(Type dest, Type src) {
        if (dest == UNDEFINED || src == UNDEFINED) return false;
        return !(assignableTypes.containsKey(dest) && assignableTypes.get(dest).contains(src));
    }

    public static boolean cannotDoOperation(Type left, Type right) {
        if (left == UNDEFINED || right == UNDEFINED) return false;
        return !(operationTypes.containsKey(left) && operationTypes.get(left).contains(right));
    }

    @SafeVarargs
    private static <T> Set<T> setOf(T... objects) {
        return new HashSet<>(Arrays.asList(objects));
    }
}
