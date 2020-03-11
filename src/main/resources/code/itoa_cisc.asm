        STW BP, -(SP)
        LDW BP, SP

// récupération des paramètres depuis pile vers registres
        LDW R0, (BP)ITOA_I    // R0 = i    
        LDW R1, (BP)ITOA_B    // R1 = b
        
// gère le signe: normalement itoa gère des int c'est à dire des entiers signés, 
// mais en fait seulement pour b=10;
// dans ce cas calcule le signe dans R3 et charge R0 avec la valeur absolue de i
        LDQ ASCII_SP, R3      // code ASCII de eSPace (SPace) -> R3
        LDQ 10, WR            // 10 -> WR
        CMP R1, WR            // charge les indicateurs de b - 10
        BNE NOSIGN-$-2        // si non égal (donc si b != 10) saute en NOSIGN, sinon calcule signe
        LDQ ASCII_PLUS, R3    // charge le code ASCII du signe plus + dans R3
        TST R0                // charge les indicateurs de R0 et donc de i
        BGE POSIT-$-2         // saute en POSIT si i >= 0
        NEG R0, R0            // change le signe de R0
        LDQ ASCII_MINUS, R3   // charge le code ASCII du signe moins - dans R3
POSIT   NOP                   // R3 = code ASCII de signe: SP pour aucun, - ou +


// convertit l'entier i en chiffres et les empile de droite à gauche
NOSIGN  LDW R2, R0            // R2 <- R0
CNVLOOP LDW R0, R2            // R0 <- R2
   
   // effectue "créativement" la DIVision par b supposé pair (car l'instruction DIV est hélas signée ...)
   // d=2*d' , D = d * q + r  , D = 2*D'+r" , D' = d' * q + r' => r = 2*r'+r"
   // un bug apparaît avec SRL R0, R0 avec R0 = 2 : met CF à 1 !!
        SRL R1, R1            // R1 = b/2
        ANI R0, R4, #1        // ANd Immédiate entre R0 et 00...01 vers R4:
                              // bit n°0 de R0 -> R4; R4 = reste" de R0/2
        SRL R0, R0            // R0 / 2 -> R0
        DIV R0, R1, R2        // quotient = R0 / R1 -> R2, reste' = R0 % R1 -> R0
        SHL R0, R0            // R0 = 2 * reste'
        ADD R0, R4, R0        // R0 = reste = 2 * reste' + reste" => R0 = chiffre
        SHL R1, R1            // R1 = b

        ADQ -10, R0           // chiffre - 10 -> R0 
        BGE LETTER-$-2        // saute en LETTER si chiffre >= 10
        ADQ 10+ASCII_0, R0    // ajoute 10 => R0 = chiffre, ajoute code ASCII de 0 
                              // => R0 = code ASCII de chiffre
        BMP STKCHR-$-2        // saute en STKCHR 

LETTER  ADQ ASCII_A, R0       // R0 = ASCII(A) pour chiffre = 10, ASCII(B) pour 11 ...
                              // ajoute code ASCII de A => r = code ASCII de chiffre
STKCHR  STW R0, -(SP)         // empile code ASCII du chiffre 
                              // (sur un mot complet pour pas désaligner pile)
        TST R2                // charge les indicateurs en fonction du quotient ds R2)
        BNE CNVLOOP-$-2       // boucle si quotient non nul; sinon sort

// les caractères sont maintenant empilés : gauche en haut et droit en bas

// recopie les caractères dans le tampon dans le bon ordre: de gauche à droite
        LDW R1, (BP)ITOA_P    // R1 pointe sur le début du tampon déjà alloué 
        STB R3, (R1)+         // copie le signe dans le tampon
CPYLOOP LDW R0, (SP)+         // dépile code du chiffre gauche (sur un mot) dans R0
        STB R0, (R1)+         // copie code du chiffre dans un Byte du tampon de gauche à droite
        CMP SP, BP            // compare SP et sa valeur avant empilement des caractères qui était BP
        BNE CPYLOOP-$-2       // boucle s'il reste au moins un chiffre sur la pile
        LDQ NUL, R0           // charge le code du caractère NUL dans R0
        STB R0, (R1)+         // sauve code NUL pour terminer la chaîne de caractères

// termine
        LDW R0, (BP)ITOA_P    // retourne le pointeur sur la chaîne de caractères

    // UNLINK: fermeture de l'environnement de la fonction itoa
        LDW SP, BP            // SP <- BP : abandonne infos locales; SP pointe sur ancinne valeur de BP
        LDW BP, (SP)+         // dépile ancienne valeur de BP dans BP; SP pointe sur adresse de retour

        RTS                   // retourne au programme appelant
