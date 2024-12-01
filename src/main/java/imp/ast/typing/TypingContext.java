package imp.ast.typing;

import imp.ast.variable.Identifier;

import java.util.HashMap;

public class TypingContext {
    private final HashMap<Identifier, Type> typeMap;

    public TypingContext() {
        this.typeMap = new HashMap<>();
    }

    public void add(Identifier id, Type type) {
        typeMap.put(id, type);
    }

    public Type get(Identifier id) {
        return typeMap.get(id);
    }

    public boolean contains(Identifier id) {
        return typeMap.containsKey(id);
    }

    public void remove(Identifier id) {
        typeMap.remove(id);
    }

    public void clear() {
        typeMap.clear();
    }
}
