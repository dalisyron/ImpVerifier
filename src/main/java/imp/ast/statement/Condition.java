package imp.ast.statement;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;
import com.microsoft.z3.Expr;
import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.interpreter.expr.Z3BoolExprInterpreter;

public final class Condition implements Z3BoolExprInterpreter, ASTNode {
    private final Expression expression;

    public Condition(Expression expression) {
        this.expression = expression;
    }

    @Override
    public String toString() {
        return expression.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Condition that) {
            return this.expression.equals(that.expression);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return expression.hashCode();
    }

    @Override
    public BoolExpr interpret(Context ctx) {
        return (BoolExpr) expression.interpret(ctx);
    }

    public void accept(ASTVisitor visitor) {
        visitor.visit(this);
    }

    public ASTNode expression() {
        return expression;
    }
}
