package imp.ast.expression.binary.integer;

import com.microsoft.z3.Context;
import com.microsoft.z3.IntExpr;
import imp.ast.expression.BinaryOpExpression;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3IntExprInterpreter;

public class ModExpression extends BinaryOpExpression implements Z3IntExprInterpreter {

    public ModExpression(Expression left, Expression right) {
        super(left, right);
    }

    @Override
    public String operatorSymbol() {
        return "%";
    }

    @Override
    public IntExpr interpret(Context ctx) {
        return (IntExpr) ctx.mkDiv((IntExpr) left().interpret(ctx), (IntExpr) right().interpret(ctx));
    }
}
