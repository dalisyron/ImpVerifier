package imp.ast.expression;

import imp.ast.Expression;

public record AddExpr(Expression left, Expression right) implements Expression {

    @Override
    public String toString() {
        String leftStr = left.toString();
        String rightStr = right.toString();
        return leftStr + " + " + rightStr;
    }
}
