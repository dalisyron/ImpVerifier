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
    public boolean equals(Object obj) {
        if (obj instanceof VarRefExpr expr) {
            return variableName.equals(expr.variableName);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return variableName.hashCode();
    }

    @Override
    public String toString() {
        return variableName.toString();
    }
}
