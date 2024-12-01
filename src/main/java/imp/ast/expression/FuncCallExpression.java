package imp.ast.expression;

import imp.ast.ASTNode;
import imp.ast.typing.FunctionType;
import imp.ast.typing.Type;
import imp.ast.typing.TypingContext;
import imp.ast.variable.Identifier;

import java.util.List;

public final class FuncCallExpression extends Expression {

    private final Identifier functionName;
    private final List<Expression> arguments;

    public FuncCallExpression(Identifier functionName, List<Expression> arguments) {
        this.functionName = functionName;
        this.arguments = arguments;
    }

    public Identifier functionName() {
        return functionName;
    }

    public List<Expression> arguments() {
        return arguments;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FuncCallExpression that)) return false;

        if (!functionName.equals(that.functionName)) return false;
        return arguments.equals(that.arguments);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(functionName).append("(");
        for (int i = 0; i < arguments.size(); i++) {
            sb.append(arguments.get(i).toString());
            if (i < arguments.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append(")");
        return sb.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(functionName);
    }

    @Override
    public Type expectedType(TypingContext context) {
        if (context.contains(functionName)) {
            Type type = context.get(functionName);
            if (type instanceof FunctionType functionType) {
                return functionType.getReturnType();
            } else {
                throw new RuntimeException("Function " + functionName + " is not a function");
            }
        } else {
            throw new RuntimeException("Function " + functionName + " not found in context");
        }
    }
}
