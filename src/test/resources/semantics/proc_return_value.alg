begin

  integer procedure factorial(n);
  value n; integer n;
  begin
    if n <= 1
    then
      if n = 0
      then
        factorial := 1
      else
        factorial := 1
    else
      factorial := n * factorial(n - 1)
  end

end
