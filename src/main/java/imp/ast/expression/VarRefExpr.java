package imp.ast.expression;

import imp.ast.variable.Identifier;

public final class VarRefExpr extends ReferenceExpr {

    private final Identifier variableName;

    public VarRefExpr(Identifier variableName) {
        this.variableName = variableName;
    }

    public Identifier variableName() {
        return variableName;
    }

    @Override
    public String toString() {
        return variableName.toString();
    }
}
