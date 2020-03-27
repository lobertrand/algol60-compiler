    // Prepare procedure environment
    STW BP, -(SP)       // Save base pointer on the stack
    LDW BP, SP          // Load stack pointer into base pointer
    
    LDW R0, (BP)4       // Load boolean parameter of shift 4 into R0
    JEQ #outfalse-$-2   // Jump to outfalse if last result equals 0
    LDW R0, #TRUE       // Load 'true' string intro R0 
    JMP #outbool-$-2    // Jump to print intruction
outfalse    
    LDW R0, #FALSE      // Load 'false' string into R0
outbool
    TRP #WRITE_EXC      // Print value 'true' or 'false'
    
    // End of procedure
    LDW SP, BP          // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)+       // charge BP avec ancien BP
    RTS                 // retour au programme appelant
