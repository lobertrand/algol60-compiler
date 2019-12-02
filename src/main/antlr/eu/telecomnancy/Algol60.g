/*
    ANTLR grammar and AST based on the original Algol60 grammar.
    Developped by  Loïc Bertrand, Timon Fugier, Tony Zhou and 
    Zineb Ziani El Idrissi. 
*/

grammar Algol60;

options {
    output = AST;
    backtrack=false;
    k=1;
    ASTLabelType=CommonTree;
}

tokens {
    ROOT;               // Program root
    BLOCK;              // Block of code
    VAR_DEC;            // Variable declaration
    PROC_DEC;           // Procedure declaration
    PROC_HEADING;       // Procedure heading
    PARAMS_DEC;         // Parameters
    ID_LIST;            // List of identifiers
    VALUE_PART;         // Procedure's parameters passed by value
    PARAM_PART;         // Procedure's parameters passed by reference
    SPEC_PART;          // Procedure's parameters names
    ARG_TYPE;           // Type of a procedure's argument
    PARAM_LIST;         // List of parameters
    PROC_CALL;          // Procedure call
    IF_STATEMENT;       // If statement
    IF_DEF;             // First part of an if statement
    THEN_DEF;           // Then part of an if statement
    ELSE_DEF;           // Else part of an if statement
    FOR_CLAUSE;         // For loop
    INIT;               // Initialization part of for loop
    STEP;               // Step part of for loop
    UNTIL;              // Condition part of for loop
    DO;                 // Code part of for loop
    WHILE_CLAUSE;       // While loop
    CONDITION;          // While loop condition
    ASSIGNMENT;         // Assignment
    ARRAY_DEC;          // Array Declaration
    BOUND_DEC;          // Declaration of the boundaries of an array
    BOUND_LIST;         // List of BOUND_DEC
    ARRAY_ASSIGNMENT;   // Assignment of an array
    INDICES;            // Indices of an element of an array
    MULT;               // Multiplication
    DIV;                // Division
    INT_DIV;            // Integer division
    ADD;                // Addition
    MINUS;              // Substraction
    TERM;               // Term in expression
    FACTOR;             // Factor in expression
    LABEL_DEC;          // Label declaration
    GOTO;               // Goto statement
    POW;                // Power
    INT;                // Integer
    POW_10;             // Scientific notation
    ARRAY_CALL;         // Access to a value of an array
    STR;                // String
    REAL;               // Real number
}

@parser::header {
package eu.telecomnancy;
}

@lexer::header {
package eu.telecomnancy;
}

// PARSER RULES

prog:   block EOF -> ^(ROOT block)
    ;

block 
    :   'begin' statement (';' statement)* 'end' -> ^(BLOCK statement+)
    ;

// Statement

statement
    :   declaration
    |   goto_statement
    |   if_clause
    |   for_clause
    |   while_clause
    |   block
    |   identifier! id_statement_end[$identifier.tree]
    ;
    
id_statement_end[CommonTree id]
    :   procedure_call_end[$id]
    |   assignment_end[$id]
    |   label_dec_end[$id]
    ;

// Label / goto statement

goto_statement
    :   'goto' identifier -> ^(GOTO identifier)
    ;

label_dec_end[CommonTree id]
    :   ':' -> ^(LABEL_DEC {$id})
    ;

// Declaration

declaration
    :   TYPE! type_declaration_end[$TYPE]
    |   procedure_declaration_no_type
    ;

type_declaration_end[Token type]
    :   variable_declaration_end[$type]
    |   procedure_declaration_end[$type]
    ;

variable_declaration
    :   TYPE! identifier_list_head[$TYPE]
    ;

variable_declaration_end[Token type]
    :   identifier_list_head[$type]
    ;
    
identifier_list_head[Token type]
    :   identifier_list -> ^(VAR_DEC {new CommonTree($type)} identifier_list)
    |   'array' identifier '[' boundaries(',' boundaries)* ']' 
        -> ^(ARRAY_DEC {new CommonTree($type)} identifier ^(BOUND_LIST boundaries+))
    ;

// Procedure declaration

procedure_declaration
    :   TYPE? procedure_declaration_end[$TYPE]
    ;

procedure_declaration_end[Token type]
    :   'procedure' procedure_heading procedure_body
        -> ^(PROC_DEC {new CommonTree($type)} procedure_heading procedure_body)
    ;

procedure_declaration_no_type
    :   'procedure' procedure_heading procedure_body
        -> ^(PROC_DEC procedure_heading procedure_body)
    ;

procedure_heading
    :   identifier formal_parameter_part ';' value_part specification_part
        -> ^(PROC_HEADING formal_parameter_part? value_part? specification_part?)
    ;

formal_parameter_part   
    :   '(' identifier_list ')' -> ^(PARAM_PART identifier_list)
    |   
    ;

identifier_list
    :   identifier ( ',' identifier )* -> ^(ID_LIST identifier*)
    ;

