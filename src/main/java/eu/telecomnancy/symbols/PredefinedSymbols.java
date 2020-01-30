package eu.telecomnancy.symbols;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class PredefinedSymbols {

    public static Set<Symbol> get() {
        HashSet<Symbol> result = new HashSet<>();

        result.add(new Procedure("outstring", Type.VOID, Arrays.asList(Type.INTEGER, Type.STRING)));
        result.add(
                new Procedure("outinteger", Type.VOID, Arrays.asList(Type.INTEGER, Type.INTEGER)));
        result.add(new Procedure("inreal", Type.VOID, Arrays.asList(Type.REAL, Type.REAL)));
        result.add(new Procedure("entier", Type.INTEGER, Arrays.asList(Type.REAL)));
        return result;
    }
}
