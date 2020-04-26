begin
    comment Gestion dynamique des indices en dehors des bornes d'une tableau;

    integer a, b, i;
    a := 2;
    b := 6;

    begin
        string array arr[a:b];
        arr[2] := "two ";
        arr[3] := "three ";
        arr[4] := "four ";
        arr[5] := "five ";
        arr[6] := "six";

        for i := a step 1 until b + 1 do
        begin
            outstring(1, "arr[");
            outinteger(1, i);
            outstring(1, "] = ");
            outstring(1, arr[i]);   comment Error: index out of bounds à b + 1;
            line();
        end;

        outstring(1, "Instruction jamais atteinte");
    end
end
