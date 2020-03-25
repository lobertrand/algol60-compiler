begin
  string procedure getMessage;
  begin
    string msg;
    msg := "Hello world!";
    getMessage := msg;
  end;

  outstring(1, "Message is : ");
  outstring(1, getMessage());
end
