package imp.interpreter.expr;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;

public interface Z3ExprInterpreter {
    Expr interpret(Context ctx);
}