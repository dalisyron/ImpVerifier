package imp.ast.expression.bool;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.typing.Type;
import imp.ast.variable.Identifier;

// Define the ForallExpr subclass for "forall" quantifier
public final class ForallExpression extends QuantifiedExpression {

    public ForallExpression(Identifier variable, Type type, Expression expression) {
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
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
