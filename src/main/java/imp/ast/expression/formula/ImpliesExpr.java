package imp.ast.expression.formula;

import imp.ast.expression.Expr;

public class ImpliesExpr extends Expr {
    private final Expr left;
    private final Expr right;

    public ImpliesExpr(Expr left, Expr right) {
        this.left = left;
        this.right = right;
    }

    public Expr left() {
        return left;
    }

    public Expr right() {
        return right;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left.toString());

        if (right instanceof ImpliesExpr) {
            sb.append(" ==> (");
            sb.append(right);
            sb.append(")");
        } else {
            sb.append(" ==> ");
            sb.append(right.toString());
        }

        return sb.toString();
    }
}
