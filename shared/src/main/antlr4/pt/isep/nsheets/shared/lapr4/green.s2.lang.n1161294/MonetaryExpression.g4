grammar MonetaryExpression;
@header {
//    package pt.isep.nsheets.shared.core.formula.compiler;
}

expression
    :   STARTER monetary_formula
    ;

monetary_formula
    :   FINAL_CURRENCY LBRACKET operations RBRACKET
    ;

operations
    :   currency
    |   monetary_formula
    |   LPAR operations RPAR
    |   right_multiplication
    |   operations (MULTI) NUMBER
    |   operations (DIV) NUMBER //NZNUMBER
    |   operations (PLUS | MINUS) operations
    ;

right_multiplication
    :   NUMBER (MULTI) (currency | monetary_formula| LPAR operations RPAR)
    ;

currency
    :   NUMBER CURRENCY
    ;

/* Numeric literals */

NUMBER      : ( DIGIT )+ ( POINT DIGIT DIGIT ) ;
NZNUMBER    : ('1'..'9')('0'..'9')POINT('0'..'9')('0'..'9');

/* Miscellaneous operators */
LPAR	: '(' ;
RPAR	: ')' ;
LBRACKET: '{' ;
RBRACKET: '}' ;
POINT	: '.' ;
/* Arithmetic operators */
PLUS	: '+' ;
MINUS	: '-' ;
MULTI	: '*' ;
DIV		: '/' ;

STARTER : '#' ;
FINAL_CURRENCY : ('euro'|'dollar'|'pound');
CURRENCY : ('e'|'$'|'£');



/* Miscellaneous operators */
fragment DIGIT : '0'..'9' ;

/* White-space (ignor ed) */
WS: ( ' ' | '\r' '\n' | '\n' | '\t' ) -> skip ;