begin
    comment Visibilité des variables :
            - démonstration du chaînage statique
            - procédure imbriquée
            - variables locales de même nom qu'une variable non-locale ;

    integer a;

    real procedure bar;
    begin
        bar := a * 2
    end;

    real procedure big;
    begin
        real procedure foo;
        begin
            foo := bar() -1
        end;
        big := foo()
    end;

    a := 3;

    outstring(1, "big() = ");
    outreal(1, big());

    line();
    line();

    outstring(1, "If-else: "); line();
    if a = 4 then
        begin
            outstring(1, "ERREUR: le 'a' actuel vaut 3 et pas 4"); line();
        end
    else
        begin
            integer a;
            a := 4;
            if a = 4 then
                outstring(1, "Bravo celui la vaut bien 4")
            else
                outstring(1, "ERREUR: le 'a' actuel vaut 4 cette fois ci");
            line();
        end;
end
