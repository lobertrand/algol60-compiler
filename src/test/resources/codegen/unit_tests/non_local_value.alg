begin
  integer a, b;
  a := 2;
  b := 3;
  outinteger(1, a); comment 2;
  outinteger(1, b); comment 3;
  outstring(1, "#"); line();
  begin
    integer c, d;
    c := 4;
    d := a + c;
    outinteger(1, a); comment 2;
    outinteger(1, b); comment 3;
    outinteger(1, c); comment 4;
    outinteger(1, d); comment 6;
    outstring(1, "#"); line();
    begin
      integer e, f;
      e := 5;
      f := a + e;
      outinteger(1, a); comment 2;
      outinteger(1, b); comment 3;
      outinteger(1, c); comment 4;
      outinteger(1, d); comment 6;
      outinteger(1, e); comment 5;
      outinteger(1, f); comment 7;
    end
  end
end
