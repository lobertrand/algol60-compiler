begin

	comment IF ELSE ;

	if a=>b then 
		a:=b
	else 

		comment BLOCK IMBRIQUE ;

		begin

			comment scientific notation (using #);

			real x;
			integer y;
			y := 3;
			x := 1.2#30;


			comment Appel de procedure ;

			outreal(1,x);

			comment array, matrix with square numbers ;

			integer array nArr[1:100];
			real array aa[1:100,1:100];
			integer i;

			comment BOUCLE FOR;

			for i := 1 step 1 until 100 do
			begin 
				nArr[i] := i*i;
				aa[i,i] := i*i ;
				outinteger (1, nArr[i])
			end ;
			outstring (1,"\n");

			comment DEFINITION DE PROCEDURE (avec type de retour et arguments);

			real procedure linearFunc(a, b, x);
			value a, b, x; real a, b, x;
			begin
				linearFunc := a + b*x
			end;

			comment DEFINITION DE PROCEDURE (avec type de retour et sans arguments);

			real procedure pi;
			begin
				pi := 3.141592654
			end;

			comment DEFINITION DE PROCEDURE (sans type de retour et avec arguments);
			
			procedure myPrint(m);
			value m; integer m;
			begin
				outinteger(1,m)
			end;

			comment DEFINITION DE PROCEDURE (sans type de retour et sans arguments);

			procedure cr;
			begin
				outstring (1,"\n")
			end;

			comment GOTO et LABEL ;

			startlabel:;
				outinteger (1,n);
				n := n+1;
			goto startlabel


		end;

	comment assignement compliqué ;

	a[1,b(1,coucou,c[1,1])] := a-b+c*d/e+y(a-b,c*d[1,1])

end