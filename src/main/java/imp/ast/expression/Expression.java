package imp.ast.expression;

import imp.ast.ASTNode;
import imp.ast.typing.ExpectedType;
import imp.ast.typing.Type;

public abstract class Expression implements ASTNode, ExpectedType {
    protected boolean hasBeenTypeChecked = false;

    public void resolveType() {
        if (!typeChecks()) {
            throw new TypeCheckingException(this);
        }

        hasBeenTypeChecked = true;
    }

    public Type type() {
        if (!hasBeenTypeChecked) {
            throw new IllegalStateException("Type has not been resolved yet");
        }

        return expectedType(null);
    }

    boolean typeChecks() {
        if (hasBeenTypeChecked) {
            return true;
        }

        boolean typeChecks = true;

        for (ASTNode child : getChildren()) {
            if (child instanceof Expression expr) {
                typeChecks &= expr.typeChecks();
            }
        }

        return typeChecks;
    }
}