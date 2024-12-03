package imp.interpreter.expr;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public interface Z3BoolExprInterpreter extends Z3ExprInterpreter {
    @Override
    BoolExpr interpret(Context ctx);
}
