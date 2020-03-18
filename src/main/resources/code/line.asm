    // prépare l'environnement de la fonction appelée (prologue) :
    LDQ 0, R1           // R1 = taille données locales (ici 0) de fonction appelée
    ADQ -2, SP          // décrémente le pointeur de pile SP
    STW BP, (SP)        // sauvegarde le contenu du registre BP sur la pile
    LDW BP, SP          // charge contenu SP ds BP qui pointe sur sa sauvegarde
    SUB SP, R1, SP      // réserve R1 octets sur la pile pour la variable locale z

    // affiche texte pointé par R0
    LDW R0, #NEWLINE        // R0 = p = adresse du début du texte à afficher
    LDW WR, #WRITE_EXC  // on suppose que symbole WRITE_EXC déjà défini
    TRP WR              // lance trappe dont n° dans WR

    // fin de la fonction (épilogue) :
    // UNLINK
    LDW SP, BP          // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)        // charge BP avec ancien BP
    ADQ 2, SP           // ancien BP supprimé de la pile
    // RTS              // retour au programme appelant:
    LDW WR, (SP)        // charge WR avec l'adresse de retour
    ADQ 2, SP           // incrémente le pointeur de pile SP
    JEA (WR)            // saute à l'instruction d'adresse absolue dans WR
