package imp.ast;

import imp.ast.ASTNode;
import imp.ast.Invariant;

import java.util.List;
import java.util.Objects;

public final class InvariantList implements ASTNode {

    private final List<Invariant> invariants;

    public InvariantList(List<Invariant> invariants) {
        this.invariants = invariants;
    }

    public List<Invariant> invariants() {
        return invariants;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Invariant invariant : invariants) {
            sb.append(invariant).append("\n");
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvariantList that = (InvariantList) o;

        return Objects.equals(invariants, that.invariants);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invariants);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
