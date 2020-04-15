begin
    integer i;
    boolean condition ;
    hello:;
    condition := (8*6+3)<(2**12);
    outstring(1,"start ");
	boucle:;
	outstring(1,"boucle ");
	i := i+1;
	if condition then
            begin
                if i = 5 then
                    goto endlabel
                else
                    goto boucle
            end;

    endlabel:;
    outstring(1,"endlabel")
end
