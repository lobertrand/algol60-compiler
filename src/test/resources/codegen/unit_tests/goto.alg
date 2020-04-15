begin
    integer i;
    hello:;
     outstring(1,"HELLO ");
    begin
	i := i+1;
	if i< 5 then 
	   goto hello ;
    end;
    outstring(1,"END")

end
