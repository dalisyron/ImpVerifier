package imp.ast.method;

import imp.ast.type.Type;
import imp.ast.variable.Identifier;

public record ReturnValue(Type type, Identifier name) {

    public ReturnValue(Type type, String name) {
        this(type, new Identifier(name));
    }

    @Override
    public String toString() {
        return type.toString() + " " + name.toString();
    }
}