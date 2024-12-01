package imp.ast.expression.type;

import imp.ast.ASTNode;

import java.util.List;

public final class BoolTypeExpression implements TypeExpression {
    private static final BoolTypeExpression instance = new BoolTypeExpression();

    private BoolTypeExpression() {
    }

    @Override
    public String toString() {
        return "bool";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof BoolTypeExpression;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    public static BoolTypeExpression getInstance() {
        return instance;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }
}
