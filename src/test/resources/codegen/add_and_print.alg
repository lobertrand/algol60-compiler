begin
  procedure addAndPrint(a, b);
  value a, b; integer a, b;
  begin
    integer res;
    res := a + b;
    outinteger(1, res);
  end;
  integer x, y;

  x := 10;
  y := 13;
  
  outstring(1, "4 + 6 = ");
  addAndPrint(4, 6);
  outstring(1, "10 + 13 = ");
  addAndPrint(x, y);
end
