package imp.ast.typing;

public class BoolArray extends ArrayType {
    private static final BoolArray instance = new BoolArray();

    private BoolArray() {
    }

    public static BoolArray getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "bool[]";
    }
}
