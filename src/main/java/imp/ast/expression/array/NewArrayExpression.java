package imp.ast.expression.array;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.typing.Type;

public final class NewArrayExpression extends Expression {

    private final Type elementType;
    private final Expression sizeExpression;

    public NewArrayExpression(Type elementType, Expression sizeExpression) {
        this.elementType = elementType;
        this.sizeExpression = sizeExpression;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NewArrayExpression expr) {
            return elementType.equals(expr.elementType) && sizeExpression.equals(expr.sizeExpression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return elementType.hashCode() ^ sizeExpression.hashCode();
    }

    public Type elementType() {
        return elementType;
    }

    public Expression sizeExpr() {
        return sizeExpression;
    }

    @Override
    public String toString() {
        return "new " + elementType + "[" + sizeExpression + "]";
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        throw new UnsupportedOperationException("Not Supported");
    }
}
