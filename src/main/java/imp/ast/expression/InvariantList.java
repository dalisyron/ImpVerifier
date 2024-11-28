package imp.ast.expression;

import java.util.List;

public record InvariantList(List<Invariant> invariants) {

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Invariant invariant : invariants) {
            sb.append(invariant).append("\n");
        }
        return sb.toString();
    }
}
