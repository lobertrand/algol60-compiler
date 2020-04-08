begin
	integer a, b;
	boolean c,d,e,f,g,h,i;
	a := -1;
	outinteger(1, a);
	b := -3;
	outinteger(1, b);
	c := (a - b) >= a;
	outboolean(1, c);
	d := 5 >= 5;
	outboolean(1, d);
	e := 5 > 5;
	outboolean(1, e);
	f := 4 <= 5;

	outboolean(1, f);
	g := 5 < 5;
	outboolean(1, g);
	h := 5 = 5;
	outboolean(1, h);
	i := 5 = 4;
	outboolean(1, i);
end

