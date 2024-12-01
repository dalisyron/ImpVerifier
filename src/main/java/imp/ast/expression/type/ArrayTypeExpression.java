package imp.ast.expression.type;

import imp.ast.ASTNode;

import java.util.List;

public record ArrayTypeExpression(TypeExpression elementType) implements TypeExpression {

    @Override
    public String toString() {
        return elementType + "[]";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(elementType);
    }
}