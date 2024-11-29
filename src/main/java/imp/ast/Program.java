
package imp.ast;

import imp.ast.method.MethodDeclaration;

import java.util.List;

public record Program(List<MethodDeclaration> methods) {

    public Program {
        if (methods == null) {
            throw new IllegalArgumentException("Methods cannot be null");
        }

        if (methods.isEmpty()) {
            throw new IllegalArgumentException("Methods cannot be empty");
        }
    }

    @Override
    public String toString() {
        return String.join("\n\n", methods.stream().map(MethodDeclaration::toString).toList());
    }
}
