package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.expression.Expression;
import imp.ast.expression.ReferenceExpression;
import imp.ast.statement.AssignStatement;
import imp.interpreter.Z3ImpInterpreter;

public class Assignment implements VerificationConditionProvider<AssignStatement> {

    public BoolExpr awp(Context ctx, AssignStatement assignment, BoolExpr Q) {
        ReferenceExpression lhs = assignment.lhs();
        Expression rhs = assignment.expression();

        Expr rhsExpr = Z3ImpInterpreter.convertExpression(ctx, rhs);
        Expr lhsExpr = Z3ImpInterpreter.convertExpression(ctx, lhs);
        Expr substituted = Q.substitute(lhsExpr, rhsExpr);

        return (BoolExpr) substituted;
    }

    public BoolExpr avc(Context ctx, AssignStatement assignment, BoolExpr Q) {
        return ctx.mkTrue();
    }
}