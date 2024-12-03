package imp.ast.expression.binary.integer;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.IntExpr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3IntExprInterpreter;

public final class SubExpression extends BinaryOpExpression implements Z3IntExprInterpreter {

    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "-";
    }

    @Override
    public IntExpr interpret(Context ctx) {
        IntExpr leftExpr = (IntExpr) left().interpret(ctx);
        IntExpr rightExpr = (IntExpr) right().interpret(ctx);
        return (IntExpr) ctx.mkSub(leftExpr, rightExpr);
    }
}
