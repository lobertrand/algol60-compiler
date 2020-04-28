begin
  comment Classic FizzBuzz program :
          - modulo procedure
          - used of booleans
          - if statements
          - for loop ;

  integer procedure mod(a, b); value a, b; integer a, b;
  begin
    mod := a - (a//b) * b
  end;

  integer i;
  boolean neither;

  for i := 1 step 1 until 100 do begin
    neither := true;

    if mod(i, 3) = 0 then begin
      outstring(1, "Fizz");
      neither := false;
    end;

    if mod(i, 5) = 0 then begin
      outstring(1, "Buzz");
      neither := false;
    end;

    if neither then outinteger(1, i);

    line()
  end
end
