package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public class GreaterThanOrEqualExpression extends Expression implements BoolExpectedType {
    private final Expression left;
    private final Expression right;

    public GreaterThanOrEqualExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanOrEqualExpression greaterThanOrEqualExpr) {
            return left.equals(greaterThanOrEqualExpr.left) && right.equals(greaterThanOrEqualExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    public Expression left() {
        return left;
    }

    public Expression right() {
        return right;
    }

    @Override
    public String toString() {
        return left + " >= " + right;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}
