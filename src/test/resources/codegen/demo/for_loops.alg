begin
    comment Trois types de boucles for :
            - for .. step .. until .. do
            - for .. while .. do
            - for .., .., .. do ;

    integer a, i;
    a := 4;
    begin
        begin
            outstring(1, "for i := 3 step 2 until 9 do = ");
            for i := 3 step 2 until 9 do
            begin
                outinteger(1, i); space();
            end;
            line();

            outstring(1, "for i := 1 while a <= 7 do = ");
            for i := 1 while a <= 7 do
            begin
                outinteger(1, a); space();
                a := a + 1
            end;
            line();

            outstring(1, "for i := 7, 9, 4 do = ");
            for i := 7, 9, 4 do
            begin
                outinteger(1, i); space();
            end;
       end
    end
end
