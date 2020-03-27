begin
  integer procedure compute(a);
  value a; integer a;
  begin
    integer procedure aux(a);
    value a; integer a;
    begin
      aux := a * 2;
    end;
    compute := aux(a) + 3;
  end;

  outreal(1, compute(compute(5)));
end
