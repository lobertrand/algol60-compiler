begin
	outboolean(1, false);
	outboolean(1, true);
	
	outboolean(1, ~false);
	outboolean(1, ~true);
	
	outboolean(1, false\/false);
	outboolean(1, false\/true);
	outboolean(1, true\/false);
	outboolean(1, true\/true);
	
	outboolean(1, false/\false);
	outboolean(1, false/\true);
	outboolean(1, true/\false);
	outboolean(1, true/\true);
	
	outboolean(1, false=>false);
	outboolean(1, false=>true);
	outboolean(1, true=>false);
	outboolean(1, true=>true);
	
	outboolean(1, false<=>false);
	outboolean(1, false<=>true);
	outboolean(1, true<=>false);
	outboolean(1, true<=>true)
	
end

