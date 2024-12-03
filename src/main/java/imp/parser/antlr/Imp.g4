grammar Imp;

parse
	: (methodDeclaration)+ EOF
	;

methodDeclaration
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
	: REQUIRES expression
	;

ensuresClause
	: ENSURES expression
	;

statement
	: assignStatement                                            # AssignStmt
	| ifStatement                                                # IfStmt
	| whileStatement                                             # WhileStmt
	| block                                                      # BlockStmt
	| varDecl                                                    # VarDeclStmt
	| expression SEMICOLON                                             # ExprStmt
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
    : (INVARIANT expression)+
    ;

parenthesizedCondition
	: LPAREN expression RPAREN
	;

assignStatement
	: reference ASSIGN expression SEMICOLON
	;

varDecl
	: type ID (ASSIGN expression)? SEMICOLON
	;

type
	: 'bool'                 # BoolType
	| 'int'                  # IntType
	| 'int[]'                # ArrayInt
	| 'bool[]'               # ArrayBool
	| LPAREN type RPAREN     # ParenType
	;

expression
	:
	expression'.length'                                               # ArrayLength
	| MINUS expression                                                  # NegExpr // Level 35
	| expression (TIMES | INTDIV | INTMOD) expression                                  # MulDivModExpr // Level 40
	| expression (PLUS | MINUS) expression                                    # AddSubExpr // Level 50
	| expression (LEQ | GEQ | GREATER | LESS) expression                      # CompExpr // Level 70
	| NOT expression                                                    # NotExpr // Level 75
	| expression EQUAL expression                                             # EqExpr // Level 71
	| expression AND expression                                               # AndExpr // Level 80
	| expression OR expression                                                # OrExpr // Level 85
	| LPAREN expression RPAREN                                          # ParenExpr
	| ID LPAREN exprList? RPAREN                                  # FuncCall
	| INT                                                         # IntExpr
    | TRUE                                                        # TrueExpr
    | FALSE                                                       # FalseExpr
	| expression IMPLIES (expression)                                         # F_Implies
	| (FORALL | EXISTS) LPAREN formalParameter RPAREN DOUBLECOLON expression                # QuantifiedExpr
	| NEW type '[' expression ']'                                       # NewArray
	| reference                                                   # ReferenceExpr
	;

reference:
      ID                                             # VarRef
    | ID '[' expression ']'                                # ArrayRef
    ;

exprList
	: expression (ARGSEP expression)*
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
COLON      : ':';
INTMOD     : '%';

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

