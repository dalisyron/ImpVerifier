package imp.ast.conditional;

import imp.ast.Conditional;

public class TrueCond implements Conditional {
    // Singleton instance
    public static final TrueCond INSTANCE = new TrueCond();

    private TrueCond() {
    }

    @Override
    public String toString() {
        return "true";
    }
}
