package imp.verification;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

import imp.ast.statement.VariableDeclaration;
import imp.interpreter.Z3ImpInterpreter;

public class Declaration implements VerificationConditionProvider<VariableDeclaration> {

    private static Declaration instance;

    private Declaration() {
    }

    @Override
    public BoolExpr awp(Context ctx, VariableDeclaration declaration, BoolExpr Q) {
        if (declaration.initializer().isEmpty()) {
            return (BoolExpr) Q;
        } else {
            Expr rhs = Z3ImpInterpreter.convertExpression(ctx, declaration.initializer().get());
            Expr varname = ctx.mkIntConst(declaration.variableName().toString());
            return (BoolExpr) Q.substitute(varname, rhs);
        }
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