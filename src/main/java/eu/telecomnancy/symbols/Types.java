package eu.telecomnancy.symbols;

import static eu.telecomnancy.symbols.Type.*;

import java.util.*;

public final class Types {

    private static final Map<Type, Set<Type>> assignableTypes = new HashMap<>();
    private static final Map<Type, Set<Type>> arithmeticTypes = new HashMap<>();

    static {
        assignableTypes.put(REAL, setOf(INTEGER, REAL));
        assignableTypes.put(INTEGER, setOf(INTEGER));
        assignableTypes.put(STRING, setOf(STRING));
        assignableTypes.put(BOOLEAN, setOf(BOOLEAN));

        arithmeticTypes.put(REAL, setOf(INTEGER, REAL));
        arithmeticTypes.put(INTEGER, setOf(INTEGER, REAL));
    }

    private Types() {}

    /**
     * Tells if source type is not assignable to destination type
     *
     * @param dest Destination type (left part of the assignment)
     * @param src Source type (right part of the assignment)
     * @return true is the assignment is invalid
     */
    public static boolean cannotAssign(Type dest, Type src) {
        if (dest == UNDEFINED || src == UNDEFINED) return false;
        return !(assignableTypes.containsKey(dest) && assignableTypes.get(dest).contains(src));
    }

    /**
     * Tells if left and right types are incompatible for doing an arithmetic operation (with
     * operators such as '+', '-', '*', '/'...)
     *
     * @param left Left operand's type
     * @param right Right operand's type
     * @return true if the operation is invalid
     */
    public static boolean cannotDoArithmeticOperation(Type left, Type right) {
        if (left == UNDEFINED || right == UNDEFINED) return false;
        return !(arithmeticTypes.containsKey(left) && arithmeticTypes.get(left).contains(right));
    }

    /**
     * Tells if left and right types are incompatible for doing a relational operation (with
     * operators such as '>', '<', '=', '<=', '>='...)
     *
     * @param left Left operand's type
     * @param right Right operand's type
     * @return true if the operation is invalid
     */
    public static boolean cannotDoRelationalOperation(Type left, Type right) {
        if (left == UNDEFINED || right == UNDEFINED) return false;
        return !(arithmeticTypes.containsKey(left) && arithmeticTypes.get(left).contains(right));
    }

    /**
     * Tells if left and right types are incompatible for doing a boolean operation (with operators
     * such as '/\', '\/', '=>'...)
     *
     * @param left Left operand's type
     * @param right Right operand's type
     * @return true if the operation is invalid
     */
    public static boolean cannotDoLogicalOperation(Type left, Type right) {
        if (left == UNDEFINED || right == UNDEFINED) return false;
        return !(left == BOOLEAN && right == BOOLEAN);
    }

    /**
     * Tells if the type in parameter is compatible with the '~' (not) operator
     *
     * @param type Type of the expression
     * @return true if the operation is invalid
     */
    public static boolean cannotNegate(Type type) {
        if (type == UNDEFINED) return false;
        return type != BOOLEAN;
    }

    @SafeVarargs
    private static <T> Set<T> setOf(T... objects) {
        return new HashSet<>(Arrays.asList(objects));
    }
}
