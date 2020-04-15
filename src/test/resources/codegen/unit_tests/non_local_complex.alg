begin
  integer a;
  real procedure bar;
  begin
    bar := a * 2          comment 3 * 2 = 6;
  end;
  real procedure big;
  begin
    real procedure foo;
    begin
      foo := bar() - 1    comment 6 - 1 = 5;
    end;
    big := foo()          comment 5;
  end;
  a := 3;
  outreal(1, big())       comment 5;
end
