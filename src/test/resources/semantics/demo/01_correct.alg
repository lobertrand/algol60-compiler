begin
    comment Procedure declaration;
    real procedure max(a, b);
    value a, b; real a, b;
    begin
        comment If-expression;
        max := if a > b then a else b
    end;
    real result;

    comment Procedure call;
    result := max(12.5, 3)
end
