package eu.telecomnancy.codegen;

public class Assembly {
    public static final String INDENT = "    ";
    public static final int GUIDE = 24;
    public static final String LINE_SEPARATOR = "\n";

    private StringBuilder lines;

    public Assembly() {
        lines = new StringBuilder();
    }

    public void code(String code) {
        lines.append(INDENT).append(code);
        lines.append(LINE_SEPARATOR);
    }

    public void code(String code, String comment) {
        lines.append(String.format("%-" + GUIDE + "s%s", INDENT + code, " // " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void label(String label) {
        lines.append(LINE_SEPARATOR);
        lines.append(label);
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
        lines.append(INDENT);
        lines.append("// ");
        lines.append(code);
        lines.append(LINE_SEPARATOR);
    }

    public void comment(String code, String comment) {
        lines.append(String.format("%-" + GUIDE + "s%s", INDENT + "// " + code, " // " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void string(String constant, String value) {
        lines.append(INDENT);
        lines.append(String.format("%-10s string  %s", constant, '"' + value + '"'));
        lines.append(LINE_SEPARATOR);
    }

    public void string(String constant, String value, String comment) {
        lines.append(INDENT);
        lines.append(
                String.format(
                        "%-10s string  %-10s %s", constant, '"' + value + '"', "// " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public void equ(String constant, String value) {
        lines.append(INDENT);
        lines.append(String.format("%-10s equ     %s", constant, value));
        lines.append(LINE_SEPARATOR);
    }

    public void equ(String constant, String value, String comment) {
        lines.append(INDENT);
        lines.append(String.format("%-10s equ     %-10s %s", constant, value, "// " + comment));
        lines.append(LINE_SEPARATOR);
    }

    public Assembly newline() {
        lines.append(LINE_SEPARATOR);
        return this;
    }

    public void insert(Assembly other) {
        lines.append(other.toString());
    }

    @Override
    public String toString() {
        return String.join("\n", lines);
    }

    public static void main(String[] args) {
        Assembly a = new Assembly();

        a.equ("SP", "R15");
        a.equ("BP", "R13", "Comment");

        a.newline();

        a.string("NEWLINE", "\\n", "Ceci est un commentaire");
        a.string("OTHER", "other");

        a.label("print_", "Fonction d'affichage sur stdout");
        a.code("LDQ 0, R1", "R1 = taille données locales (ici 0) de fonction appelée");
        a.comment("LINK (R1)", "crée et lie l'environnement de fonction appelée");
        a.code("ADQ -2, SP", "décrémente le pointeur de pile SP");
        a.code("STW BP, (SP)", "sauvegarde le contenu du registre BP sur la pile");
        a.code("LDW BP, SP", "charge contenu SP ds BP qui pointe sur sa sauvegarde");
        a.code("SUB SP, R1, SP", "réserve R1 octets sur la pile pour la variable locale z");

        a.newline();

        a.comment("charge R0 avec le paramètre p de déplacement 4");
        a.comment("LDW R0, (BP)4", "R0 = M[BP + 4]");
        a.code("LDW R0, BP", "R0 = BP");
        a.code("ADQ 4, R0", "R0 pointe sur p");
        a.code("LDW R0, (R0)", "R0 = p = adresse du début du texte à afficher");

        System.out.println(a);
    }
}
