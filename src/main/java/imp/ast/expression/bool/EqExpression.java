package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class EqExpression extends Expression implements BoolExpectedType {
    private final Expression left;
    private final Expression right;

    public EqExpression(Expression left, Expression right) {
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
        if (obj instanceof EqExpression expr) {
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
        return left.toString() + " == " + right.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}