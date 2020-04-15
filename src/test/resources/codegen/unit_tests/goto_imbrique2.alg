begin
  integer i;
  string b;
  b := "end";

  start:;
  outstring(1, "start ");

  jump:;
  outstring(1, "jump ");
  i := i + 1;

  begin
    integer k;
    k := 8;
    outstring(1, "before ");
    begin
      begin
        if i <> 3 then
          goto jump;
      end
    end;
    outinteger(1, k);
    outstring(1, " after ");
  end;

  outstring(1, b);
end
