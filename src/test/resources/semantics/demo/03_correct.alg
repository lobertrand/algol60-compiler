begin
    integer procedure getMessageNumber(grade);
    value grade; integer grade;
    begin
        comment If-statement exhaustiveness check;
        if grade < 8 then
            getMessageNumber := 1
        else if grade < 14 then
            getMessageNumber := 2
        else
            getMessageNumber := 3
    end;

    integer a;
    a := 1;
    begin
        string array messages[a:3];
        string msg;

        messages[1] := "bad";
        messages[2] := "okay";
        messages[3] := "great";

        msg := messages[getMessageNumber(12)];
    end
end
