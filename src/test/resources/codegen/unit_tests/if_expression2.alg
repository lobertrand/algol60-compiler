begin 

	real pow10,pow2 ;
	boolean condition;
	outstring(1,"START");
	condition := (8*6+3)<(2**12);
	pow2 := if condition then 2**2 else 3**2;
	pow10 := if condition then 2#4 else 3#2;
	outreal(1,pow2);
	outreal(1,pow10);
	outstring(1, "END")

end
