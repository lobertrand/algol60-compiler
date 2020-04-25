package eu.telecomnancy.symbols;

import static java.util.Arrays.*;

import eu.telecomnancy.codegen.UniqueReference;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

public class PredefinedSymbols {

    public static Set<Symbol> create(UniqueReference uniqueReference) {
        HashSet<Symbol> result = new LinkedHashSet<>();
        result.add(
                new Procedure("outstring", Type.VOID, asList(Type.INTEGER, Type.STRING))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("outboolean", Type.VOID, asList(Type.INTEGER, Type.BOOLEAN))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("outinteger", Type.VOID, asList(Type.INTEGER, Type.INTEGER))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("outreal", Type.VOID, asList(Type.INTEGER, Type.REAL))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("inreal", Type.VOID, asList(Type.INTEGER, Type.REAL))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("ininteger", Type.VOID, asList(Type.INTEGER, Type.INTEGER))
                        .withAsmLabel(uniqueReference));
        result.add(
                new Procedure("entier", Type.INTEGER, asList(Type.REAL))
                        .withAsmLabel(uniqueReference));
        result.add(new Procedure("line", Type.VOID, asList()).withAsmLabel(uniqueReference));
        result.add(new Procedure("space", Type.VOID, asList()).withAsmLabel(uniqueReference));
        return result;
    }
}
