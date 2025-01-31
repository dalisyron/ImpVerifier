package imp.ast.expression.binary.bool.compare;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;

public final class EqExpression extends BinaryOpExpression {

    public EqExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "==";
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}