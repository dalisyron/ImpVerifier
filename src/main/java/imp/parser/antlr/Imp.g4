grammar Imp;

// ─────────────────────────────────────────────────────────────────
// Parser Rules (unchanged)
// ─────────────────────────────────────────────────────────────────

parse: (methodDeclaration)+ EOF;

methodDeclaration:
    'method' ID LPAREN formalParameters? RPAREN returnsBlock? conditionBlock block;

formalParameters: formalParameter (',' formalParameter)*;

formalParameter: type ID;

returnsBlock: RETURNS LPAREN formalParameter RPAREN;

conditionBlock: (requiresClause | ensuresClause)*;

requiresClause: REQUIRES expression;

ensuresClause: ENSURES expression;

statement:
    assignStatement            # AssignStmt
    | ifStatement             # IfStmt
    | whileStatement          # WhileStmt
    | block                   # BlockStmt
    | varDecl                 # VarDeclStmt
    | expression SEMICOLON    # ExprStmt
    ;

block: LBRACE statement* RBRACE;

ifStatement: IF parenthesizedCondition block (ELSE block)?;

whileStatement:
    WHILE parenthesizedCondition invariantList? block;

invariantList: (INVARIANT expression)+;

parenthesizedCondition: LPAREN expression RPAREN;

assignStatement: reference ASSIGN expression SEMICOLON;

varDecl: type ID (ASSIGN expression)? SEMICOLON;

type:
	BOOL_TYPE					# BoolType
	| INT_TYPE					# IntType
	| INT_ARRAY_TYPE				# IntArrayType
	| BOOL_ARRAY_TYPE			# BoolArrayType
	| LPAREN type RPAREN	# ParenType;

expression:
      reference                                                                 # ReferenceExpr
    | expression '.length'                                                     # ArrayLength
    | ID LPAREN exprList? RPAREN                                               # FuncCall
    | (MINUS | NOT) expression                                                 # UnaryExpr
    | NEW type LBRACK expression RBRACK                                        # NewArray
    | expression (TIMES | INTDIV | INTMOD) expression                          # MulDivModExpr
    | expression (PLUS | MINUS) expression                                     # AddSubExpr
    | expression (LEQ | GEQ | GREATER | LESS) expression                       # CompExpr
    | expression EQUAL expression                                              # EqExpr
    | expression AND expression                                                # AndExpr
    | expression OR expression                                                 # OrExpr
    | LPAREN expression RPAREN                                                 # ParenExpr
    | INT                                                                      # IntExpr
    | TRUE                                                                     # TrueExpr
    | FALSE                                                                    # FalseExpr
    | expression IMPLIES expression                                            # F_Implies
    | (FORALL | EXISTS) LPAREN formalParameter RPAREN DOUBLECOLON expression   # QuantifiedExpr
    ;

reference:
      ID                         # VarRef
    | ID LBRACK expression RBRACK # ArrayRef
    ;

exprList: expression (ARGSEP expression)*;

// ─────────────────────────────────────────────────────────────────
// Lexer Rules (refactored and grouped)
// ─────────────────────────────────────────────────────────────────


// ─────────────────────────────────────────────────────────────────
// 1. Terminals to separate arguments, etc.
// ─────────────────────────────────────────────────────────────────
ARGSEP: ',';  // Used where the grammar has (ARGSEP expression)*


// ─────────────────────────────────────────────────────────────────
// 2. Core keywords (appear as string literals in parser rules)
// ─────────────────────────────────────────────────────────────────
METHOD: 'method';       // The parser still uses 'method' as a literal
REQUIRES: 'requires';
ENSURES: 'ensures';
INVARIANT: 'invariant';
FORALL: 'forall';
EXISTS: 'exists';
NEW: 'new';
RETURNS: 'returns';
IF: 'if';
ELSE: 'else';
WHILE: 'while';
TRUE: 'true';
FALSE: 'false';

// ─────────────────────────────────────────────────────────────────
// 3. Operators and punctuation
// ─────────────────────────────────────────────────────────────────
ASSIGN: '=';
PLUS: '+';
MINUS: '-';
TIMES: '*';
INTDIV: '/';
INTMOD: '%';
EQUAL: '==';
LEQ: '<=';
GEQ: '>=';
GREATER: '>';
LESS: '<';
NOT: '!';
AND: '&&';
OR: '||';
IMPLIES: '==>';
DOUBLECOLON: '::';
COLON: ':';
SEMICOLON: ';';

// ─────────────────────────────────────────────────────────────────
// 4. Brackets
// ─────────────────────────────────────────────────────────────────
LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';


// ─────────────────────────────────────────────────────────────────
// 5. Type names (used as string literals in the `type` rule)
// ─────────────────────────────────────────────────────────────────
BOOL_TYPE: 'bool';
INT_TYPE: 'int';
INT_ARRAY_TYPE: 'int[]';
BOOL_ARRAY_TYPE: 'bool[]';


// ─────────────────────────────────────────────────────────────────
// 6. Identifier and numeric literal
// ─────────────────────────────────────────────────────────────────
ID: LETTER (LETTER | [0-9])*;

fragment LETTER: [a-zA-Z];

INT: [0-9]+;


// ─────────────────────────────────────────────────────────────────
// 7. Whitespace and comments
// ─────────────────────────────────────────────────────────────────
WS: [ \t\r\n]+ -> skip;

SL_COMMENT: '//' .*? '\n' -> skip;
