package imp.ast.expression;

import com.microsoft.z3.*;
import imp.ast.ASTVisitor;
import imp.ast.typing.FunctionType;

import java.util.List;

public final class FuncCallExpression extends Expression {

    private final Identifier identifier;
    private final List<Expression> arguments;
    private FunctionType functionType;

    public FuncCallExpression(Identifier identifier, List<Expression> arguments) {
        this.identifier = identifier;
        this.arguments = arguments;
    }

    public Identifier identifier() {
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
    public void accept(ExpressionVisitor visitor) {
        visitor.visit(this);
    }

    public FunctionType getFunctionType() {
        return functionType;
    }

    public void setFunctionType(FunctionType functionType) {
        this.functionType = functionType;
    }
}
