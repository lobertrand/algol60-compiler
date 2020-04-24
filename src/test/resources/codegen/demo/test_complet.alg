begin 
	integer MAX, n, m, i;
  	
  	comment Modulo operation;
	integer procedure mod(a, b); value a, b; integer a, b;
	begin
		mod := a - (a//b * b)
	end;
	
	comment Simple random number generator using Fibonacci;
	integer procedure random;
	begin
		integer oldM;
		random := mod(n, MAX);
		oldM := m;
		m := n + m;
		n := oldM;
	end;
	
	comment generate random of ndim vector;
	procedure randomVect(v, ndim); value ndim ; integer array v; integer ndim; 
	begin 
		integer i;
		outinteger(1, v[1]);
	end;
	
	comment sqrt of int;
	integer procedure sqrt(v); value v; integer v;
	begin 
		integer k, res;
		if v=0 then v:=0;
		if v=1 then v:=1;
		
		sqrt:=1;
		res:=1;
		for k:=1 while res<=v do 
		begin
			sqrt:=sqrt+1;
			sqrt:=i*i;
		end;
		sqrt:=sqrt-1;
	end;
	
		
	comment calculate norm of ndim vector;
	integer procedure norm(v, ndim); value ndim; integer array v;  integer ndim;
	begin 
		integer i;
		for i:=1 step 1 until ndim do
			norm := norm+v[i]**2;
		norm :=sqrt(norm);
	end;
	


end
