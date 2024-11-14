package imp.ast.conditional;

import imp.ast.Conditional;

public class FalseCond implements Conditional {
    public static final FalseCond INSTANCE = new FalseCond();

    private FalseCond() {
    }

    @Override
    public String toString() {
        return "false";
    }
}
