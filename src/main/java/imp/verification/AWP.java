package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.statement.AssignStatement;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.IfStatement;
import imp.ast.statement.Statement;
import imp.ast.statement.WhileStatement;

public class AWP {

    private static AWP instance;

    private AWP() {
    }

    public BoolExpr awp(Context ctx, Statement statement, BoolExpr Q) {
        if (statement.getClass() == BlockStatement.class) {
            return Composition.getInstance().awp(ctx, (BlockStatement)statement, Q);
        } else if (statement.getClass() == Assignment.class) {
            return Assignment.getInstance().awp(ctx, (AssignStatement)statement, Q);
        } else if (statement.getClass() == IfStatement.class) {
            return IfElse.getInstance().awp(ctx, (IfStatement)statement, Q);
        } else if(statement.getClass() == WhileStatement.class) {
            return While.getInstance().awp(ctx, (WhileStatement)statement, Q);
        } else {
            throw new RuntimeException("Non-implemented AWP");
        }
    }

    public static AWP getInstance() {
        if (instance == null) {
            instance = new AWP();
        }
        return instance;
    }
}
