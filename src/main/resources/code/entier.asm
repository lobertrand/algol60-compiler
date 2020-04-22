	// Returns an integer part of a real (basically does nothing)
	STW BP, -(SP)
    LDW BP, SP
	LDW R1, (BP)4
	LDW SP, BP 
    LDW BP, (SP)+
    RTS

