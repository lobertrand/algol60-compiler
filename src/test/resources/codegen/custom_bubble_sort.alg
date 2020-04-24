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

    procedure fillVector(v, length);
    value v, length; real array v; integer length;
    begin
      for i := 1 step 1 until length do
        v[i] := random();
    end;

    procedure printVector(v, length);
    value v, length; real array v; integer length;
    begin
      for i := 1 step 1 until length do
        outreal(1, v[i]);
    end;

    procedure bubbleSort(v, length);
    value v, length; real array v; integer length;
    begin
      integer i, j;
      for i := 1 step 1 until N-1 do
        for j := 1 step 1 until N-i do
          if v[j] > v[j+1] then
          begin
            real temp;
            temp := v[j];
            v[j] := v[j+1];
            v[j+1] := temp
          end;
    end;

    real array a[1:N];

    fillVector(a, N);
    outstring(1, "Pseudo-random numbers:"); line();
    printVector(a, N);
    
    bubbleSort(a, N);

    outstring(1, "After bubble sort:"); line();
    printVector(a, N);
  end
end
