package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.ast.variable.Identifier;

import java.util.List;

// Define the ExistsExpr subclass for "exists" quantifier
public final class ExistsExpression extends QuantifiedExpression {

    public ExistsExpression(Identifier variable, Expression expression) {
        super("exists", variable, expression);
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