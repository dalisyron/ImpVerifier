package imp.ast.variable;

import imp.ast.ASTNode;

import java.util.Objects;

public final class Identifier implements ASTNode {

    private final String name;

    public Identifier(String name) {
        Objects.requireNonNull(name);

        if (name.isBlank()) {
            throw new IllegalArgumentException("Identifier name cannot be blank");
        }

        if (!Character.isLetter(name.charAt(0))) {
            throw new IllegalArgumentException("Identifier name must start with a letter");
        }

        for (int i = 1; i < name.length(); i++) {
            if (!Character.isLetterOrDigit(name.charAt(i))) {
                throw new IllegalArgumentException("Identifier name must contain only letters and digits");
            }
        }

        this.name = name;
    }

    public String name() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }
}
