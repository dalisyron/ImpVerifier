package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.expression.type.Type;
import imp.ast.variable.Identifier;

import java.util.List;

public record ReturnValue(Type type, Identifier name) implements ASTNode {

    public ReturnValue(Type type, String name) {
        this(type, new Identifier(name));
    }

    @Override
    public String toString() {
        return type.toString() + " " + name.toString();
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(type, name);
    }
}