package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.statement.AssignStatement;
import imp.ast.statement.IfStatement;
import imp.ast.statement.Statement;

public class AWP {

    public static BoolExpr awp(Context ctx, Statement statement, BoolExpr Q) {
        if (statement.getClass() == IfStatement.class) {
            return IfElse.getInstance().awp(ctx, (IfStatement)statement, Q);
        } else {
            throw new RuntimeException("Non-implemented AWP");
        }
    }

}
