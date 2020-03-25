begin
  procedure addAndPrint(a, b);
  value a, b; integer a, b;
  begin
    integer res;
    res := a + b;
    outinteger(1, res);
  end;
  
  addAndPrint(12, 3);
end
