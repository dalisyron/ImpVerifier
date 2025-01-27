package imp.ast.typing.data.array;

import imp.ast.typing.data.DataType;

public sealed abstract class ArrayType extends DataType permits BoolArray, IntArray {
    DataType elementType;

    public ArrayType(DataType elementType) {
        this.elementType = elementType;
    }

    public DataType getElementType() {
        return elementType;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ArrayType other)) {
            return false;
        }
        return elementType.equals(other.elementType);
    }

    @Override
    public int hashCode() {
        return elementType.hashCode();
    }

}
