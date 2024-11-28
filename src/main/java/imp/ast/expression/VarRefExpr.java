package imp.ast.expression;

import imp.ast.variable.Identifier;

public record VarRefExpr(Identifier variableName) implements ReferenceExpr {

    @Override
    public String toString() {
        return variableName.toString();
    }
}
