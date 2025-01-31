package imp.ast.expression;

import imp.ast.ASTVisitor;
import imp.ast.statement.Statement;
import imp.ast.typing.Type;

public abstract class Expression extends Statement {
    Type type;

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }

    @Override
    public final void accept(ASTVisitor visitor) {
        accept((ExpressionVisitor) visitor);
    }

    public abstract void accept(ExpressionVisitor visitor);
}