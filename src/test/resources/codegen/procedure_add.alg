begin
  integer procedure add(a, b);
  value a, b; integer a, b;
  begin
    integer temp;
    temp := a + b;
    add := temp;
  end;
  integer x, y, res;

  x := 10;
  y := 13;
  
  outstring(1, "4 + 6 = ");
  res := add(4, 6);
  outinteger(1, res);
  
  outstring(1, "10 + 13 = ");
  outinteger(1, add(x, y));
end
