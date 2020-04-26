package eu.telecomnancy.codegen;

import eu.telecomnancy.tools.IOUtils;

public class PredefinedCode {

    public static final String OUTSTRING_ = "outstring_";
    public static final String OUTBOOLEAN_ = "outboolean_";
    public static final String OUTINTEGER_ = "outinteger_";
    public static final String OUTREAL_ = "outreal_";
    public static final String ENTIER_ = "entier_";
    public static final String ITOA = "itoa";
    public static final String LINE_ = "line_";
    public static final String SPACE_ = "space_";
    public static final String DIV_0 = "div0";
    public static final String INDEX_OOB = "index_oob";

    public static void require(String label, Assembly asm) {
        switch (label) {
            case OUTSTRING_:
                appendOutstringCode(asm);
                break;
            case OUTBOOLEAN_:
                appendOutbooleanCode(asm);
                break;
            case OUTINTEGER_:
            case OUTREAL_:
                appendOutintegerOrRealCode(asm);
                break;
            case ENTIER_:
                appendEntierCode(asm);
                break;
            case LINE_:
                appendLineCode(asm);
                break;
            case SPACE_:
                appendSpaceCode(asm);
                break;
            case DIV_0:
                appendDiv0Code(asm);
                break;
            case INDEX_OOB:
                appendIndexOutOfBoundsCode(asm);
                break;
            case ITOA:
                appendItoaCode(asm);
                break;
            default:
                break;
        }
    }

    public static void appendAliases(Assembly asm) {
        asm.equ("EXIT_EXC", "64", "n° d'exceptoin de EXIT");
        asm.equ("READ_EXC", "65", "n° d'exception de READ (lit 1 ligne)");
        asm.equ("WRITE_EXC", "66", "n° d'exception de WRITE (affiche 1 ligne)");
        asm.equ("STACK_ADRS", "0x1000", "base de pile en 1000h (par exemple)");
        asm.equ("LOAD_ADRS", "0xF000", "adresse de chargement de l'exécutable");
        asm.equ("HEAP_ADRS", "0x9000", "adresse de base du tas en 9000 h (par exemple)");

        asm.comment("Alias de registres");
        asm.equ("SP", "R15", "alias pour R15, pointeur de pile");
        asm.equ("WR", "R14", "Work Register (registre de travail)");
        asm.equ("BP", "R13", "frame Base Pointer (pointage environnement)");
        asm.equ("NUL", "0", "caractère NUL qui doit terminer une chaîne de caractères");
        asm.equ("HP", "R11", "alias for R11 is HP for the Heap Pointer");
        asm.equ("NULL", "0", "pointeur nul");
        asm.equ("NIL", "0", "fin de liste chaînée");
        asm.equ("ITOA_I", "4", "offset du paramètre i");
        asm.equ("ITOA_P", "6", "offset du paramètre p");
        asm.equ("ITOA_B", "8", "offset du paramètre b");
        asm.equ("ASCII_MINUS", "45", "code ASCII de -");
        asm.equ("ASCII_PLUS", "43", "code ASCII de +");
        asm.equ("ASCII_SP", "32", "code ASCII d'espace SP");
        asm.equ(
                "ASCII_0",
                "48",
                "code ASCII de zéro (les autres chiffres jusqu'à 9 suivent dans l'ordre)");
        asm.equ(
                "ASCII_A",
                "65",
                "code ASCII de A (les autres lettres jusqu'à Z suivent dans l'ordre alphabétique)");
        asm.string("DIV0", "Error: Division by zero");
        asm.string("OUTBOUND", "Error: Index out of bounds");
        asm.string("TRUE", "true");
        asm.string("FALSE", "false");
        asm.string("SPACE", " ");
    }

    private static void appendOutstringCode(Assembly asm) {
        if (asm.isLabelDefined(OUTSTRING_)) return;
        asm.beginProcedureDeclaration();
        asm.label(OUTSTRING_, "fonction d'affichage (string)");
        asm.insert(IOUtils.loadString("/code/oustring.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendItoaCode(Assembly asm) {
        if (asm.isLabelDefined(ITOA)) return;
        asm.beginProcedureDeclaration();
        asm.label(ITOA, "fonction de conversion (int to ascii)");
        asm.insert(IOUtils.loadString("/code/itoa.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendOutintegerOrRealCode(Assembly asm) {
        if (asm.isLabelDefined(OUTINTEGER_)) return;
        require(ITOA, asm);
        asm.beginProcedureDeclaration();
        asm.label(OUTINTEGER_, "print function (integer)");
        asm.code("NOP", "No operation (continue to outreal_ code)");
        asm.label(OUTREAL_, "print function (real)");
        asm.insert(IOUtils.loadString("/code/outinteger.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendLineCode(Assembly asm) {
        if (asm.isLabelDefined(LINE_)) return;
        asm.beginProcedureDeclaration();
        asm.label(LINE_, "fonction de retour à la ligne");
        asm.insert(IOUtils.loadString("/code/line.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendSpaceCode(Assembly asm) {
        if (asm.isLabelDefined(SPACE_)) return;
        asm.beginProcedureDeclaration();
        asm.label(SPACE_, "fonction imprimant un espace");
        asm.insert(IOUtils.loadString("/code/space.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendDiv0Code(Assembly asm) {
        if (asm.isLabelDefined(DIV_0)) return;
        asm.beginProcedureDeclaration();
        asm.label(DIV_0, "Erreur de division par 0");
        asm.insert(IOUtils.loadString("/code/div0.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendIndexOutOfBoundsCode(Assembly asm) {
        if (asm.isLabelDefined(INDEX_OOB)) return;
        asm.beginProcedureDeclaration();
        asm.label(INDEX_OOB, "Index out of bounds error message");
        asm.insert(IOUtils.loadString("/code/index_oob.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendOutbooleanCode(Assembly asm) {
        if (asm.isLabelDefined(OUTBOOLEAN_)) return;
        asm.beginProcedureDeclaration();
        asm.label(OUTBOOLEAN_, "print boolean");
        asm.insert(IOUtils.loadString("/code/outboolean.asm"));
        asm.endProcedureDeclaration();
    }

    private static void appendEntierCode(Assembly asm) {
        if (asm.isLabelDefined(ENTIER_)) return;
        asm.beginProcedureDeclaration();
        asm.label(ENTIER_, "partie entière");
        asm.insert(IOUtils.loadString("/code/entier.asm"));
        asm.endProcedureDeclaration();
    }
}
