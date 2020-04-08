begin
   switch state := first, second,third,forth,fifth;

   outstring(1,"le switch est déclaree : ");
   goto state[5] ;
   outstring(1,"j'ai clairement sauté trop loin");



  first:;
      outstring(1, "first operation");
      goto switchEnd;

  second:;
      outstring(1, "second operation");
      goto switchEnd;
  third:;
      outstring(1, "third operation");
      goto switchEnd;
  fifth:;
       outstring(1, "fifth operation");
       goto switchEnd;
  forth:;
       outstring(1, "forth operation");
       goto switchEnd;
  switchEnd:;



end


