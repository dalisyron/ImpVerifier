package imp.ast.expression.binary.bool.logic;

import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;

public final class ImpliesExpression extends BinaryOpExpression {

    public ImpliesExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "==>";
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(left().toString());

        if (right() instanceof ImpliesExpression) {
            sb.append(" ==> (");
            sb.append(right());
            sb.append(")");
        } else {
            sb.append(" ==> ");
            sb.append(right().toString());
        }

        return sb.toString();
    }
}
