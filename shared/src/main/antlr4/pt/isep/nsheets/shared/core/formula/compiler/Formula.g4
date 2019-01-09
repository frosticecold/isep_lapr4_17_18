grammar Formula;
@header {
//    package pt.isep.nsheets.shared.core.formula.compiler;
}

expression
	: EQ comparison /* EOF */
	;

comparison
	: concatenation
		( ( EQ | NEQ | GT | LT | LTEQ | GTEQ ) concatenation )?
	;

assign:
    tempvar ATTRIB comparison
    | tempvar ATTRIB assign;

concatenation
        : ( MINUS )? ( atom | assign )
        | concatenation PERCENT
        | <assoc=right> concatenation POWER concatenation
        | concatenation ( MULTI | DIV ) concatenation
        | concatenation ( PLUS | MINUS ) concatenation
        | concatenation AMP concatenation
        ;

tempvar:
    TVAR;

atom
	:	function_call
	|	reference
	|	literal
	|	LPAR comparison RPAR
	|	block
	|	attribution
	;

block
	:	LBRACKET comparison ( SEMI comparison )* RBRACKET
	;

attribution
	: 	( reference | VAR | GVAR ) ATTRIB concatenation
	;

function_call
	:	FUNCTION (LPAR|LBRACKET)
		( comparison ( SEMI comparison )* )?
		(RPAR|RBRACKET)
	;

reference
	:	CELL_REF
		( ( COLON ) CELL_REF )?
	;

literal
	:	NUMBER
	|	STRING
	;

fragment LETTER: ('a'..'z'|'A'..'Z') ;

VAR: UNDER LETTER (LETTER | NUMBER)* ;

GVAR: AT LETTER (LETTER | NUMBER)* ;

FUNCTION :
	  ( LETTER )+
	;


CELL_REF
	:
		( ABS )? LETTER ( LETTER )?
		( ABS )? ( DIGIT )+
	;

/* String literals, i.e. anything inside the delimiters */

STRING  : QUOT ('\\"' | ~'"')* QUOT
        ;


TVAR: '_'[a-zA-Z0-9]+;


QUOT: '"'
	;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

fragment
DIGIT : '0'..'9' ;

/* Comparison operators */
EQ		: '=' ;
NEQ		: '<>' ;
LTEQ	: '<=' ;
GTEQ	: '>=' ;
GT		: '>' ;
LT		: '<' ;

/* Text operators */
AMP		: '&' ;

/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV		: '/' ;
POWER	: '^' ;
PERCENT : '%' ;

/* Reference operators */
fragment ABS : '$' ;
fragment EXCL:  '!'  ;
COLON	: ':' ;
ATTRIB : ':=' ;
HASHTAG : '#' ;

/* Miscellaneous operators */
COMMA	: ',' ;
SEMI	: ';' ;
LPAR	: '(' ;
RPAR	: ')' ;
LBRACKET: '{' ;
RBRACKET: '}' ;

UNDER	: '_' ;
AT : '@' ;

//
CURRENCY_HEADER : ('#euro' | '#dollar' | '#pound') ;
EURO : '#euro';
CURRENCY : ('$' | '£' | '€') ;


/* White-space (ignored) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;
