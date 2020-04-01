begin 

    outstring(1, "if ");
    if true then
        outstring(1, "then ")
    else
        outstring(1, "else ");
    outstring(1, "end ");

    line();

    outstring(1, "if ");
    if false then
        outstring(1, "then ")
    else
        outstring(1, "else ");
    outstring(1, "end ");

end
