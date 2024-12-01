package imp.ast.expression.bool;

import imp.ast.expression.Expression;
import imp.ast.variable.Identifier;

// Define the ForallExpr subclass for "forall" quantifier
public final class ForallExpression extends QuantifiedExpression {

    public ForallExpression(Identifier variable, Expression expression) {
        super("forall", variable, expression);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
