grammar Algol60;

options {
	output = AST;
	backtrack=false;
	k=1;
}

tokens {
    ROOT;           // Program root
    BLOCK;          // Block of code
    VAR_DEC;        // Variable declaration
}

@parser::header {
package eu.telecomnancy;
// import java.util.HashMap;
}

// @parser::members {
// /** Map variable name to Integer objet holding value */
// HashMap<String,Integer> memory = new HashMap<String,Integer>();
// }

@lexer::header {
package eu.telecomnancy;
}

// PARSER RULES

prog:   block -> ^(ROOT block)
    ;

block
    :   'begin' statement* 'end' -> ^(BLOCK statement*)
    ;

statement
    :   COMMENT
    |   declaration
    |   assignment
    |   if_clause
    ;

// Declaration

declaration
    :   variable_declaration
    ;

variable_declaration
    :   TYPE IDENTIFIER ';' -> ^(VAR_DEC TYPE IDENTIFIER)
    ;

// Assignment

assignment
    :   IDENTIFIER ':='^ expression ';'!
    ;

expression
    :   INTEGER
    |   STRING
    |   IDENTIFIER
    ;

if_clause
    :   'if' logical_statement 'then' ';'!
    ;

logical_statement
    :   expression boolean_operator expression
    |   LOGICAL_VALUE
    ;

boolean_operator
    :   RELATIONAL_OPERATOR
    |   LOGICAL_OPERATOR
    ;

// LEXER RULES

TYPE:   'real'
    |   'integer'
    |   'boolean'
    |   'string'
    ;

COMMENT
    :   'comment' ~( ';' | '\r' | '\n' )* ';'
        // Ignore comments (not in the AST)
        { $channel=HIDDEN; }
    ;

STRING
    :   '\'' ~( '`' | '\r' | '\n' )* '`'
        // Strips the string from its quotes in the lexer
        // https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687006/How+do+I+strip+quotes
        { setText(getText().substring(1, getText().length() - 1)); }
    ;

KEYWORD
    :   'if'
    |   'then'
    |   'else'
    |   'for'
    |   'do'
    |   'goto'
    ;

LOGICAL_VALUE
    :   'true'
    |   'false'
    ;


IDENTIFIER
    :   ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

ARITHMETIC_OPERATOR
    :   '+'
    |   '-'
    |   '*'
    |   '/'
    |   '//'
    |   '**'
    ;
    
RELATIONAL_OPERATOR
    :   '<'
    |   '<='
    |   '='
    |   '<>'
    |   '>'
    |   '>='
    ;

LOGICAL_OPERATOR
    :   '<=>'
    |   '=>'
    |   '\\/'
    |   '/\\'
    |   '~'
    ;

INTEGER
    :   ('+'|'-')?('1'..'9')('0'..'9')*
    ;


WS  :   (' '|'\t'|'\r'|'\n')+
        // Ignore whitespace (not in the AST)
        { $channel=HIDDEN; }
    ;
