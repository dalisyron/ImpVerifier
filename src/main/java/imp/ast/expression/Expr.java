package imp.ast.expression;

import imp.ast.type.Type;

public sealed abstract class Expr permits ArrayLengthExpr, BinaryExpr, FalseExpr, FuncCallExpr, IntExpr, NegExpr, NewArrayExpr, NotExpr, ParenExpr, QuantifiedExpr, ReferenceExpr, TrueExpr {

    private Type type;

    void setType(Type type) {
        this.type = type;
    }

    public Type type() {
        return type;
    }
}