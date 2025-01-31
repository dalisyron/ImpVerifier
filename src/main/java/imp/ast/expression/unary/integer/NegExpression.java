package imp.ast.expression.unary.integer;

import com.microsoft.z3.ArithExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;
import imp.ast.expression.UnaryExpression;

public final class NegExpression extends UnaryExpression {

    public NegExpression(Expression expression) {
        super(expression);
    }

    @Override
    public String operatorSymbol() {
        return "-";
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}
