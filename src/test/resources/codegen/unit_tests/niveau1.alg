begin
integer n,cpt, m ,i ;
n := 100;
m := 5;
cpt := 0;
for i := 0 step 1 until n do
    begin
    if (i // m = 0 ) then
        begin
        cpt := cpt + 1;
        outinteger(1, i)
        end
    end;
outinteger(1, cpt)
end