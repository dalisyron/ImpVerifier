package imp.ast.expression.constant.bool;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3ExprInterpreter;

public final class FalseExpression extends Expression implements Z3ExprInterpreter {

    private static final FalseExpression INSTANCE = new FalseExpression();

    private FalseExpression() {}

    public static FalseExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "false";
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        return ctx.mkFalse();
    }
}
