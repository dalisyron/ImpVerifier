package imp.ast.typing;

public final class BoolType extends Type {
    private static final BoolType instance = new BoolType();

    private BoolType() {
    }

    public static BoolType getInstance() {
        return instance;
    }
}
