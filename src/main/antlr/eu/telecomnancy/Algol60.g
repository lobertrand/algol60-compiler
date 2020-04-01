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
    WHILE;              // While
    SWITCH_DEC;         // Switch Declaration
    SWITCH_CALL;        // Switch Call
}

@parser::header {
package eu.telecomnancy;
import eu.telecomnancy.ast.DefaultAST;
}

@lexer::header {
package eu.telecomnancy;
import eu.telecomnancy.syntax.InvalidNumberException ;
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
    :   'goto'! identifier! goto_statement_end[$identifier.tree]
    ;

goto_statement_end[DefaultAST id]
    :   -> ^(GOTO {$id})
    |   '[' arithmetic_expression ']' -> ^(SWITCH_CALL {$id} arithmetic_expression)
    ;

label_dec_end[DefaultAST id]
    :   ':' -> ^(LABEL_DEC {$id})
    ;

// Declaration

declaration
    :   TYPE! type_declaration_end[$TYPE]
    |   procedure_declaration_no_type
    |   switch_declaration
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
    
// Switch declaration
switch_declaration
    :   'switch' identifier ':=' identifier_list
        -> ^(SWITCH_DEC identifier identifier_list)
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
    :   ( specifier identifier_list ';' )*
        -> ^(SPEC_PART ^(ARG_TYPE specifier identifier_list)*)
    ;

specifier
    :   TYPE ARRAY?
        { if ($ARRAY != null) $TYPE.setText($TYPE.text + "_array"); }
        -> TYPE
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
    |   number
    |   identifier! id_expression_end[$identifier.tree]
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
    :   implication (EQUIVALENT^ implication)*
    ;

implication
    :   or_expr (IMPLY^ or_expr)*
    ;

or_expr
    :   and_expr (OR^ and_expr)*
    ;

and_expr
    :   not_expr (AND^ not_expr)*
    ;

not_expr
    :   comparison
    |   NOT not_expr -> ^(NOT not_expr)
    ;

comparison
    :   add_expr (( LESS_THAN | LESS_EQUAL 
                  | EQUAL | GREATER_EQUAL 
                  | GREATER_THAN | NOT_EQUAL )^ add_expr)*
    ;

add_expr
    :   mult_expr (( ADD | MINUS )^ mult_expr)*
    ;

mult_expr
    :   pow_expr (( MULT | DIV | INT_DIV )^ pow_expr)*
    ;

pow_expr
    :   a=group_expr
        (   POW b=group_expr    -> ^(POW $a $b)
        |                       -> $a
        )
    ;

group_expr
    :   expression
    |   '('! arithmetic_expression ')'!
    ;

// If expression

if_expression
    :   'if' a=arithmetic_expression 'then' b=arithmetic_expression (options{greedy=true;}:'else' c=expression)
        -> ^(IF_EXPRESSION
                ^(IF_DEF $a)
                ^(THEN_DEF $b)
                ^(ELSE_DEF $c)
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

number
    :   d1=MINUS?
        (   i1=INT  { if ($d1 != null) $i1.setText("-" + $i1.text); }
            (   '#' d2=MINUS? i2=INT
                { if ($d2 != null) $i2.setText("-" + $i2.text); }
                -> ^(POW_10 INT INT)
            |                   
                -> INT  
            )
        |   REAL    { if ($d1 != null) $REAL.setText("-" + $REAL.text); }
            (   '#' d3=MINUS? i3=INT
                { if ($d3 != null) $i3.setText("-" + $i3.text); }
                -> ^(POW_10 REAL INT)
            |   
                -> REAL
            )
        )
    ;

string
    :   STR
    ;

identifier
    :   IDENTIFIER //-> ^(ID IDENTIFIER)
    ;

// LEXER RULES

MINUS   : '-'  ;
ADD     : '+'  ;
DIV     : '/'  ;
INT_DIV : '//' ;
MULT    : '*'  ;
POW     : '**' ;

EQUIVALENT : '<=>' ;
IMPLY      : '=>'  ;
OR         : '\\/' ;
AND        : '/\\' ;
NOT        :  '~'  ;

LESS_THAN     : '<'  ;
LESS_EQUAL    : '<=' ;
EQUAL         : '='  ;
GREATER_EQUAL : '>=' ;
GREATER_THAN  : '>'  ;
NOT_EQUAL     : '<>' ;

ARRAY
    :   'array'
    ;

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
        {
            int i = 0;
            try {
                i = Integer.parseInt(getText());
            } catch(NumberFormatException e) {
                exceptions.add(new InvalidNumberException("Integer size exceeded : " + getText(),input));
            }
        }
    |   '0'
    ;

REAL:   ('0'..'9')*'.'('0'..'9')*
    {
        float i = 0;
        try {
            i = Float.parseFloat(getText());
            if (i == Float.POSITIVE_INFINITY) {
                exceptions.add(new InvalidNumberException("Real size exceeded : " + getText(),input));
            }
        } catch(NumberFormatException e) {
            exceptions.add(new InvalidNumberException("Real size exceeded : " + getText(),input));
        }
    }
    ;

IDENTIFIER
    :   ('a'..'z'|'A'..'Z'|'_')('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
    ;

WS  :   (' '|'\t'|'\r'|'\n')+
        // Ignore whitespace (not in the AST)
        { $channel=HIDDEN; }
    ;
