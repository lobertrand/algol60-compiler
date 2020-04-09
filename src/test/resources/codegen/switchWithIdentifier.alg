begin
   integer a;
   switch state := first, second,third,forth,fifth;
   a := 1;
   start :;

   goto state[a] ;




  first:;
      a := a+1;
      outstring(1, "first operation");
      goto start ;

  second:;
      a := a+2;
      outstring(1, "second operation");
      goto start ;
  third:;
      outstring(1, "third operation");
      goto start ;
  fifth:;
       outstring(1, "fifth operation");
       goto switchEnd;
  forth:;
       a := a+1;
       outstring(1, "forth operation");
       goto start ;
  switchEnd:;
  outstring(1, "Congrat bruhhhh you did it like a boss");



end