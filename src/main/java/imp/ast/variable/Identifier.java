
package imp.ast.variable;

import imp.ast.ASTNode;

import java.util.List;
import java.util.Objects;

public record Identifier(String name) implements ASTNode {

    public Identifier {
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
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of();
    }
}