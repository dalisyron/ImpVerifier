package imp.ast;

import imp.ast.method.MethodDeclaration;

import java.util.List;
import java.util.Objects;

public record Program(List<MethodDeclaration> methods) implements ASTNode {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Program program = (Program) o;

        return Objects.equals(methods, program.methods);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
