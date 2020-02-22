begin
    comment Procedure declaration;
    integer procedure max(a, b, c);
    value a, b; integer b;
    begin
        comment If-expression;
        max := if a > b then a else b
    end;
    integer result;

    comment Procedure call;
    result := max("12.5", 3.5)
end
