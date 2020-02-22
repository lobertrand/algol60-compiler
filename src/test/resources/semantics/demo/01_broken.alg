begin
    integer a, b;
    ininteger(0, a);
    ininteger(0, b);

    begin
        comment Switch declaration;
        switch state := first, second, extra;
        boolean chooseFirst;

        first:;
            outstring(1, "first operation");
            goto switchEnd;

        second:;
            outstring(1, "second operation");
            goto switchEnd;

        comment If-expression;
        chooseFirst := b > 5 \/ a + 3 /\ a+b < 40;
        goto state[if chooseFirst then 1 else 2]
    end;

    begin
        switchEnd:
    end
end
