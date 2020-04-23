begin
  integer MAX, n, m, i;

  comment Modulo operation;
  integer procedure mod(a, b); value a, b; integer a, b;
  begin
    mod := a - (a//b * b)
  end;

  comment Simple random number generator using Fibonacci;
  integer procedure random;
  begin
    integer oldM;
    random := mod(n, MAX);
    oldM := m;
    m := n + m;
    n := oldM;
  end;

  MAX := 10;
  n := 456;
  m := 123;

  begin
  	comment bubble sort;

  	real array a[1:100];
  	integer i, j, n;
    n := 10;

    outstring(1, "Pseudo-random numbers:"); line();
    for i := 1 step 1 until n do
    begin
      a[i] := random();
      outreal(1, a[i])
    end;

    comment Do the Bubble Sort;
    for i := 1 step 1 until n-1 do
      for j := 1 step 1 until n-i-1 do
        if a[j] > a[j+1] then
        begin
          real temp;
          temp := a[j];
          a[j] := a[j+1];
          a[j+1] := temp
        end;

    outstring(1, "After bubble sort:"); line();
    for i := 1 step 1 until n do
    begin
      outreal(1, a[i])
    end
  end
end

