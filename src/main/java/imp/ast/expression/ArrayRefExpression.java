package imp.ast.expression;

import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import com.microsoft.z3.Sort;
import imp.ast.ASTVisitor;
import imp.ast.typing.data.value.PrimitiveType;

public final class ArrayRefExpression extends ReferenceExpression {
    private final Identifier arrayName;
    private final Expression indexExpression;
    private PrimitiveType elementType;

    public ArrayRefExpression(Identifier arrayName, Expression indexExpression) {
        this.arrayName = arrayName;
        this.indexExpression = indexExpression;
    }

    public Identifier arrayName() {
        return arrayName;
    }

    public Expression indexExpr() {
        return indexExpression;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        ArrayRefExpression other = (ArrayRefExpression) obj;
        return arrayName.equals(other.arrayName) && indexExpression.equals(other.indexExpression);
    }


    public PrimitiveType getElementType() {
        return elementType;
    }

    public void setElementType(PrimitiveType elementType) {
        this.elementType = elementType;
    }

    @Override
    public void accept(ExpressionVisitor v) {
        v.visit(this);
    }
}
