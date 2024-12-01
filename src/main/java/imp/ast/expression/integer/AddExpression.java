package imp.ast.expression.integer;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public class AddExpression extends Expression implements IntExpectedType {
    private final Expression left;
    private final Expression right;

    public AddExpression(Expression left, Expression right) {
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
        if (obj instanceof AddExpression addExpr) {
            return left.equals(addExpr.left) && right.equals(addExpr.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " + " + right.toString() + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}