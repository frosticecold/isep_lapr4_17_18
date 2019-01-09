grammar MacroExcel;
@header {
//    package pt.isep.nsheets.shared.core.formula.compiler;
}

macro: line+;

line :  expression;

expression
	: comparison
	;
	
comparison
	: concatenation
		( ( EQ | NEQ | GT | LT | LTEQ | GTEQ ) concatenation )? 
	;

concatenation
        : ( MINUS )? atom                                       
        | concatenation PERCENT
        | <assoc=right> concatenation POWER concatenation
        | concatenation ( MULTI | DIV ) concatenation
        | concatenation ( PLUS | MINUS ) concatenation
        | concatenation AMP concatenation 
        ;


atom
	:       macro_c 
        |       function_call
	|	reference
	|	literal
	|	LPAR comparison RPAR
	|	block
	|	attribution
	;

block
	:	LBRACKET comparison (SEMI comparison)* RBRACKET
	;

attribution
	: 	( reference | VAR ) ATTRIB concatenation
	;

function_call
	:	FUNCTION LPAR
		( comparison ( SEMI comparison )* )?

		(RPAR|RBRACKET)
                |macro_c

		RPAR

	;
macro_c
        :
        MACRO LPAR STRING  RPAR
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

FUNCTION : 
	  '=' ( LETTER )+ 
	;	
	 
 
CELL_REF
	:
		( ABS )? LETTER ( LETTER )?
		( ABS )? ( DIGIT )+
	;

/* String literals, i.e. anything inside the delimiters */

STRING  : QUOT ('\\"' | ~'"')* QUOT
        ;


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
 
/* Miscellaneous operators */
COMMA	: ',' ;
SEMI	: ';' ;
LPAR	: '(' ;
RPAR	: ')' ;
LBRACKET: '{' ;
RBRACKET: '}' ;
STARTER : '|' ;
MACRO :'macro';
UNDER	: '_' ; 

/* White-space (ignored) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;
	
/* comment line (ignored) */
LINECOMMENT: SEMI ~[\r\n]* -> skip;	