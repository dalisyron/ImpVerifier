package imp.ast;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public record Invariant(Expression expression) implements ASTNode {

    @Override
    public String toString() {
        return "invariant " + expression;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(expression);
    }
}
