package eu.telecomnancy.symbols;

import static java.util.Arrays.*;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PredefinedSymbols {

    public static Set<Symbol> get() {
        HashSet<Symbol> result = new LinkedHashSet<>();
        result.add(new Procedure("outstring", Type.VOID, asList(Type.INTEGER, Type.STRING)));
        result.add(new Procedure("outboolean", Type.VOID, asList(Type.INTEGER, Type.BOOLEAN)));
        result.add(new Procedure("outinteger", Type.VOID, asList(Type.INTEGER, Type.INTEGER)));
        result.add(new Procedure("outreal", Type.VOID, asList(Type.INTEGER, Type.REAL)));
        result.add(new Procedure("inreal", Type.VOID, asList(Type.INTEGER, Type.REAL)));
        result.add(new Procedure("ininteger", Type.VOID, asList(Type.INTEGER, Type.INTEGER)));
        result.add(new Procedure("entier", Type.INTEGER, asList(Type.REAL)));

        return result;
    }
}
