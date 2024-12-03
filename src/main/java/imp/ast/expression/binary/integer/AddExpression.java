package imp.ast.expression.binary.integer;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.IntExpr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;
import imp.interpreter.expr.Z3IntExprInterpreter;

public class AddExpression extends BinaryOpExpression implements Z3IntExprInterpreter {

    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "+";
    }

    @Override
    public IntExpr interpret(Context ctx) {
        return (IntExpr) ctx.mkAdd((IntExpr) left().interpret(ctx), (IntExpr) right().interpret(ctx));
    }
}
