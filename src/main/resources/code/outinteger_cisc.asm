    // Prepare procedure environment
    STW BP, -(SP)
    LDW BP, SP

    // char toto[7];
    // réserve 7+1 = 8 caractères en pile
    // (entier pair supérieur à 7 demandé pour pas désaligner pile)
    ADI SP, SP, #-8     // réserve place pour text sur pile (8 octets);
                        // déplacement du début du tableau est -8

    // int value;
    ADI SP, SP, #-2     // réserve place pour variable value;
                        // déplacement de value est -10

    // value = -23; 
    // LDW R0, (R0)
    // LDW R0, #-23        // charge R0 avec -23 = C2(23) = FFE9
    LDW R0, (BP)4       // Load string parameter of shift 4 into R0

    STW R0, (BP)-10     // sauve R0 à l'adresse BP-10


    LDW R0, BP          // R0 = BP
    ADQ 4, R0           // R0 pointe sur p
    LDW R0, (R0)-10     // R0 = p = adresse du début du texte à afficher

    // itoa(value, text, 10); // appelle itoa avec i = value, p = text, b = 10

    LDW R0, #10         // charge 10 (pour base décimale) dans R0
    STW R0, -(SP)       // empile contenu de R0 (paramètre b)

    ADI BP, R0, #-8     // R0 = BP - 8 = adresse du tableau text
    STW R0, -(SP)       // empile contenu de R0 (paramètre p)

    LDW R0, (BP)-10     // charge R0 avec value
    STW R0, -(SP)       // empile contenu de R0 (paramètre i)

    JSR @itoa_          // appelle fonction itoa d'adresse itoa_

    ADI SP, SP, #6      // nettoie la pile des paramètres 
                        // de taille totale 6 octets

    // print(text);

    ADI BP, R0, #-8     // R0 = BP - 8 = adresse du tableau text
    STW R0, -(SP)       // empile contenu de R0 (paramètre p)
    
    JSR @outstring_     // appelle fonction print d'adresse print_

    ADI SP, SP, #2      // nettoie la pile des paramètres
                        // de taille totale 2 octets

    // } // fermeture du bloc englobant de main
    LDW SP, BP          // abandonne variables locales de main
    LDW BP, (SP)+       // dépile ancien BP dans BP
