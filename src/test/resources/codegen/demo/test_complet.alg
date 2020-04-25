begin 
 	integer array a[1:3];
 	integer array b[1:3];
 	integer i;
 	switch fruits_jaune:= banane, citron;
	
	
	comment sqrt of int;
	integer procedure sqrt(v); value v; integer v;
	begin 
		integer k, i;
		if v=0 then v:=0;
		if v=1 then v:=1;
		
		sqrt:=1;
		i:=1;
		for k:=1 while sqrt<=v do 
		begin
			i:=i+1;
			sqrt:=i*i;
		end;
		sqrt:=i-1;
	end;
	
		
	comment calculate norm of ndim vector;
	integer procedure norm(v, ndim); value ndim; integer array v;  integer ndim;
	begin 
		integer i;
		for i:=1 step 1 until ndim do
			norm := norm+v[i]**2;
		norm :=sqrt(norm);
	end;
	
	
	comment fibonacci;
	integer procedure fib(n); value n; integer n;
	begin
		if n <=1 then fib := n else fib := fib(n-1)+fib(n-2);
	end;
	
	
	
	comment initialize vectors;
	for i:=1 step 1 until 3 do
	begin
		a[i]:=i;
		b[i]:=i*2;
	end;
	
	outstring(1, "Demonstration de quelques applications mathematiques :");
	line();
	outstring(1, "Point A : (1, 2, 3)");
	line();
	outstring(1, "Point B : (2, 4, 6)");
	line();
	outstring(1, "Calcul de partie entiere de la norme (gestion des tableaux en tant que parametre de procedure) :");
	line();
	outstring(1, "Vecteur OA : ");
	outinteger(1, norm(a, 3));
	outstring(1, "Vecteur OB : ");
	outinteger(1, norm(b, 3));
	
	outstring(1, "Appartenance des vecteurs dans une boule d'origine O et de rayon 5 : (gestion des booleens)");
	line();
	outstring(1, "Vecteur OA : ");
	outboolean(1, norm(a, 3)<=5);
	line();
	outstring(1, "Vecteur OB : ");
	outboolean(1, norm(b, 3)<=5);
	line();
	outstring(1, "Calcul des 10 premiers valeurs de la suite de fibonacci (procedure recursive) :");
	line();
	for i:=1 step 1 until 10 do
	begin
		outinteger(1, fib(i));
	end;
	line();
	outstring(1, "Visibilite et utilisations d'etiquettes dans differents blocs (utilisation du switch) :");
	line();

	goto fruits_jaune[1];
	
	
	banane:;
	begin
		outstring(1, "Coucou, je suis dans banane, je vais aller voir coco");
		line();
		goto coco;
	end;
	coco:;
	begin 
		goto abricot;
		outstring(1, "Coucou, je suis dans coco, je vais aller voir abricot !");
		line();
		abricot:;
		begin
			outstring(1, "Coucou, je suis dans abricot, je vais aller voir citron. J'ai saute coco !");
			line();

			goto fruits_jaune[2];
		end;
	end;
	citron:;
		begin
			outstring(1, "Coucou, je suis dans citron. Les fruits c'est pas bon !");
		end;
	
end
