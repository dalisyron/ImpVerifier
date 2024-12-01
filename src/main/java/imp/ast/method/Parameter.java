package imp.ast.method;

import imp.ast.ASTNode;
import imp.ast.expression.type.Type;
import imp.ast.variable.Identifier;

import java.util.List;

public record Parameter(Type type, Identifier name) implements ASTNode {

    public Parameter(Type type, String name) {
        this(type, new Identifier(name));
    }

    @Override
    public List<ASTNode> getChildren() {
        return List.of(type, name);
    }
}