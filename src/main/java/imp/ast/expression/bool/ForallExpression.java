package imp.ast.expression.bool;

import com.microsoft.z3.*;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.expression.ExpressionVisitor;
import imp.ast.typing.data.DataType;
import imp.ast.expression.Identifier;

// Define the ForallExpr subclass for "forall" quantifier
public final class ForallExpression extends QuantifiedExpression {

    public ForallExpression(Identifier variable, DataType type, Expression expression) {
        super("forall", variable, type, expression);
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
