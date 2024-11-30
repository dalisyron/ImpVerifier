package imp.ast.statement;

import imp.ast.expression.Expression;
import imp.ast.type.Type;
import imp.ast.variable.Identifier;

import java.util.Optional;

public record VariableDeclaration(Type declaredType, Identifier variableName, Optional<Expression> initializer) implements Statement {

    public VariableDeclaration(Type declaredType, Identifier variableName) {
        this(declaredType, variableName, Optional.empty());
    }

    public VariableDeclaration(Type declaredType, Identifier variableName, Expression initializer) {
        this(declaredType, variableName, Optional.of(initializer));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(declaredType).append(" ").append(variableName);
        initializer.ifPresent(expr -> sb.append(" = ").append(expr));
        sb.append(";");
        return sb.toString();
    }
}
