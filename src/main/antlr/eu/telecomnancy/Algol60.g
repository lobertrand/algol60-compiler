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
    IF_EXPRESSION;      // If expression
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
    NOT;                // Logical not
    AND;                // Logical and
    OR;                 // Logical or
    IMPLY;              // Logical implication
    EQUIVALENT;         // Logical equivalence
    GREATER_THAN;       // Greater than
    LESS_THAN;          // Less than
    GREATER_EQUAL;      // Great than or equal
    LESS_EQUAL;         // Less than or equal
    EQUAL;              // Equal
    NOT_EQUAL;          // Not equal
    WHILE;	            // While
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

// Block

block 
    :   'begin' block_head? block_tail? -> ^(BLOCK block_head? block_tail?)
    ;

block_head
    :   declaration
        (   ';'! ( block_head | block_tail | 'end'! )
        |   'end'!
        )
    ;

block_tail
    :   statement
        (   ';'! ( block_tail | 'end'! )
        |   'end'!
        )
    ;

// Statement

statement
    :   goto_statement
    |   if_clause
    |   for_clause
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
    |   if_expression
    ;

id_expression_end[DefaultAST id]
    :   array_call_end[$id]
    |   procedure_call_end[$id]
    |   -> {$id}
    ;

// Arithmetic expressions

arithmetic_expression
    :   equivalence
    ;

equivalence
    :   implication! equivalence_end[$implication.tree]
    ;

equivalence_end[DefaultAST t2]
    :   '<=>' equivalence -> ^(EQUIVALENT {$t2} equivalence?)
    |   -> {$t2}
    ;

implication
    :   or_expr! implication_end[$or_expr.tree]
    ;

implication_end[DefaultAST t2]
    :   '=>' implication -> ^(IMPLY {$t2} implication?)
    |   -> {$t2}
    ;

or_expr
    :   and_expr! or_expr_end[$and_expr.tree]
    ;

or_expr_end[DefaultAST t2]
    :   '\\/' or_expr -> ^(OR {$t2} or_expr?)
    |   -> {$t2}
    ;

and_expr
    :   not_expr! and_expr_end[$not_expr.tree]
    ;

and_expr_end[DefaultAST t2]
    :   '/\\' and_expr -> ^(AND {$t2} and_expr?)
    |   -> {$t2}
    ;

not_expr
    :   '~' not_expr -> ^(NOT not_expr?)
    |   comparison
    ;

comparison
    :   add_expr! comparison_end[$add_expr.tree]
    ;

comparison_end[DefaultAST t2]
    :   '<' comparison -> ^(LESS_THAN {$t2} comparison?)
    |   '<=' comparison -> ^(LESS_EQUAL {$t2} comparison?)
    |   '=' comparison -> ^(EQUAL {$t2} comparison?)
    |   '>=' comparison -> ^(GREATER_EQUAL {$t2} comparison?)
    |   '>' comparison -> ^(GREATER_THAN {$t2} comparison?)
    |   '<>' comparison -> ^(NOT_EQUAL {$t2} comparison?)
    |   -> {$t2}
    ;

add_expr
    :   mult_expr! add_expr_end[$mult_expr.tree]
    ;

add_expr_end[DefaultAST t2]
    :   '+' add_expr -> ^(ADD {$t2} add_expr?)
    |   '-' add_expr -> ^(MINUS {$t2} add_expr?)
    |   -> {$t2}
    ;
    
mult_expr
    :   pow_expr! mult_expr_end[$pow_expr.tree]
    ;

mult_expr_end[DefaultAST t2]
    :   '*' mult_expr -> ^(MULT {$t2} mult_expr?)
    |   '/' mult_expr -> ^(DIV {$t2} mult_expr?)
    |   '//' mult_expr -> ^(INT_DIV {$t2} mult_expr?)
    |   -> {$t2}
    ;

pow_expr
    :   group_expr! pow_expr_end[$group_expr.tree]
    ;

pow_expr_end[DefaultAST t2]
    :   '**' pow_expr -> ^(POW {$t2} pow_expr?)
    |   -> {$t2}
    ;

group_expr
    :   expression
    |   '('! arithmetic_expression ')'!
    ;

// If expression

if_expression
    :   'if' arithmetic_expression 'then' expression (options{greedy=true;}:'else' expression)
        -> ^(IF_EXPRESSION
                ^(IF_DEF arithmetic_expression)
                ^(THEN_DEF expression)
                ^(ELSE_DEF expression)
            )
    ;

// If clause

if_clause
    :   'if' arithmetic_expression 'then' statement (options{greedy=true;}:'else' statement)? 
        -> ^(IF_STATEMENT
                ^(IF_DEF arithmetic_expression)
                ^(THEN_DEF statement)
                ^(ELSE_DEF statement)?
            )
    ;

// For clause

for_clause
    :   'for'! for_assignment!  (   for_step_until_do[$for_assignment.tree]
                                |   for_while_do[$for_assignment.tree]
                                |   for_do[$for_assignment.tree]
                                )
    ;

for_assignment
    :   identifier ':='! arithmetic_expression
    ;

for_step_until_do[DefaultAST ass]
    :   'step' arithmetic_expression 'until' arithmetic_expression 'do' statement
        -> ^(FOR_CLAUSE
                ^(INIT {$ass})
                ^(STEP arithmetic_expression)
                ^(UNTIL arithmetic_expression)
                ^(DO statement)
            )
    ;

for_while_do[DefaultAST ass]
    :   'while' arithmetic_expression 'do' statement 
        -> ^(FOR_CLAUSE
                ^(INIT {$ass})
                ^(WHILE arithmetic_expression)
                ^(DO statement)
            )
    ;

for_do[DefaultAST ass]
    :   (',' arithmetic_expression)* 'do' statement
        -> ^(FOR_CLAUSE
                ^(INIT {$ass} arithmetic_expression*)
                ^(DO statement)
            )
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
