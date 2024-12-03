package imp.interpreter;

import com.microsoft.z3.AST;
import com.microsoft.z3.Context;

public interface Z3ASTInterpreter {
    AST interpret(Context ctx);
}
