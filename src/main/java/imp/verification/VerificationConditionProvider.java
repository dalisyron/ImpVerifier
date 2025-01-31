package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.ast.statement.IfStatement;
import imp.ast.statement.Statement;

public interface VerificationConditionProvider<T extends Statement> {

    BoolExpr awp(Context ctx, T ast, BoolExpr Q);
    BoolExpr avc(Context ctx, T ast, BoolExpr Q);
}
