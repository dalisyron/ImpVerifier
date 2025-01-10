package imp.ast.statement;

import imp.ast.ASTVisitor;
import imp.ast.expression.Expression;
import imp.ast.typing.Type;
import imp.ast.expression.Identifier;

import java.util.Objects;
import java.util.Optional;

public final class VariableDeclaration implements Statement {

    private final Type declaredType;
    private final Identifier variableName;
    private final Expression initializer;

    public VariableDeclaration(Type declaredType, Identifier variableName, Expression initializer) {
        this.declaredType = declaredType;
        this.variableName = variableName;
        this.initializer = initializer;
    }

    public VariableDeclaration(Type declaredType, Identifier variableName) {
        this(declaredType, variableName, null);
    }

    public Type declaredType() {
        return declaredType;
    }

    public Identifier variableName() {
        return variableName;
    }

    public Optional<Expression> initializer() {
        return Optional.ofNullable(initializer);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(declaredType).append(" ").append(variableName);
        initializer().ifPresent(expr -> sb.append(" = ").append(expr));
        sb.append(";");
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableDeclaration that = (VariableDeclaration) o;

        if (!Objects.equals(declaredType, that.declaredType)) return false;
        if (!Objects.equals(variableName, that.variableName)) return false;
        return Objects.equals(initializer, that.initializer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(declaredType, variableName, initializer);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
