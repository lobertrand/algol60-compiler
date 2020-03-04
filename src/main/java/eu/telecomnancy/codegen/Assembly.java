package eu.telecomnancy.codegen;

public class Assembly {
    public static final String INDENT = "    ";
    public static final String LINE_SEPARATOR = "\n";

    private StringBuilder lines;

    public Assembly() {
        lines = new StringBuilder();
    }

    public void code(String code, String comment) {
        lines.append(String.format("%-24s%s", INDENT + code, " // " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void label(String label, String comment) {
        lines.append(LINE_SEPARATOR);
        lines.append(label);
        lines.append(" // ");
        lines.append(comment);
        lines.append(LINE_SEPARATOR);
    }

    public void comment(String code) {
        lines.append(LINE_SEPARATOR);
        lines.append(INDENT);
        lines.append("// ");
        lines.append(code);
        lines.append(LINE_SEPARATOR);
    }

    public void comment(String code, String comment) {
        lines.append(String.format("%-24s%s", INDENT + "// " + code, " // " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void string(String constant, String value, String comment) {
        lines.append(INDENT);
        String quotedStr = '"' + value + '"';
        String pattern = "%-10s string  %-10s %s";
        lines.append(String.format(pattern, constant, quotedStr, "// " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void equ(String constant, String value, String comment) {
        lines.append(INDENT);
        lines.append(String.format("%-10s equ     %-10s %s", constant, value, "// " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void def(String key, String value, String comment) {
        lines.append(INDENT);
        lines.append(String.format("%-10s %-18s %s", key, value, "// " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void newline() {
        lines.append(LINE_SEPARATOR);
    }

    public void insert(Assembly other) {
        lines.append(other.toString());
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }

    public static Assembly exampleWrite(String str) {
        Assembly asm = new Assembly();

        asm.equ("EXIT_EXC", "64", "n° d'exceptoin de EXIT");
        asm.equ("READ_EXC", "65", "n° d'exception de READ (lit 1 ligne)");
        asm.equ("WRITE_EXC", "66", "n° d'exception de WRITE (affiche 1 ligne)");
        asm.equ("STACK_ADRS", "0x1000", "base de pile en 1000h (par exemple)");
        asm.equ("LOAD_ADRS", "0xF000", "adresse de chargement de l'exécutable");

        asm.newline();
        asm.equ("SP", "R15", "alias pour R15, pointeur de pile");
        asm.equ("WR", "R14", "Work Register (registre de travail)");
        asm.equ("BP", "R13", "frame Base Pointer (pointage environnement)");

        asm.newline();
        asm.def("ORG", "LOAD_ADRS", "adresse de chargement");
        asm.def("START", "main_", "adresse de démarrage");

        asm.newline();
        asm.string("VALUE", str, "valeur à imprimer");

        asm.label("main_", "début du programme");

        asm.code("LDW R0, #VALUE", "place le string dans R0");
        asm.code("LDQ WRITE_EXC, WR", "place la trappe WRITE dans WR");
        asm.code("TRP WR", "lance la trappe WRITE");

        //        asm.code("TRP #READ_EXC", "lance la trappe READ");
        //        asm.code("TRP #WRITE_EXC", "lance la trappe WRITE");

        asm.newline();
        asm.code("TRP #EXIT_EXC", "arrete le programme");
        return asm;
    }
}
