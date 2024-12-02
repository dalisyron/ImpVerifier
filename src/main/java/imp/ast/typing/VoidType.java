package imp.ast.typing;

public final class VoidType extends Type {
    private static final VoidType instance = new VoidType();

    private VoidType() {
    }

    public static VoidType getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "unit";
    }
}
