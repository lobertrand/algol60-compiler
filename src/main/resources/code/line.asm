    // Prepare procedure environment
    STW BP, -(SP)       // Save base pointer on the stack
    LDW BP, SP          // Load stack pointer into base pointer

    LDW R0, #NEWLINE    // Byte to print in R0
    TRP #WRITE_EXC      // Print newline

    // End of procedure
    LDW SP, BP          // Point to saved base pointer
    LDW BP, (SP)+       // Pop saved base pointer into the current one
    RTS                 // Return to caller
