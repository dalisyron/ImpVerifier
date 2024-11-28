package imp.ast.expression;

public abstract sealed class ReferenceExpr extends Expr permits VarRefExpr, ArrayRefExpr {
}
