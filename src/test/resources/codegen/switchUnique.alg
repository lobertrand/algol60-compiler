begin
   switch state := first, second;
   begin
      switch state := first, second;

      goto state[2] ;


      first:;
          outstring(1, "first operation");
          goto switchEnd;

      second:;
          outstring(1, "second operation");
          goto switchEnd;


       switchEnd:
   end;

   goto state[1] ;

   first:;
       outstring(1, "first operation");
       goto switchEnd;

   second:;
       outstring(1, "second operation");
       goto switchEnd;

    switchEnd:
end