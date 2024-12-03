package imp.ast.expression.unary.bool;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.expression.UnaryExpression;

public final class NotExpression extends UnaryExpression {

    public NotExpression(Expression expression) {
        super(expression);
    }

    @Override
    public String operatorSymbol() {
        return "!";
    }

    @Override
    public BoolExpr interpret(Context ctx) {
        return ctx.mkNot((BoolExpr) expression().interpret(ctx));
    }
}
