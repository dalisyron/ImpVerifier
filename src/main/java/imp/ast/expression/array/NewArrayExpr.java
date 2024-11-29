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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof NewArrayExpr expr) {
            return elementType.equals(expr.elementType) && sizeExpr.equals(expr.sizeExpr);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return elementType.hashCode() ^ sizeExpr.hashCode();
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
