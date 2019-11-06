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
    PROC_DEC;
    PROC_HEADING;
    PARAMS_DEC;
    ID_LIST;
    VALUE_PART;
    PARAM_PART;
    SPEC_PART;
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
    |   procedure_declaration
    ;

variable_declaration
    :   TYPE IDENTIFIER ';' -> ^(VAR_DEC TYPE IDENTIFIER)
    ;

procedure_declaration
    :   'procedure' procedure_heading procedure_body
        -> ^(PROC_DEC procedure_heading procedure_body)
    ;

procedure_heading
    :   IDENTIFIER formal_parameter_part ';' value_part specification_part
        -> ^(PROC_HEADING formal_parameter_part value_part specification_part)
    ;

formal_parameter_part    
    :   '(' identifier_list ')' ->^(PARAM_PART identifier_list)
    |    
    ;

identifier_list
    :   IDENTIFIER ( ',' IDENTIFIER )* -> ^(ID_LIST IDENTIFIER*)
    ;

value_part
    :   'value' identifier_list ';' -> ^(VALUE_PART identifier_list)
    |
    ;

specification_part
    :   (TYPE identifier_list ';')* ->^(SPEC_PART identifier_list*)
    ;

procedure_body
    :   block
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
    :   'comment' ~( ';' )* ';'
        // Ignore comments (not in the AST)
        { $channel=HIDDEN; }
    ;

STRING
    :   '"' ~( '"' | '\r' | '\n' )* '"'
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
