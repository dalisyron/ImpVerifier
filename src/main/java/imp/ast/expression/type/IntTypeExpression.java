package imp.ast.expression.type;

import imp.ast.ASTNode;

import java.util.List;

public final class IntTypeExpression implements TypeExpression {
    private static final IntTypeExpression instance = new IntTypeExpression();

    private IntTypeExpression() {
    }

    @Override
    public String toString() {
        return "int";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntTypeExpression;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }

    public static IntTypeExpression getInstance() {
        return instance;
    }
}
