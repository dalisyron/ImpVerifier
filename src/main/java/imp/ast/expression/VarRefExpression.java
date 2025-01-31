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

    public DataType getType() {
        return type;
    }

    @Override
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public void setType(DataType type) {
        this.type = type;
    }
}