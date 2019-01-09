grammar VisualBasic;
@header {
//    package pt.isep.nsheets.shared.core.formula.compiler;
}

macro: line+;

line: comparison;

comparison:
	concatenation (
		(EQ | NEQ | GT | LT | LTEQ | GTEQ) concatenation
	)?;

concatenation: (MINUS)? atom
	| concatenation PERCENT
	| <assoc = right> concatenation POWER concatenation
	| concatenation ( MULTI | DIV) concatenation
	| concatenation ( PLUS | MINUS) concatenation
	| concatenation AMP concatenation
	| ifexpr
	| forexpr;

atom:
    macro_call
	| function_call
	| reference
	| literal
	| LPAR comparison RPAR
	| attribution;

attribution: CELL_REF ATTRIB concatenation;

function_call:
	FUNCTION LPAR (comparison ( COMMA comparison)*)? RPAR;

macro_call:
    MACRO_START LPAR (STRING) (SEMI literal)* RPAR;

ifexpr:
	IF LPAR (comparison) RPAR THEN comparison (ELSE comparison)? ENDIF;

forexpr:
	FOR NUMBER TO NUMBER (STEP NUMBER)? (concatenation SEMI)* concatenation ENDFOR;


reference: CELL_REF ( ( COLON) CELL_REF)?;

literal: NUMBER | STRING;

fragment LETTER: ('a' ..'z' | 'A' ..'Z');

FUNCTION: 'func.' (LETTER)+;

CELL_REF: ( ABS)? LETTER ( LETTER)? ( ABS)? ( DIGIT)+;

/* String literals, i.e. anything inside the delimiters */

STRING: QUOT ('\\"' | ~'"')* QUOT;

QUOT: '"';

/* Numeric literals */
NUMBER: ( DIGIT)+ ( COMMA ( DIGIT)+)?;

fragment DIGIT: '0' ..'9';

/* Comparison operators */
EQ: '=';
NEQ: '<>';
LTEQ: '<=';
GTEQ: '>=';
GT: '>';
LT: '<';

/* Text operators */
AMP: '&';

/* Arithmetic operators */
PLUS: '+';
MINUS: '-';
MULTI: '*';
DIV: '/';
POWER: '^';
PERCENT: '%';

/* Reference operators */
fragment ABS: '$';
fragment EXCL: '!';
COLON: ':';
ATTRIB: ':=';

/* Miscellaneous operators */
COMMA: ',';
SEMI: ';';
LPAR: '(';
RPAR: ')';
LCURLYBRACKET: '{';
RCURLYBRACKET: '}';

/* White-space (ignored) */
WS: ( ' ' | '\r' | '\n' | '\t') -> skip;

LINECOMMENT: DOUBLESLASH ~[\r\n]* -> skip;

/* Comment */
DOUBLESLASH: '//';

/* COMMANDS */
IF: 'If'|'if';
THEN: 'Then'|'then';
ELSE: 'Else'|'else';
ENDIF: 'EndIf'|'endif';
FUNC: 'func.';
FOR: 'For'|'for';
ENDFOR: 'EndFor'|'endfor';
TO: 'To'|'to';
STEP: 'Step'|'STEP'|'step';
MACRO_START: 'call';