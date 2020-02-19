begin
    begin
        integer procedure max(a, b);
        value a, b; integer a, b;
        begin
            max := if a > b then a else b
        end;
        integer result;
        result := max(12, 3)
    end;

    begin
        integer array a[1:3];
        integer procedure max(arr);
        value arr; integer array arr;
        begin
            max := 0
        end;
        integer result;

        a[1] := 5;
        a[2] := 15;
        a[3] := 10;
        result := max(a);
    end;

    begin
        integer array a[1:100];
        integer i;
        for i := 1 step 1 until 100 do
        begin
            a[i] := i
        end
    end
end