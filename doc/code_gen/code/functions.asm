


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
HP			EQU R10			// heap pointer (pointeur tas)
LR			EQU R9			// loop register
                            // R0 pour résultat de fonction
                            // R1 ... R8 disponibles


ORG         LOAD_ADRS       // adresse de chargement
START       main_           // adresse de démarrage

main_
	JSR @read_
	JSR @print_
	TRP #EXIT_EXC

print_
    TRP #WRITE_EXC
    RTS

read_
	LDQ READ_EXC, WR		
	TRP #READ_EXC
	LDW R0, (WR)
	RTS
	
	
	
