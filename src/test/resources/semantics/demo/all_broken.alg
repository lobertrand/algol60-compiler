begin

    begin
        integer a, b;
        real a;
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

            chooseFirst := b > 5 \/ a + 3 /\ a+b < 40;
            comment If-expression;
            goto state[if chooseFirst then 1 else 2]
        end;

        begin
            switchEnd:
        end
    end;

    begin
        comment Array as parameter of procedure;
        integer procedure max(arr, a, b, extra);
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
    end;

    begin
        integer procedure getMessageNumber(grade);
        value grade; integer grade; integer extra;
        begin
            comment If-statement exhaustiveness check;
            if grade < 8 then
                getMessageNumber := 1
            else if grade < 14 then
                getMessageNumber := 2
            comment else
                getMessageNumber := 3 ;
        end;

        string a;
        a := 1;
        notDeclared();
        begin
            string array messages[a:3];
            comment string msg;

            messages[1] := "bad";
            messages[2] := "okay";
            messages[4] := "great";

            msg := messages[getMessageNumber(12.5)];
        end
    end;

    for a := 1, 2, 3 do
        myLabel:;

end
