grammar Imp;

parse
	: (procDecl)+ EOF
	;

procDecl
	: 'method' ID LPAREN formalParameters? RPAREN returnsBlock? conditionBlock block
	;

formalParameters
	: formalParameter (',' formalParameter)*
	;

formalParameter
	: type ID
	;

returnsBlock
	: RETURNS LPAREN formalParameter RPAREN
	;

conditionBlock
	: (requiresClause | ensuresClause)*
	;

requiresClause
	: REQUIRES expr
	;

ensuresClause
	: ENSURES expr
	;

statement
	: assignStatement                                            # AssignStmt
	| ifStatement                                                # IfStmt
	| whileStatement                                             # WhileStmt
	| block                                                      # BlockStmt
	| varDecl                                                    # VarDeclStmt
	| expr SEMICOLON                                             # ExprStmt
	;

block
	: LBRACE statement* RBRACE
	;

ifStatement
	: IF parenthesizedCondition block (ELSE block)?
	;

whileStatement
	: WHILE parenthesizedCondition invariantList? block
	;

invariantList
    : (INVARIANT expr)+
    ;

parenthesizedCondition
	: LPAREN expr RPAREN
	;

assignStatement
	: reference ASSIGN expr SEMICOLON
	;

varDecl
	: type ID (ASSIGN expr)? SEMICOLON
	;

type
	: 'bool'                 # BoolType
	| 'int'                  # IntType
	| type '[]'              # ArrayType
	| LPAREN type RPAREN     # ParenType
	;

expr
	: MINUS expr                                                  # NegExpr // Level 35
	| expr (TIMES | INTDIV) expr                                  # MulDivExpr // Level 40
	| expr (PLUS | MINUS) expr                                    # AddSubExpr // Level 50
	| expr (LEQ | GEQ | GREATER | LESS) expr                      # CompExpr // Level 70
	| NOT expr                                                    # NotExpr // Level 75
	| expr AND expr                                               # AndExpr // Level 80
	| expr OR expr                                                # OrExpr // Level 85
	| expr EQUAL expr                                             # EqExpr // Level 71
	| LPAREN expr RPAREN                                          # ParenExpr
	| ID LPAREN exprList? RPAREN                                  # FuncCall
	| INT                                                         # IntExpr
    | TRUE                                                        # TrueExpr
    | FALSE                                                       # FalseExpr
	| expr IMPLIES (expr)                                         # F_Implies
	| (FORALL || EXISTS) ID DOUBLECOLON expr                      # F_Quant
	| NEW type '[' expr ']'                                       # NewArray
	| reference                                                   # ReferenceExpr
	| expr'.length'                                               # ArrayLength
	;

reference:
      ID                                             # VarRef
    | ID '[' expr ']'                                # ArrayRef
    ;

exprList
	: expr (ARGSEP expr)*
	;

TRUE       : 'true';
FALSE      : 'false';
IF         : 'if';
ELSE       : 'else';
WHILE      : 'while';
ASSIGN     : '=';
PLUS       : '+';
TIMES      : '*';
EQUAL      : '==';
LEQ        : '<=';
GEQ        : '>=';
SEMICOLON  : ';';
GREATER    : '>';
LESS       : '<';
NOT        : '!';
AND        : '&&';
OR         : '||';
LPAREN     : '(';
RPAREN     : ')';
LBRACE     : '{';
RBRACE     : '}';
REQUIRES   : 'requires';
ENSURES    : 'ensures';
INVARIANT  : 'invariant';
FORALL     : 'forall';
EXISTS     : 'exists';
IMPLIES    : '==>';
DOUBLECOLON: '::';
MINUS      : '-';
INTDIV     : '/';
RETURNS    : 'returns';
ARGSEP     : ',';
NEW        : 'new';

ID
	: LETTER (LETTER | [0-9])*
	;

fragment LETTER
	: [a-zA-Z]
	;

INT
	: [0-9]+
	;

WS
	: [ \t\r\n]+ -> skip
	;

SL_COMMENT
	: '//' .*? '\n' -> skip
	;

