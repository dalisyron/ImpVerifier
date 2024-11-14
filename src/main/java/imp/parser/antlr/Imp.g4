grammar Imp;

parse
    : statement+ EOF
    ;

statement
    : simpleStatement (';' simpleStatement)*                        # SequenceStmt
    ;

simpleStatement
    : VARIABLE ASSIGN expression                                   # AssignStmt
    | IF conditional THEN statement ELSE statement                 # IfStmt
    | WHILE conditional DO block                                   # WhileStmt
    | block                                                        # BlockStmt
    ;

block
    : BEGIN statement END
    ;

conditional
    : TRUE                                                         # TrueCond
    | FALSE                                                        # FalseCond
    | expression EQUAL expression                                  # EqualCond
    | expression LEQ expression                                    # LeqCond
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
WHILE     : 'while';
DO        : 'do';
BEGIN     : 'begin';
END       : 'end';
ASSIGN    : ':=';
PLUS      : '+';
TIMES     : '*';
EQUAL     : '=';
LEQ       : '<=';
SEMICOLON : ';';

VARIABLE
    : [a-zA-Z][a-zA-Z0-9]*;

INTEGER
    : [0-9]+;

WS
    : [ \t\r\n]+ -> skip;
