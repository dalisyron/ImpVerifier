package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.typing.Type;
import imp.ast.variable.Identifier;

import java.util.Objects;

public final class ReturnValue implements ASTNode {

    private final Type type;
    private final Identifier name;

    public ReturnValue(Type type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public ReturnValue(Type type, String name) {
        this(type, new Identifier(name));
    }

    public Type type() {
        return type;
    }

    public Identifier name() {
        return name;
    }

    @Override
    public String toString() {
        return type.toString() + " " + name.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReturnValue that = (ReturnValue) o;

        if (!Objects.equals(type, that.type)) return false;
        return Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, name);
    }
}
