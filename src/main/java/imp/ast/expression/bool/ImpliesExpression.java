package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class ImpliesExpression extends Expression implements BoolExpectedType {
    private final Expression left;
    private final Expression right;

    public ImpliesExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression left() {
        return left;
    }

    public Expression right() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ImpliesExpression expr) {
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

        if (right instanceof ImpliesExpression) {
            sb.append(" ==> (");
            sb.append(right);
            sb.append(")");
        } else {
            sb.append(" ==> ");
            sb.append(right.toString());
        }

        return sb.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}
