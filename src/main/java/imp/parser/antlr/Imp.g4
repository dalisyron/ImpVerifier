grammar Imp;

parse
    : statement+ EOF
    ;

statement
    : simpleStatement (';' simpleStatement)*                        # SequenceStmt
    ;

simpleStatement
    : VARIABLE ASSIGN expression                                   # AssignStmt
    | IF conditional THEN statement ELSE statement DONE            # IfStmt
    | WHILE conditional INVARIANT conditional DO block             # WhileStmt
    | block                                                        # BlockStmt
    | PRECOND conditional                                          # Precondition
    | POSTCOND conditional                                         # Postcondition
    ;

block
    : BEGIN statement END
    ;

conditional
    : TRUE                                                         # TrueCond
    | FALSE                                                        # FalseCond
    | expression                                                   # BaseCase
    | expression EQUAL expression                                  # EqualCond
    | expression LEQ expression                                    # LeqCond
    | expression LT expression                                     # LtCond
    | expression GEQ expression                                    # GeqCond
    | expression GT expression                                     # GtCond
    | conditional AND conditional                                  # AndCond
    | conditional OR conditional                                   # OrCond
    | conditional IMPLIES conditional                              # ImpliesCond
    ;

// Expression grammar
expression
    : INTEGER                     # IntegerExpr
    | VARIABLE                    # VariableExpr
    | expression TIMES expression # MulExpr
    | expression PLUS expression  # AddExpr
    | '(' expression ')'          # ParenExpr
    ;

TRUE      : 'true';
FALSE     : 'false';
IF        : 'if';
THEN      : 'then';
ELSE      : 'else';
DONE      : 'done';
WHILE     : 'while';
DO        : 'do';
BEGIN     : 'begin';
END       : 'end';
ASSIGN    : ':=';
PLUS      : '+';
TIMES     : '*';
EQUAL     : '=';
LEQ       : '<=';
LT        : '<';
GEQ       : '>=';
GT        : '>';
AND       : '&&';
OR        : '||';
IMPLIES   : '==>';
PRECOND   : 'preq';
POSTCOND  : 'post';
INVARIANT : 'invariant';
SEMICOLON : ';';

VARIABLE
    : [a-zA-Z][a-zA-Z0-9]*;

INTEGER
    : [0-9]+;

WS
    : [ \t\r\n]+ -> skip;
