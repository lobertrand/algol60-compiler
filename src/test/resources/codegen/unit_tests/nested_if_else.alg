begin 

    outstring(1, "START ");
    if false then
        begin
            outstring(1, "A ");
            if false then
               outstring(1, "A1 ")
            else
                outstring(1, "A2 ");
        end
    else
        begin
            outstring(1, "B ");
            if false then
               outstring(1, "B1 ")
            else
                outstring(1, "B2 ");
        end;
    outstring(1, "END");
end
