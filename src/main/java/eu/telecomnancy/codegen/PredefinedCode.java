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
    }

    public static void appendOutstringCode(Assembly asm) {
        asm.beginProcedureDeclaration();
        asm.label("outstring_", "fonction d'affichage (string)");
        asm.insert(IOUtils.loadString("/code/oustring_risc.asm"));
        asm.endProcedureDeclaration();
    }
}
