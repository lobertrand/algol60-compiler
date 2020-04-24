begin
  integer MAX, N;
  integer n, m, i;

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

  N := 10; comment Length of vector;
  begin
  	real array a[1:N];
  	integer i, j;

    outstring(1, "Pseudo-random numbers:"); line();
    for i := 1 step 1 until N do
    begin
      a[i] := random();
      outreal(1, a[i])
    end;

    comment Do the Bubble Sort;
    for i := 1 step 1 until N-1 do
      for j := 1 step 1 until N-i do
        if a[j] > a[j+1] then
        begin
          real temp;
          temp := a[j];
          a[j] := a[j+1];
          a[j+1] := temp
        end;

    outstring(1, "After bubble sort:"); line();
    for i := 1 step 1 until N do
      outreal(1, a[i])
  end
end
