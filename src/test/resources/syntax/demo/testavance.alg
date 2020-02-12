begin

	comment IF ELSE ;

	if a=>b then 
		a:=b
	else 
		s:=2;

	comment Block imbriqué ;

	begin
		integer a
	end;

	comment BOUCLE FOR;

	for i := 1 step 1 until 100 do
	begin 
		x := i 
	end ;


	comment DEFINITION DE PROCEDURE (avec type de retour et arguments);

	real procedure linearFunc(a, b, x);
	value a, b, x; real a, b, x;
	begin
		linearFunc := a + b*x
	end;

	comment Appel de proc ;

	linearFunc(a,b,c);

	comment GOTO et LABEL ;

	startlabel:;
		n := n+1;
	goto startlabel


end