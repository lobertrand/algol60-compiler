begin
  integer procedure add(a, b);
  value a, b; integer a, b;
  begin
    integer temp;
    temp := a + b;
    add := temp;
  end;
  integer x, y, res;

  x := 2;
  y := 4;

  outstring(1, "4 + 6 = ");
  res := add(4, 6);
  outinteger(1, res);

  outstring(1, "2 + 3 + 4 = ");
  outinteger(1, add(x, add(3, y)));
end
