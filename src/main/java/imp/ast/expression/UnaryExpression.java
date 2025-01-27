package imp.ast.expression;

import imp.ast.ASTVisitor;

public abstract class UnaryExpression extends Expression {

    private final Expression expression;
    abstract public String operatorSymbol();

    public UnaryExpression(Expression expression) {
        this.expression = expression;
    }

    public Expression expression() {
        return expression;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof UnaryExpression that) {
            if (that.operatorSymbol().equals(operatorSymbol())) {
                return this.expression.equals(that.expression);
            } else {
                return false;
            }
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 31 * expression.hashCode();
    }


    @Override
    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }
}
