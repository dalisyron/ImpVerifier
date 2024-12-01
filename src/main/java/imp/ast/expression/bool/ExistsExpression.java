package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.typing.Type;
import imp.ast.variable.Identifier;

import java.util.List;

// Define the ExistsExpr subclass for "exists" quantifier
public final class ExistsExpression extends QuantifiedExpression {

    public ExistsExpression(Identifier variable, Type type, Expression expression) {
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
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}