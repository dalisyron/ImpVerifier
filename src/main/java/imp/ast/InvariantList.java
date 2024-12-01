package imp.ast;

import imp.ast.ASTNode;
import imp.ast.Invariant;

import java.util.List;

public record InvariantList(List<Invariant> invariants) implements ASTNode {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Invariant invariant : invariants) {
            sb.append(invariant).append("\n");
        }
        return sb.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.copyOf(invariants);
    }
}