value_part
    :   'value' identifier_list ';' -> ^(VALUE_PART identifier_list)
    |
    ;

specification_part
    :   ( TYPE identifier_list ';' )*
        -> ^(SPEC_PART ^(ARG_TYPE TYPE identifier_list)*)
    ;

procedure_body
    :   block
    ;

// Procedure call

procedure_call
    :   identifier! procedure_call_end[$identifier.tree]
    ;

procedure_call_end[CommonTree id]
    :   '(' actual_parameter_list ')'
        -> ^(PROC_CALL {$id} actual_parameter_list)
    ;

actual_parameter_list
    :   arithmetic_expression ( ',' arithmetic_expression )*
        -> ^(PARAM_LIST arithmetic_expression*)
    |   -> ^(PARAM_LIST)
    ;

// Assignment

assignment
    :   identifier! assignment_end[$identifier.tree]
    ;

assignment_end[CommonTree id]
    :   ':=' arithmetic_expression -> ^(ASSIGNMENT {$id} arithmetic_expression)
    |   '[' bound (',' bound)* ']' ':=' arithmetic_expression
        -> ^(ARRAY_ASSIGNMENT {$id} ^(INDICES bound+) arithmetic_expression)
    ;


boundaries
    :   bound ':' bound -> ^(BOUND_DEC bound bound)
    ;

bound
    :   arithmetic_expression
    ;

array_call_end[CommonTree id]
    :   '[' actual_parameter_list ']'
        -> ^(ARRAY_CALL {$id} actual_parameter_list)
    ;

// Expression

expression
    :   string 
    |   integer 
    |   identifier! id_expression_end[$identifier.tree]
    |   scientific_expression
    ;

id_expression_end[CommonTree id]
    :   array_call_end[$id]
    |   procedure_call_end[$id]
    |   -> {$id}
    ;

// Arithmetic
    
arithmetic_expression
    :   term! arithmetic_expression_end[$term.tree]
    ;

arithmetic_expression_end[CommonTree t2]
    :   '+' arithmetic_expression -> ^(ADD {$t2} arithmetic_expression?)
    |   '-' arithmetic_expression -> ^(MINUS {$t2} arithmetic_expression?)
    |   -> {$t2}
    ;    
    
term
    :   expression! term1[$expression.tree]
    ;

term1[CommonTree t2]
    :   '*' term -> ^(MULT {$t2} term?)
    |   '/' term -> ^(DIV {$t2} term?)
    |   '//' term -> ^(INT_DIV {$t2} term?)
    |   '**' term -> ^(POW {$t2} term?)
    |   -> {$t2}
    ;

// If clause

if_clause
    :   'if' logical_statement 'then' statement (options{greedy=true;}:'else' statement)? 
        -> ^(IF_STATEMENT ^(IF_DEF logical_statement) ^(THEN_DEF statement) ^(ELSE_DEF statement)*)
    ;

// For clause

for_clause
    :   'for' assignment 'step' expression 'until' expression 'do' statement 
        -> ^(FOR_CLAUSE ^(INIT assignment) ^(STEP expression) ^(UNTIL expression) ^(DO statement))
    ;

// While clause

while_clause 
    :   'while'  logical_statement 'do' statement 
        -> ^(WHILE_CLAUSE ^(CONDITION logical_statement) ^(DO statement))
    ;

logical_statement
    :   arithmetic_expression! logical_statement_end[$arithmetic_expression.tree]
    |   LOGICAL_VALUE
    ;
logical_statement_end[CommonTree t2]
   :  boolean_operator arithmetic_expression-> ^(boolean_operator {$t2} arithmetic_expression)
   ;

//logical_statement
//    :   arithmetic_expression boolean_operator arithmetic_expression -> ^(boolean_operator ^(arithmetic_expression) ^(arithmetic_expression))
//    |   LOGICAL_VALUE
//    ;
boolean_operator
    :   RELATIONAL_OPERATOR
    |   LOGICAL_OPERATOR
    ;

// Intermediate parser rules	

integer
    :   '-'? INTEGER -> ^(INT '-'? INTEGER)
    ;

scientific_expression
    :   REAL! scientific_expression_end[$REAL]
    ;

scientific_expression_end[Token real]
    :   '#' INTEGER -> ^(POW_10 {new CommonTree($real)} INTEGER)
    |   -> ^(REAL {new CommonTree($real)})
    ;

string
    :   STRING -> ^(STR STRING)
    ;

identifier
    :   IDENTIFIER //-> ^(ID IDENTIFIER)
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

LOGICAL_VALUE
    :   'true'
    |   'false'
    ;

INTEGER
    :   ('1'..'9')('0'..'9')*
    |   '0'
    ;

REAL 
    :   ('0'..'9')*'.'('0'..'9')*
    ;

IDENTIFIER
    :   ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
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

WS  :   (' '|'\t'|'\r'|'\n')+
        // Ignore whitespace (not in the AST)
        { $channel=HIDDEN; }
    ;
