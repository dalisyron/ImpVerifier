
package imp.ast.expression.type;

import imp.ast.ASTNode;

public sealed interface TypeExpression extends ASTNode permits ArrayTypeExpression, BoolTypeExpression, IntTypeExpression {

    @Override
    public String toString();
}