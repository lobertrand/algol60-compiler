    // Print error message
    LDW R0, #DIV0
    TRP #WRITE_EXC
    // Print newlines
    LDW R0, #NEWLINE
    TRP #WRITE_EXC
    TRP #WRITE_EXC
    // Exit program
    TRP #EXIT_EXC
