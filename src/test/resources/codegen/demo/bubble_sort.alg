begin
  comment Bubble sort algorithm on a vector :
          - 1D array passed as procedure parameter
          - for .. step .. until loops
          - non local variable N used as size of dynamic array 'a' ;

  integer N;
  N := 6; comment Length of vector a;
  begin
    procedure printVector(v, length);
    value v, length; real array v; integer length;
    begin
      integer i;
      for i := 1 step 1 until length do
        outreal(1, v[i]);
    end;

    procedure swap(v, a, b);
    value v, a, b; real array v; integer a, b;
    begin
      real temp;
      temp := v[a];
      v[a] := v[b];
      v[b] := temp;
    end;

    procedure bubbleSort(v, length);
    value v, length; real array v; integer length;
    begin
      integer i, j;
      for i := 1 step 1 until length-1 do
        for j := 1 step 1 until length-i do
          if v[j] > v[j+1] then
            swap(v, j, j+1);
    end;

    real array a[1:N];
    a[1] := 3;
    a[2] := 8;
    a[3] := 2;
    a[4] := 7;
    a[5] := 1;
    a[6] := 4;

    outstring(1, "Original vector:"); line();
    printVector(a, N);
    bubbleSort(a, N);
    outstring(1, "Bubble-sorted:"); line();
    printVector(a, N);
  end
end
