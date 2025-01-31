package imp.ast.typing;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.expression.TypeVisitor;

public abstract class Type extends ASTNode {
    public abstract void accept(TypeVisitor v);

    @Override
    public void accept(ASTVisitor v) {
        accept((TypeVisitor) v);
    }
}
