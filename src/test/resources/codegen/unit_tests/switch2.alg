begin
    integer n;
     switch new := first,second,third ;
     n := 3;
     if n < 5 then
               begin
                    label :;
                    goto new[2];
                end
     else
            goto new[3];

     first :;
     outstring(1,"first ");
     second:;
     outstring(1,"second ");
     third:;
     outstring(1,"third ")

end
