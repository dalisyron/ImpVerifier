package imp.ast.expression.bool;

import com.microsoft.z3.*;
import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.typing.data.DataType;
import imp.ast.expression.Identifier;
import imp.interpreter.expr.Z3ExprInterpreter;

// Define the ExistsExpr subclass for "exists" quantifier
public final class ExistsExpression extends QuantifiedExpression implements Z3ExprInterpreter {

    public ExistsExpression(Identifier variable, DataType type, Expression expression) {
        super("exists", variable, type, expression);
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        DataType type = getType();
        Sort sort = type.interpret(ctx);

        BoolExpr bodyExpr = (BoolExpr) body.interpret(ctx);

        return ctx.mkExists(new Expr[] { ctx.mkConst(variable.name(), sort) }, bodyExpr, 1, null, null, null, null);
    }
}