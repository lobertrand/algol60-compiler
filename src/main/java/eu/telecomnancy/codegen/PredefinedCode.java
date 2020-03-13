package eu.telecomnancy.codegen;

import eu.telecomnancy.tools.IOUtils;

public class PredefinedCode {

    public static void appendAliases(Assembly asm) {
        asm.equ("EXIT_EXC", "64", "n° d'exceptoin de EXIT");
        asm.equ("READ_EXC", "65", "n° d'exception de READ (lit 1 ligne)");
        asm.equ("WRITE_EXC", "66", "n° d'exception de WRITE (affiche 1 ligne)");
        asm.equ("STACK_ADRS", "0x1000", "base de pile en 1000h (par exemple)");
        asm.equ("LOAD_ADRS", "0xF000", "adresse de chargement de l'exécutable");

        asm.comment("Alias de registres");
        asm.equ("SP", "R15", "alias pour R15, pointeur de pile");
        asm.equ("WR", "R14", "Work Register (registre de travail)");
        asm.equ("BP", "R13", "frame Base Pointer (pointage environnement)");
        asm.equ("NUL", "0", "caractère NUL qui doit terminer une chaîne de caractères");
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
    }

    public static void appendOutstringCode(Assembly asm) {
        asm.beginProcedureDeclaration();
        asm.label("outstring_", "fonction d'affichage (string)");
        asm.insert(IOUtils.loadString("/code/oustring_risc.asm"));
        asm.endProcedureDeclaration();
    }

    public static void appendItoaCode(Assembly asm) {
        asm.beginProcedureDeclaration();
        asm.label("itoa_", "fonction de conversion (int to ascii)");
        asm.insert(IOUtils.loadString("/code/itoa_cisc.asm"));
        asm.endProcedureDeclaration();
    }

    public static void appendOutintegerCode(Assembly asm) {
        asm.beginProcedureDeclaration();
        asm.label("outinteger_", "fonction d'affichage (string)");
        asm.insert(IOUtils.loadString("/code/outinteger_cisc.asm"));
        asm.endProcedureDeclaration();
    }

    public static void appendLineCode(Assembly asm) {
        asm.beginProcedureDeclaration();
        asm.label("line_", "fonction de retour à la ligne");
        asm.insert(IOUtils.loadString("/code/line.asm"));
        asm.endProcedureDeclaration();
    }
}
