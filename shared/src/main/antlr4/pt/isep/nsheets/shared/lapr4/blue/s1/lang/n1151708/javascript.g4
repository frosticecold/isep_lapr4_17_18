grammar javascript;
@header {
//    package pt.isep.nsheets.shared.core.formula.compiler;
}
macro: line+;

line: comparison;

	
comparison
	: concatenation
		( ( DOUBLEEQ | EQ | NEQ | GT | LT | LTEQ | GTEQ ) concatenation )? 	
	;

concatenation
        : ( MINUS )? atom                                       
        | concatenation PERCENT
        | <assoc=right> concatenation POWER concatenation
        | concatenation ( MULTI | DIV ) concatenation
        | concatenation ( PLUS | MINUS ) concatenation
		| ifexpression 
		| forexpression
        | concatenation AMP concatenation
		
        ;

atom
	:	function_call
	|	reference
	|	literal
	|	LPAR comparison RPAR
	|	attribution
	;

attribution
	: CELL_REF ATTRIB concatenation
	;

function_call
	:	
	FUNCTION LPAR comparison RPAR  ( LCURLYBRACKETS ( comparison ( SEMI comparison )* )? RCURLYBRACKETS )? 
		
	;

ifexpression :  IF LPAR comparison RPAR LCURLYBRACKETS comparison ( SEMI comparison ) ? RCURLYBRACKETS ;

forexpression : FOR LPAR attribution SEMI comparison SEMI attribution RPAR LCURLYBRACKETS comparison ( SEMI comparison)* RCURLYBRACKETS ;

reference
	:	CELL_REF
		( ( COLON ) CELL_REF )?
	;

literal
	:	NUMBER
	|	STRING
	;
	

fragment LETTER: ('a'..'z'|'A'..'Z') ;
  
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


QUOT: '"' 
	;

/* Numeric literals */
NUMBER: ( DIGIT )+ ( COMMA ( DIGIT )+ )? ;

fragment 
DIGIT : '0'..'9' ;

/* Comparison operators */
EQ		: '=' ;
DOUBLEEQ : '==' ;
NEQ		: '!=' ;
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
LCURLYBRACKETS	: '{' ;
RCURLYBRACKETS	: '}' ;

/* Function Names */

IF : '.IF' ;
FOR : '.FOR' ;

/* White-space (ignored) */
WS: ( ' ' | '\r' | '\n' | '\t') -> skip;

LINECOMMENT: DOUBLESLASH ~[\r\n]* -> skip;

/* Comment */
DOUBLESLASH: '//' ;