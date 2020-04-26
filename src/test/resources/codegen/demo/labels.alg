begin
    comment Goto sur étiquettes :
            - différents niveaux d'imbrication
            - dépilement d'autant d'environnements que nécessaire
            - accès à la variable non locale 'a' ;

    integer a;

    goto startBlock;
    hello:;
    outstring(1, "YOU JUMPED TO THE WRONG HELLO "); line();
    startBlock:;
    begin
        outstring(1, "WELCOME TO THE NEW BLOCK"); line();

        startBlock:;
        hello:;

        a := a + 1;
        outstring(1, "a now is equal to ");
        outinteger(1, a); line();
        if a = 5 then
            begin
                goto endlabel
            end
        else
        begin
            goto hello
        end;

        endlabel:;
        outstring(1, "endlabel"); line();
    end
end
