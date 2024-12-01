package imp.ast.typing;

public class IntArray extends ArrayType {
    private static final IntArray instance = new IntArray();

    private IntArray() {
    }

    public static IntArray getInstance() {
        return instance;
    }

    @Override
    public String toString() {
        return "int[]";
    }
}
