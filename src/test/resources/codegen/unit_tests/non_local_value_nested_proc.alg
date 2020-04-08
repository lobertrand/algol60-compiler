begin
  integer x, y, z;

  integer procedure f(n); value n; integer n;
  begin
    integer c;
    integer procedure aux(b); value b; integer b;
    begin
      aux := b + z + c;    comment 5 + 3 + 4 = 12;
    end;
    c := 4;
    f := aux(n) + x;    comment 12 + 2 = 14;
  end;

  x := 2;
  y := 5;
  z := 3;
  outinteger(1, f(y));    comment 14;
end
