package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public class GreaterThanExpression extends Expression implements BoolExpectedType {
    private final Expression left;
    private final Expression right;

    public GreaterThanExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GreaterThanExpression greaterThanExpr) {
            return left.equals(greaterThanExpr.left) && right.equals(greaterThanExpr.right);
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
        return left + " > " + right;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}