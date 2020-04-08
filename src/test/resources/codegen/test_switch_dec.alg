begin
   switch state := first, second;
   first:;
       outstring(1, "first operation");
       goto switchEnd;

   second:;
       outstring(1, "second operation");
       goto switchEnd;

    switchEnd:
end
