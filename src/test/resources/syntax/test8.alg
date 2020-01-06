begin
comment variable declarations must be before procedures definitions!;
	real y;
	
    y[poly(2,2),55] := a[1,poly2("ca",poly2(2,2,2),5.0)] ;
	begin
		poly2 := a0 + a1*x + a2*x*x
	end;
	
	y[poly2(gegreg)- 5,2] := poly2(0,0,1.0, 2.0);
	outreal(1, y)
end
