package imp.ast.expression;

import imp.ast.type.Type;

public abstract class Expr {

    private Type type;

    void setType(Type type) {
        this.type = type;
    }

    public Type type() {
        return type;
    }
}