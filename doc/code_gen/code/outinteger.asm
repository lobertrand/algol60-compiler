

//simple print, risc
EXIT_EXC    EQU 64          // n° d'exception de EXIT
READ_EXC    EQU 65          // n° d'exception de READ (lit 1 ligne)
WRITE_EXC   EQU 66          // n° d'exception de WRITE (affiche 1 ligne)
STACK_ADRS  EQU 0x1000      // base de pile en 1000h (par exemple)
LOAD_ADRS   EQU 0xF000      // adresse de chargement de l'exécutable

// ces alias permettront de changer les réels registres utilisés
SP          EQU R15         // alias pour R15, pointeur de pile
WR          EQU R14         // Work Register (registre de travail)
BP          EQU R13         // frame Base Pointer (pointage environnement)
                            // R12, R11 réservés
                            // R0 pour résultat de fonction
                            // R1 ... R10 disponibles


ORG         LOAD_ADRS       // adresse de chargement
START       main_           // adresse de démarrage

PARAM       EQU 12



main_   
    LDW R1, #PARAM          // charge adresse de la chaîne n°0 dans R1
    ADQ 0x0030, R1
    LDW WR, #WRITE_EXC    // on suppose que symbole WRITE_EXC déjà défini
    TRP WR                // lance trappe dont n° dans WR
    STW R1, -(SP)           // empile paramètre p = STRING0 contenu dans R1 :
    JSR @print_             // appelle la fonction d'adresse print_:
    TRP #EXIT_EXC           // EXIT: arrête le programme
    
    
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
