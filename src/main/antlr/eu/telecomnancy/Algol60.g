/*
    ANTLR grammar and AST based on the original Algol60 grammar.
    Developped by Loïc Bertrand, Timon Fugier, Tony Zhou and
    Zineb Ziani El Idrissi. 
*/

grammar Algol60;

options {
    output = AST;
    backtrack=false;
    k=1;
    ASTLabelType=DefaultAST;
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
    LABEL_DEC;          // Label declaration
    GOTO;               // Goto statement
    POW;                // Power
    POW_10;             // Scientific notation
    ARRAY_CALL;         // Access to a value of an array
    STR;                // String
    AND;                // Logical and
    OR;                 // Logical or
    IMPLY;              // Logical implication
    EQUIVALENT;         // Logical equivalence
    GREATER_THAN;       // Greater than
    LESS_THAN;          // Less than
    GREATER_EQUAL;      // Great than or equal
    LESS_EQUAL;      // Less than or equal
    EQUAL;              // Equal
    NOT_EQUAL;          // Not equal
}

@parser::header {
package eu.telecomnancy;
import eu.telecomnancy.ast.DefaultAST;
}

@lexer::header {
package eu.telecomnancy;
}

@parser::members {
private List<RecognitionException> exceptions = new ArrayList<>();
public List<RecognitionException> getExceptions() { return exceptions; }
public boolean hasExceptions() { return !exceptions.isEmpty(); }
@Override
public void reportError(RecognitionException e) { exceptions.add(e); }
}

@lexer::members {
private List<RecognitionException> exceptions = new ArrayList<>();
public List<RecognitionException> getExceptions() { return exceptions; }
public boolean hasExceptions() { return !exceptions.isEmpty(); }
@Override
public void reportError(RecognitionException e) { exceptions.add(e); }
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
    
id_statement_end[DefaultAST id]
    :   procedure_call_end[$id]
    |   assignment_end[$id]
    |   label_dec_end[$id]
    ;

// Label / goto statement

goto_statement
    :   'goto' identifier -> ^(GOTO identifier)
    ;

label_dec_end[DefaultAST id]
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
    :   identifier_list -> ^(VAR_DEC {new DefaultAST($type)} identifier_list)
    |   'array' identifier '[' boundaries(',' boundaries)* ']' 
        -> ^(ARRAY_DEC {new DefaultAST($type)} identifier ^(BOUND_LIST boundaries+))
    ;

// Procedure declaration

procedure_declaration
    :   TYPE? procedure_declaration_end[$TYPE]
    ;

procedure_declaration_end[Token type]
    :   'procedure' procedure_heading procedure_body
        -> ^(PROC_DEC {new DefaultAST($type)} procedure_heading procedure_body)
    ;

procedure_declaration_no_type
    :   'procedure' procedure_heading procedure_body
        -> ^(PROC_DEC procedure_heading procedure_body)
    ;

procedure_heading
    :   identifier formal_parameter_part ';' value_part specification_part
        -> ^(PROC_HEADING identifier formal_parameter_part? value_part? specification_part?)
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

procedure_call_end[DefaultAST id]
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

assignment_end[DefaultAST id]
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

array_call_end[DefaultAST id]
    :   '[' actual_parameter_list ']'
        -> ^(ARRAY_CALL {$id} actual_parameter_list)
    ;

// Expression

expression
    :   string 
    |   integer 
    |   identifier! id_expression_end[$identifier.tree]
    |   scientific_expression
    |   LOGICAL_VALUE
    ;

id_expression_end[DefaultAST id]
    :   array_call_end[$id]
    |   procedure_call_end[$id]
    |   -> {$id}
    ;

// Arithmetic
    
arithmetic_expression
    :   term! arithmetic_expression_end[$term.tree]
    ;

arithmetic_expression_end[DefaultAST t2]
    :   '+' arithmetic_expression -> ^(ADD {$t2} arithmetic_expression?)
    |   '-' arithmetic_expression -> ^(MINUS {$t2} arithmetic_expression?)
    |   '<' arithmetic_expression -> ^(LESS_THAN {$t2} arithmetic_expression?)
    |   '<=' arithmetic_expression -> ^(LESS_EQUAL {$t2} arithmetic_expression?)
    |   '>' arithmetic_expression -> ^(GREATER_THAN {$t2} arithmetic_expression?)
    |   '>=' arithmetic_expression -> ^(GREATER_EQUAL {$t2} arithmetic_expression?)
    |   '<>' arithmetic_expression -> ^(NOT_EQUAL {$t2} arithmetic_expression?)
    |   '=' arithmetic_expression -> ^(EQUAL {$t2} arithmetic_expression?)
    |   '<=>' arithmetic_expression -> ^(EQUIVALENT {$t2} arithmetic_expression?)
    |   '=>' arithmetic_expression -> ^(IMPLY {$t2} arithmetic_expression?)
    |   '\\/' arithmetic_expression -> ^(OR {$t2} arithmetic_expression?)
    |   '/\\' arithmetic_expression -> ^(AND {$t2} arithmetic_expression?)
    // |   '~' arithmetic_expression -> ^(NOT {$t2} arithmetic_expression?)
    |   -> {$t2}
    ;    
    
term
    :   expression! term1[$expression.tree]
    ;

term1[DefaultAST t2]
    :   '*' term -> ^(MULT {$t2} term?)
    |   '/' term -> ^(DIV {$t2} term?)
    |   '//' term -> ^(INT_DIV {$t2} term?)
    |   '**' term -> ^(POW {$t2} term?)
    |   -> {$t2}
    ;

// If clause

if_clause
    :   'if' arithmetic_expression 'then' statement (options{greedy=true;}:'else' statement)? 
        -> ^(IF_STATEMENT ^(IF_DEF arithmetic_expression) ^(THEN_DEF statement) ^(ELSE_DEF statement)*)
    ;

// For clause

for_clause
    :   'for' assignment 'step' expression 'until' expression 'do' statement 
        -> ^(FOR_CLAUSE ^(INIT assignment) ^(STEP expression) ^(UNTIL expression) ^(DO statement))
    ;

// While clause

while_clause 
    :   'while'  arithmetic_expression 'do' statement 
        -> ^(WHILE_CLAUSE ^(CONDITION arithmetic_expression) ^(DO statement))
    ;

// Intermediate parser rules	

integer
    :   DASH? INT
        // Prepends integer node with '-' if DASH is present
        { if ($DASH != null) $INT.setText("-" + $INT.text); }
        -> INT
    ;

scientific_expression
    :   REAL ( '#' INT -> ^(POW_10 REAL INT)
             |         -> REAL
             )
    ;

string
    :   STR
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

STR
    :   '"' ~( '"' | '\r' | '\n' )* '"'
        // Strips the string from its quotes in the lexer
        // https://theantlrguy.atlassian.net/wiki/spaces/ANTLR3/pages/2687006/How+do+I+strip+quotes
        { setText(getText().substring(1, getText().length() - 1)); }
    ;

LOGICAL_VALUE
    :   'true'
    |   'false'
    ;

INT
    :   ('1'..'9')('0'..'9')*
    |   '0'
    ;

DASH:   '-'
    ;

REAL 
    :   ('0'..'9')*'.'('0'..'9')*
    ;

IDENTIFIER
    :   ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

WS  :   (' '|'\t'|'\r'|'\n')+
        // Ignore whitespace (not in the AST)
        { $channel=HIDDEN; }
    ;