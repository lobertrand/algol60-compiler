begin
    comment Gestion dynamique des erreurs d'indice dans un tableaux
            - erreur d'indice en dehors des bornes
            - déclaration et utilisation d'un tableau à 2 dimensions;

    integer a, b, c, d;
    a := 2;
    b := 6;
    c := -3;
    d := 0;

    begin
        string array arr[a:b, c:d];
        arr[2, -3] := "one ";
        arr[4, -1] := "two ";
        arr[5, -2] := "three ";
        arr[6, 0] := "four ";

        outstring(1, arr[2, -3]);
        outstring(1, arr[4, -1]);
        outstring(1, arr[5, -2]);
        outstring(1, arr[6, 0]);
        outstring(1, arr[-4, 0]);   comment Error: index out of bounds;

        outstring(1, "Instruction jamais atteinte");
    end
end
