package imp.ast.expression;

import imp.ast.ASTVisitor;

public abstract class BinaryOpExpression extends Expression {
    private final Expression left;
    private final Expression right;

    public BinaryOpExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    public Expression left() {
        return left;
    }

    public Expression right() {
        return right;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BinaryOpExpression expr)) {
            return false;
        }

        if (expr.operatorSymbol().equals(operatorSymbol())) {
            return left.equals(expr.left) && right.equals(expr.right);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ right.hashCode();
    }

    @Override
    public String toString() {
        return "(" + left.toString() + " " + operatorSymbol() + " " + right.toString() + ")";
    }

    abstract public String operatorSymbol();

    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
