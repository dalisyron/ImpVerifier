package imp.ast.expression.array;

import imp.ast.expression.Expr;
import imp.ast.type.Type;

public final class NewArrayExpr extends Expr {

    private final Type elementType;
    private final Expr sizeExpr;

    public NewArrayExpr(Type elementType, Expr sizeExpr) {
        this.elementType = elementType;
        this.sizeExpr = sizeExpr;
    }

    public Type elementType() {
        return elementType;
    }

    public Expr sizeExpr() {
        return sizeExpr;
    }

    @Override
    public String toString() {
        return "new " + elementType + "[" + sizeExpr + "]";
    }
}
