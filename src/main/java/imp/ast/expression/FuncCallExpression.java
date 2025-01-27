package imp.ast.expression;

import com.microsoft.z3.*;
import imp.ast.ASTVisitor;
import imp.ast.typing.FunctionType;
import imp.ast.typing.Type;

import java.util.List;

public final class FuncCallExpression extends Expression {

    private final Identifier identifier;
    private final List<Expression> arguments;

    public FuncCallExpression(Identifier identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
    }

    public Identifier functionName() {
        return identifier;
    }

    public List<Expression> arguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncCallExpression that)) return false;

        if (!identifier.equals(that.identifier)) return false;
        return arguments.equals(that.arguments);
    }


    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public Expr interpret(Context ctx) {
        AST variable = identifier.interpret(ctx);
        if (!(variable instanceof FuncDecl)) {
            throw new IllegalStateException("Identifier does not have a function type");
        }
        return ctx.mkApp((FuncDecl) variable, arguments.stream().map(e -> e.interpret(ctx)).toArray(Expr[]::new));
    }
}
