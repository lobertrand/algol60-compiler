begin
  comment Factorial procedure :
          - classic recursive function
          - if-expression to populate result variable
          - for enumeration loop ;

  integer procedure factorial(n);
  value n; integer n;
  begin
    factorial := if n <= 1
                 then 1
                 else n * factorial(n - 1);
  end;

  integer i;
  for i := 6, 4, 7, 3 do
  begin
    outinteger(1, i);
    outinteger(1, factorial(i));
    line();
  end
end
