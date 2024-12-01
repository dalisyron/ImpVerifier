package imp.ast.typing;

public class IntType extends Type {
    private static final IntType instance = new IntType();

    private IntType() {
    }

    public static IntType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "int";
    }
}
