begin

    comment TEST Procedure imbriquées avec utilisation de variable globale pour verifier le chainage statique;

    integer a;
    real procedure bar;
    begin
        bar := a*2
    end;
    real procedure big;
    begin
        real procedure foo;
        begin
            foo := bar() -1
        end;
        big := foo()
    end;
    a:=3;
    outreal(1,big());

    comment TEST des 3 BOUCLES FOR imbriquées avec variable a et i ;

    begin
        integer a, i;
        a:=4;
        begin
           begin
              for i := 3 step 2 until 9 do
              begin
                outinteger(1, i)
              end;
              for i := 1 while a <= 5 do
              begin
                  outinteger(1, a);
                  a:=a+1
              end;
              for i := 3, 4, 5 do
              begin
                outinteger(1, i)
              end
           end
        end
    end;

    comment TEST des IF ELSE avec variables globales;


    if a = 4 then
        begin
            outstring(1, "ERREUR car le 'a'actuel vaut 3 et pas 4");
        end
    else
        begin
            integer a;
            a := 4;
            if a = 4 then
               outstring(1, "Bravo celui la vaut bien 4 ")
            else
                outstring(1, "ERREUR le a actuel vaut 4 cette fois si ");
        end;

    comment TEST des Goto imbriqués;
    goto startBlock;
    hello:;
    outstring(1,"YOU JUMPED TO THE WRONG HELLO "); line();
    startBlock:;
    begin
        outstring(1,"WELCOME TO THE NEW BLOCK ");

        startBlock:;
        hello:;

        a := a+1;
        outstring(1,"a now is equal to ");
        outinteger(1,a);
        if a = 5 then
            begin
                goto endlabel
            end
        else
        begin
            goto hello
        end;


        endlabel:;
        outstring(1,"endlabel")
    end
end