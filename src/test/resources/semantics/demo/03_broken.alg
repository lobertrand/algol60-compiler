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
    end;

    for a := 1, 2, 3 do
        myLabel:;
end
