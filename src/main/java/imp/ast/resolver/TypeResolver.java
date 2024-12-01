package imp.ast.resolver;

import imp.ast.ASTNode;
import imp.ast.Program;
import imp.ast.expression.Expression;
import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.bool.FalseExpression;
import imp.ast.expression.bool.TrueExpression;
import imp.ast.expression.integer.IntExpression;
import imp.ast.expression.type.BoolTypeExpression;
import imp.ast.expression.type.IntTypeExpression;

public class TypeResolver {
    public static void resolveTypes(Program program) {
        resolveTypesRec(program);
    }

    private static void resolveTypesRec(ASTNode node) {
        if (!(node instanceof Expression)) {
            for (ASTNode child : node.getChildren()) {
                resolveTypesRec(child);
            }
            return;
        }

        resolveTypes((Expression) node);
    }

    private static void resolveTypes(Expression expression) {
        if (expression.type() != null) {
            return;
        }

        if (expression instanceof TrueExpression) {
            expression.setType(BoolTypeExpression.getInstance());
        } else if (expression instanceof FalseExpression) {
            expression.setType(BoolTypeExpression.getInstance());
        } else if (expression instanceof IntExpression) {
            expression.setType(IntTypeExpression.getInstance());
        } else if (expression instanceof NewArrayExpression) {
        }
    }
}