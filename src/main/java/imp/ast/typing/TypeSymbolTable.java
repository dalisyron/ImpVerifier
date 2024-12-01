package imp.ast.typing;

import imp.ast.variable.Identifier;

import java.util.*;

public class TypeSymbolTable extends AbstractMap<Identifier, Type> {

    private Deque<HashMap<Identifier, Type>> stack = new ArrayDeque<HashMap<Identifier, Type>>();

    public TypeSymbolTable() {
        stack.push(new HashMap<Identifier, Type>());
    }

    // This is not the most efficient implementation, but it's simple.

    public void pushState() {
        HashMap<Identifier, Type> newTable = new HashMap<Identifier, Type>(stack.peek().size());
        newTable.putAll(stack.peek());
        stack.push(newTable);
    }

    public void popState() {
        if (stack.size() == 1) {
            throw new IllegalStateException("TypeSymTab: cannot pop last symbol table");
        }
        stack.pop();
    }

    @Override
    public Set<Map.Entry<Identifier, Type>> entrySet() {
        return stack.peek().entrySet();
    }

    @Override
    public Type put(Identifier key, Type value) {
        return stack.peek().put(key, value);
    }
}
