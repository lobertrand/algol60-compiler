begin
	outboolean(1, 1>0);
	outboolean(1, 1>1);
	outboolean(1, 0>1);
	
	outboolean(1, 1>=0);
	outboolean(1, 1>=1);
	outboolean(1, 0>=1);
	
	outboolean(1, 0<1);
	outboolean(1, 0<0);
	outboolean(1, 1<0);
	
	outboolean(1, 0<=1);
	outboolean(1, 0<=0);
	outboolean(1, 1<=0);
	
	outboolean(1, 0=0);
	outboolean(1, 0=1);
	
	outboolean(1, 0<>1);
	outboolean(1, 0<>0)
	
end

