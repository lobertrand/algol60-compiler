begin
    comment Gestion dynamique de la division par zéro;

    real a, b, c, d;

    a := 2;             outreal(1, a); space();
    b := 6;             outreal(1, b); space();
    c := b - a * 3;     outreal(1, c); space();
    d := 5 / c;         comment Error: Division by zero;

    outstring(1, "Instruction jamais atteinte");
end
