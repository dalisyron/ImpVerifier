package imp.ast;

public interface ASTNode {
    void accept(ASTVisitor v);
}