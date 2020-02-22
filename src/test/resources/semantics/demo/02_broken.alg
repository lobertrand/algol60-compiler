begin
    comment Array as parameter;
    integer procedure max(arr, a, b);
    value arr, a, b; string array arr; integer a, b;
    begin
        integer i, result;
        result := arr[a];
        comment For-statement;
        for i := a+1 step 1 until b do
        begin
            comment If-statement;
            if arr[i] > result then
                result := arr[i]
        end;
        max := result
    end;

    comment Array declaration and assignment;
    integer array a[1:3];
    a[0] := 6;
    a[2] := 8;
    a[4] := 3;

    comment Procedure call;
    outinteger(1, max(a, 1, 3))
end
