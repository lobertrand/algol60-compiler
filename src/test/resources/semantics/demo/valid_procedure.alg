begin
    integer procedure max(a, b);
    value a, b; integer a, b;
    begin
        max := if a > b then a else b
    end;
    integer result;

    result := max(12, 3)
end