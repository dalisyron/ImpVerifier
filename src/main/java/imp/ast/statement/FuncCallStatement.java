package imp.ast.statement;

import imp.ast.ASTVisitor;
import imp.ast.expression.FuncCallExpression;

public final class FuncCallStatement extends Statement {

    private final FuncCallExpression funcCallExpression;

    public FuncCallStatement(FuncCallExpression funcCallExpression) {
        this.funcCallExpression = funcCallExpression;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncCallStatement that)) return false;

        return funcCallExpression.equals(that.funcCallExpression);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    public FuncCallExpression funcCallExpression() {
        return funcCallExpression;
    }
}