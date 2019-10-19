grammar Expr;

@parser::header {
package eu.telecomnancy;
import java.util.HashMap;
}

@parser::members {
/** Map variable name to Integer objet holding value */
HashMap<String,Integer> memory = new HashMap<String,Integer>();
}

@lexer::header {
package eu.telecomnancy;
}

prog:   stat+
    ;

stat:   expr NEWLINE {System.out.println($expr.value);}
    |   ID '=' expr NEWLINE
        {memory.put($ID.text, new Integer($expr.value));}
    |   NEWLINE
    ;

expr returns [int value]
    :   e=multExpr {$value = $e.value;}
        (   '+' e=multExpr {$value += $e.value;}
        |   '-' e=multExpr {$value -= $e.value;}
        )*
    ;

multExpr returns [int value]
    :   e=atom {$value = $e.value;} ('*' e=atom {$value *= $e.value;})*
    ;

atom returns [int value]
    :   INT {$value = Integer.parseInt($INT.text);}
    |   ID
        {
            Integer v = (Integer)memory.get($ID.text);
            if ( v!=null ) $value = v.intValue();
            else System.err.println("undefined variable "+$ID.text);
        }
    |   '(' expr ')' {$value = $expr.value;}
    ;

ID  : ('a'..'z'|'A'..'Z')+ ;
INT : '0'..'9'+ ;
NEWLINE:'\r'? '\n' ;
WS  : (' '|'\t')+ {$channel=HIDDEN;}; /* pour la version 3.5.2 */
