    // Prepare procedure environment
    STW BP, -(SP)       // Save base pointer on the stack
    LDW BP, SP          // Load stack pointer into base pointer

    LDW R0, (BP)4       // Load string parameter of shift 4 into R0
    TRP #WRITE_EXC      // Prints the string value from R0

    // End of procedure
    LDW SP, BP          // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)+       // charge BP avec ancien BP
    RTS                 // retour au programme appelant
