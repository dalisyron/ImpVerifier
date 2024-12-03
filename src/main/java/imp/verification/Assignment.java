package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.expression.Expression;
import imp.ast.expression.ReferenceExpression;
import imp.ast.statement.AssignStatement;

public class Assignment implements VerificationConditionProvider<AssignStatement> {

    private static Assignment instance;

    private Assignment() {
    }

    @Override
    public BoolExpr awp(Context ctx, AssignStatement assignment, BoolExpr Q) {
        ReferenceExpression lhs = assignment.lhs();
        Expression rhs = assignment.expression();

        Expr rhsExpr = rhs.interpret(ctx);
        Expr lhsExpr = lhs.interpret(ctx);
        Expr substituted = Q.substitute(lhsExpr, rhsExpr);

        return (BoolExpr) substituted;
    }

    @Override
    public BoolExpr avc(Context ctx, AssignStatement assignment, BoolExpr Q) {
        return ctx.mkTrue();
    }

    public static Assignment getInstance() {
        if (instance == null) {
            instance = new Assignment();
        }
        return instance;
    }
}