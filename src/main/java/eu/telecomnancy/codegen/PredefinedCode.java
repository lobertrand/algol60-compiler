package eu.telecomnancy.codegen;

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
        asm.label("outstring", "fonction d'affichage (string)");
        asm.code("LDQ 0, R1", "R1 = taille données locales (ici 0) de fonction appelée");
        asm.code("ADQ -2, SP", "décrémente le pointeur de pile SP");
        asm.code("STW BP, (SP)", "sauvegarde le contenu du registre BP sur la pile");
        asm.code("LDW BP, SP", "charge contenu SP ds BP qui pointe sur sa sauvegarde");
        asm.code("SUB SP, R1, SP", "réserve R1 octets sur la pile pour la variable locale z");

        asm.comment("charge R0 avec le paramètre p de déplacement 4");
        asm.code("LDW R0, BP", "R0 = BP");
        asm.code("ADQ 4, R0", "R0 pointe sur p");
        asm.code("LDW R0, (R0)", "R0 = p = adresse du début du texte à afficher");

        asm.comment("affiche texte pointé par R0");
        asm.code("LDW WR, #WRITE_EXC", "on suppose que symbole WRITE_EXC déjà défini");
        asm.code("TRP WR", "lance trappe dont n° dans WR");

        asm.comment("fin de la fonction (épilogue) :");
        asm.comment("UNLINK");
        asm.code("LDW SP, BP", "charge SP avec contenu de BP: abandon infos locales");
        asm.code("LDW BP, (SP)", "charge BP avec ancien BP");
        asm.code("ADQ 2, SP", "ancien BP supprimé de la pile");
        asm.comment("RTS", "retour au programme appelant:");
        asm.code("LDW WR, (SP)", "charge WR avec l'adresse de retour");
        asm.code("ADQ 2, SP", "incrémente le pointeur de pile SP");
        asm.code("JEA (WR)", "saute à l'instruction d'adresse absolue dans WR");
    }
}
