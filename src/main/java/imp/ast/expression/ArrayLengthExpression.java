package imp.ast.expression;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.IntSort;
import imp.ast.ASTVisitor;

public final class ArrayLengthExpression extends Expression {

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
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
