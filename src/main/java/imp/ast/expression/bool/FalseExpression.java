package imp.ast.expression.bool;

import imp.ast.ASTNode;
import imp.ast.expression.Expression;

import java.util.List;

public final class FalseExpression extends Expression implements BoolExpectedType {

    private static final FalseExpression INSTANCE = new FalseExpression();

    private FalseExpression() {}

    public static FalseExpression getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "false";
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }
}
