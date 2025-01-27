package imp.ast.expression;

import imp.ast.statement.Statement;
import imp.ast.typing.Type;
import imp.ast.typing.data.DataType;
import imp.interpreter.expr.Z3ExprInterpreter;

public abstract class Expression extends Statement implements Z3ExprInterpreter {
    Type type;

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}