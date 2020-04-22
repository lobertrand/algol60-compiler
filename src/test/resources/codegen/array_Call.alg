begin
    integer a,b,c;
    integer array d[10:25,6:59];
    a:=22;
    d[a,a]:= 59;
    b := d[a,a] + 10 ;
    outinteger(1,b);

end