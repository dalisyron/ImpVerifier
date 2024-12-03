package imp.interpreter.sort;

import com.microsoft.z3.Context;
import com.microsoft.z3.Sort;
import imp.interpreter.Z3ASTInterpreter;

public interface Z3SortInterpreter extends Z3ASTInterpreter {
    Sort interpret(Context ctx);
}
