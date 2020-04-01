begin
    integer a;
    nomlabel1 :;
    a := a + 1;
    outinteger(1,a);
    goto nomlabel3;
    nomlabel2 :;
    a := a + 100;
    outinteger(1,a);
    goto nomlabel1;
    nomlabel3 :;
    a := a + 10;
    outinteger(1,a);
    goto nomlabel2;
end