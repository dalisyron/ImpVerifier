package imp.ast.expression.binary.bool.compare;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public class LessThanExpression extends BinaryOpExpression implements Z3BoolExprInterpreter {

    public LessThanExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "<";
    }

    @Override
    public BoolExpr interpret(Context ctx) {
        return ctx.mkLt((IntExpr) left().interpret(ctx), (IntExpr) right().interpret(ctx));
    }
}
