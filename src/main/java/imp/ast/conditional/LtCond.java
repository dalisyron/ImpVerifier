package imp.ast.conditional;

import imp.ast.Conditional;
import imp.ast.Expression;

public record LtCond(Expression left, Expression right) implements Conditional {

    @Override
    public String toString() {
        return left.toString() + " < " + right.toString();
    }
}
