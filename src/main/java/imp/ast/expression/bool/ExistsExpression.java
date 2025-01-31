package imp.ast.expression.bool;

import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;
import imp.ast.typing.data.DataType;
import imp.ast.expression.Identifier;

// Define the ExistsExpr subclass for "exists" quantifier
public final class ExistsExpression extends QuantifiedExpression {

    public ExistsExpression(Identifier variable, DataType type, Expression expression) {
        super("exists", variable, type, expression);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }
}