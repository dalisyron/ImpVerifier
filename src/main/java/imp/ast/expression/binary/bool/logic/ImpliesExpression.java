package imp.ast.expression.binary.bool.logic;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public final class ImpliesExpression extends BinaryOpExpression implements Z3BoolExprInterpreter {

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

    @Override
    public BoolExpr interpret(Context ctx) {
        return ctx.mkImplies((BoolExpr) left().interpret(ctx), (BoolExpr) right().interpret(ctx));
    }
}
