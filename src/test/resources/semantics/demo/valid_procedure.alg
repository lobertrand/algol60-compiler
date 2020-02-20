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
        integer procedure max(arr, startIndex, endIndex);
        value arr, startIndex, endIndex;
        integer array arr; integer startIndex, endIndex;
        begin
            integer i, maximum;
            for i := startIndex step 1 until endIndex do
            begin
                if arr[i] > maximum then
                    maximum := arr[i]
            end;
            max := maximum
        end;
        integer array a[1:3];
        integer result;

        a[1] := 6;
        a[2] := 8;
        a[3] := 3;
        result := max(a, 1, 3);
    end;
end