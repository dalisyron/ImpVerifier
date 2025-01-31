package imp.ast;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

public final class InvariantList extends ASTNode {
    @NotNull
    private final List<Invariant> invariants;


    public InvariantList(@NotNull List<Invariant> invariants) {
        Objects.requireNonNull(invariants, "Invariants cannot be null");
        this.invariants = invariants;
    }

    public List<Invariant> invariants() {
        return invariants;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InvariantList that = (InvariantList) o;

        return Objects.equals(invariants, that.invariants);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invariants);
    }


}
