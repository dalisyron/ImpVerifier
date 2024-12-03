package imp.ast.expression.binary.bool.logic;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public final class OrExpression extends BinaryOpExpression implements Z3BoolExprInterpreter {

    public OrExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "||";
    }

    @Override
    public BoolExpr interpret(Context ctx) {
        return ctx.mkOr((BoolExpr) left().interpret(ctx), (BoolExpr) right().interpret(ctx));
    }
}
