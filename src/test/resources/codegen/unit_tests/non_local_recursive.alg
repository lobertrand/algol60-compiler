begin
  integer a;

  integer procedure rec(n);
  value n; integer n;
  begin
    rec := 0;
    if (n > 20) then
      rec := n
    else
      begin
        rec := rec(a * n);
        outinteger(1, rec); space();
      end
  end;

  a := 2;
  outinteger(1, rec(3))
end
