package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class TrueExpression extends Expression implements BoolExpectedType {

    private static final TrueExpression INSTANCE = new TrueExpression();

    private TrueExpression() {}

    // Public method to access the single instance
    public static TrueExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "true";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }

}
