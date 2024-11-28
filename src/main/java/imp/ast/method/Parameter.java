package imp.ast.method;

import imp.ast.type.Type;
import imp.ast.variable.Identifier;

public record Parameter(Type type, Identifier name) {

    public Parameter(Type type, String name) {
        this(type, new Identifier(name));
    }
}