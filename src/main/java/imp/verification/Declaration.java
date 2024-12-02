package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import imp.ast.statement.VariableDeclaration;

public class Declaration implements VerificationConditionProvider<VariableDeclaration> {

    private static Declaration instance;

    private Declaration() {
    }

    @Override
    public BoolExpr awp(Context ctx, VariableDeclaration declaration, BoolExpr Q) {
        // Declaration does not change the conditionals
        return (BoolExpr) Q;
    }

    @Override
    public BoolExpr avc(Context ctx, VariableDeclaration declaration, BoolExpr Q) {
        // no additional avc for declaration
        return ctx.mkTrue();
    }

    public static Declaration getInstance() {
        if (instance == null) {
            instance = new Declaration();
        }
        return instance;
    }
}