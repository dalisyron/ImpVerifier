package imp.ast.statement;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;
import imp.ast.expression.type.TypeExpression;
import imp.ast.variable.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public record VariableDeclaration(TypeExpression declaredType, Identifier variableName, Optional<Expression> initializer) implements Statement {

    public VariableDeclaration(TypeExpression declaredType, Identifier variableName) {
        this(declaredType, variableName, Optional.empty());
    }

    public VariableDeclaration(TypeExpression declaredType, Identifier variableName, Expression initializer) {
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

    @Override
    public List<ASTNode> getChildren() {
        List<ASTNode> children = new ArrayList<>(List.of(declaredType, variableName));
        initializer.ifPresent(children::add);
        return children;
    }
}
