package imp.ast;

import imp.print.ImpPrettyPrinter;

public abstract class ASTNode {
    public abstract void accept(ASTVisitor v);

    @Override
    public String toString() {
        return ImpPrettyPrinter.getInstance().prettyPrint(this);
    }
}