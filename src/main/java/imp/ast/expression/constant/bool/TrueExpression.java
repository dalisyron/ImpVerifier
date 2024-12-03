package imp.ast.expression.constant.bool;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3ExprInterpreter;

public final class TrueExpression extends Expression implements Z3ExprInterpreter {

    private static final TrueExpression INSTANCE = new TrueExpression();

    private TrueExpression() {}

    // Public method to access the single instance
    public static TrueExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "true";
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        return ctx.mkTrue();
    }
}
