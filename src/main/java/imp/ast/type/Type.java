
package imp.ast.type;

public sealed interface Type permits ArrayType, BoolType, IntType {

    @Override
    public String toString();
}