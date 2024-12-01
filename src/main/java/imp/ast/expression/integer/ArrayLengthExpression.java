package imp.ast.expression.integer;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class ArrayLengthExpression extends Expression implements IntExpectedType {

    private final Expression arrayExpression;

    public ArrayLengthExpression(Expression arrayExpression) {
        this.arrayExpression = arrayExpression;
    }

    public Expression arrayExpr() {
        return arrayExpression;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ArrayLengthExpression arrayLengthExpr) {
            return arrayExpression.equals(arrayLengthExpr.arrayExpression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * arrayExpression.hashCode();
    }

    @Override
    public String toString() {
        return arrayExpression.toString() + ".length";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(arrayExpression);
    }
}
