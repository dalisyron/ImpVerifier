package imp.ast.conditional;

import imp.ast.Conditional;

public record AndCond(Conditional left, Conditional right) implements Conditional {

    @Override
    public String toString() {
        return left.toString() + " && " + right.toString();
    }
}
