package imp.ast.expression;

import imp.ast.ASTVisitor;
import imp.ast.variable.Identifier;

public final class ArrayRefExpression extends ReferenceExpression {
    private final Identifier arrayName;
    private final Expression indexExpression;

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

    @Override
    public String toString() {
        return arrayName + "[" + indexExpression + "]";
    }

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
