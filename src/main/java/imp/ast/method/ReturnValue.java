package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.typing.data.DataType;
import imp.ast.expression.Identifier;

import java.util.Objects;

public final class ReturnValue implements ASTNode {

    private final DataType type;
    private final Identifier name;

    public ReturnValue(DataType type, Identifier name) {
        this.type = type;
        this.name = name;
    }

    public ReturnValue(DataType type, String name) {
        this(type, new Identifier(name));
    }

    public DataType type() {
        return type;
    }

    public Identifier name() { return name;
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

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }
}
