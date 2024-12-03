package imp.ast.expression;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTVisitor;
import imp.ast.typing.data.DataType;

public final class VarRefExpression extends ReferenceExpression {
    private final Identifier variableName;
    private DataType type;

    public VarRefExpression(Identifier variableName) {
        this.variableName = variableName;
    }

    public Identifier variableName() {
        return variableName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof VarRefExpression expr) {
            return variableName.equals(expr.variableName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variableName.hashCode();
    }

    @Override
    public String toString() {
        return variableName.toString();
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        return ctx.mkConst(variableName.name(), type.interpret(ctx));
    }

    public DataType getType() {
        return type;
    }

    public void setType(DataType type) {
        this.type = type;
    }
}
