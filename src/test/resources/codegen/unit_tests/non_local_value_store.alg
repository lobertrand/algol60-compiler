begin
  integer a;
  a := 2;
  begin
    integer b;
    b := 8;
    a := b + 3;
    begin
      outinteger(1, a); space();
      a := a * 2;
    end
  end;
  outinteger(1, a);
end
