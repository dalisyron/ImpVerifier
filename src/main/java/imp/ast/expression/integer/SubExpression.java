package imp.ast.expression.integer;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class SubExpression extends Expression implements IntExpectedType {
    private final Expression left;
    private final Expression right;

    public SubExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SubExpression expr) {
            return left.equals(expr.left) && right.equals(expr.right);
        }
        return false;
    }

    public Expression left() {
        return left;
    }

    public Expression right() {
        return right;
    }

    @Override
    public String toString() {
        return left.toString() + " - " + right.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}
