package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.statement.IfStatement;
import imp.ast.statement.Statement;

public class AVC {
    private static AVC instance;

    private AVC() {
    }

    public BoolExpr avc(Context ctx, Statement statement, BoolExpr Q) {
        if (statement.getClass() == IfStatement.class) {
            return IfElse.getInstance().avc(ctx, (IfStatement)statement, Q);
        } else {
            throw new RuntimeException("Non-implemented AWP");
        }
    }

    public static AVC getInstance() {
        if (instance == null) {
            instance = new AVC();
        }
        return instance;
    }
}
