package imp.ast;

import imp.ast.method.MethodDeclaration;

import java.util.List;
import java.util.Objects;

public final class Program implements ASTNode {

    private final List<MethodDeclaration> methods;

    public Program(List<MethodDeclaration> methods) {
        if (methods == null) {
            throw new IllegalArgumentException("Methods cannot be null");
        }
        if (methods.isEmpty()) {
            throw new IllegalArgumentException("Methods cannot be empty");
        }
        this.methods = methods;
    }

    public List<MethodDeclaration> methods() {
        return methods;
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
    public int hashCode() {
        return Objects.hash(methods);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
