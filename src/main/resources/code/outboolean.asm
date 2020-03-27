	// Prepare procedure environment
    STW BP, -(SP)       // Save base pointer on the stack
    LDW BP, SP          // Load stack pointer into base pointer
    
    LDW R0, (BP)4       // Load string parameter of shift 4 into R0
    JEQ #false_-$-2		// Jump to false_ if last result equals 0
    LDW R0, #TRUE
    TRP #WRITE_EXC
    // End of procedure
    LDW SP, BP          // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)+       // charge BP avec ancien BP
    RTS                 // retour au programme appelant
    
false_    
	LDW R0, #FALSE
	TRP #WRITE_EXC
    // End of procedure
    LDW SP, BP          // charge SP avec contenu de BP: abandon infos locales
    LDW BP, (SP)+       // charge BP avec ancien BP
    RTS                 // retour au programme appelant
