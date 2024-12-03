package imp.interpreter.expr;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.IntExpr;

public interface Z3IntExprInterpreter extends Z3ExprInterpreter {
    @Override
    IntExpr interpret(Context ctx);
}
