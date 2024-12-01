package imp.ast.expression.integer;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public class DivExpression extends Expression implements IntExpectedType {
    private final Expression left;
    private final Expression right;

    public DivExpression(Expression left, Expression right) {
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
        if (obj instanceof DivExpression that) {
            return this.left.equals(that.left) && this.right.equals(that.right);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * left.hashCode() + right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " / " + right.toString() + ")";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(left, right);
    }
}