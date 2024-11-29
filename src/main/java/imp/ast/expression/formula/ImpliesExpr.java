package imp.ast.expression.formula;

import imp.ast.expression.Expr;

public final class ImpliesExpr extends Expr {
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
    public boolean equals(Object obj) {
        if (obj instanceof ImpliesExpr expr) {
            return left.equals(expr.left) && right.equals(expr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
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
