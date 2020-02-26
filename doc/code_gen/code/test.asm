
// 6.2. Programme d'affichage résultant en langage d'assemblage CISC

EXIT_EXC    equ 64        // n° d'exception de EXIT
READ_EXC    equ 65        // n° d'exception de READ (lit 1 ligne)
WRITE_EXC   equ 66        // n° d'exception de WRITE (affiche texte)
STACK_ADRS  equ 0x1000    // base de pile en 1000h (par exemple)
LOAD_ADRS   equ 0xF000    // adresse de chargement de l'exécutable
NIL         equ 0         // fin de liste: contenu initial de BP

// ces alias permettront de changer les réels registres utilisés
SP          equ R15       // alias pour R15: Stack Pointer (pointeur de pile)
WR          equ R14       // alias pour R14: Work Register (reg. de travail)
BP          equ R13       // alias pour R13: frame Base Pointer (point. envir.)
                          // R12, R11 réservés
                          // R0 pour résultat de fonction
                          // R1 ... R10 disponibles

// à placer pour un programme ASM complet
ORG         LOAD_ADRS     // adresse de chargement
START       main_         // adresse de démarrage

// la fonction print peut être prédéfinie ici ou à la fin du fichier

// PROGRAMME PRINCIPAL

// void main(void)
// {

// CHAINES DE CARACTERES CONSTANTES DE MAIN

// définies juste avant le point d'entrée main_
STRING0     string "Hello world !\n"      // place la chaîne terminée par NUL
STRING1     string "Il fait beau ...\n"   // place la chaîne terminée par NUL

//
// PROGRAMME PRINCIPAL
//

main_ 
    LDW SP, #STACK_ADRS   // charge SP avec STACK_ADRS
    LDQ NIL, BP           // charge BP avec NIL=0

// prépare l'environnement (frame) du programme principal qui n'a aucune variable;
    LDQ 0, R1             // R1 = taille données locales prog. principal: rien ici

    // LINK (R1)          // crée et lie l'environnement du prog. principal (n'existe pas sur APR)
    ADQ -2, SP            // décrémente le pointeur de pile SP
    STW BP, (SP)          // sauvegarde le contenu du registre BP sur la pile
    LDW BP, SP            // charge contenu SP ds BP qui pointe sur sa sauvegarde
    SUB SP, R1, SP        // réserve R1 octets sur la pile pour variables locales

// print("Hello world !");

// empile les paramètres de la fonction
    LDW R1, #STRING0      // charge adresse de la chaîne n°0 dans R1
    STW R1, -(SP)         // empile paramètre p = STRING0 contenu dans R1 :

// appelle la fonction print d'adresse print_ :
    JSR @print_           // appelle la fonction d'adresse print_:

// print("Il fait beau ...");

// empile les paramètres de la fonction
    LDW R1, #STRING1      // charge adresse de la chaîne n°0 dans R1
    STW R1, -(SP)         // empile paramètre p = STRING0 contenu dans R1 :

// appelle la fonction print d'adresse print_ :
    JSR @print_           // appelle la fonction d'adresse print_:

// } accolade fermante de la définition du programme principal main
    // UNLINK
    LDW SP, BP            // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)          // charge BP avec ancien BP
    ADQ 2, SP             // ancien BP supprimé de la pile

// nécessaire sur le simulateur, hors mode "pas à pas" sinon le programme ne s'arrête pas ! :

// arrête le programme
    TRP #EXIT_EXC         // EXIT: arrête le programme
// gère le redémarrage du programme
    JEA @main_            // si on redemande l'exécution, saute à main_


//
// DÉFINITION DE FONCTIONS
//

// prépare l'environnement de la fonction appelée (prologue) :
print_ 
    LDQ 0, R1             // R1 = taille données locales (ici 0) de fonction appelée
    // LINK (R1)          // crée et lie l'environnement de fonction appelée
    ADQ -2, SP            // décrémente le pointeur de pile SP
    STW BP, (SP)          // sauvegarde le contenu du registre BP sur la pile
    LDW BP, SP            // charge contenu SP ds BP qui pointe sur sa sauvegarde
    SUB SP, R1, SP        // réserve R1 octets sur la pile pour la variable locale z

// charge R0 avec le paramètre p de déplacement 4
    // LDW R0, (BP)4      // R0 = M[BP + 4]
    LDW R0, BP            // R0 = BP
    ADQ 4, R0             // R0 pointe sur p
    LDW R0, (R0)          // R0 = p = adresse du début du texte à afficher

// affiche texte pointé par R0
    // TRP WR, #WRITE_EXC // lance trappe n° WRITE_EXC: affiche texte d'adresse R0
    LDW WR, #WRITE_EXC    // on suppose que symbole WRITE_EXC déjà défini
    TRP WR                // lance trappe dont n° dans WR

// fin de la fonction (épilogue) :
    // UNLINK
    LDW SP, BP            // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)          // charge BP avec ancien BP
    ADQ 2, SP             // ancien BP supprimé de la pile
    // RTS                // retour au programme appelant:
    LDW WR, (SP)          // charge WR avec l'adresse de retour
    ADQ 2, SP             // incrémente le pointeur de pile SP
    JEA (WR)              // saute à l'instruction d'adresse absolue dans WR
