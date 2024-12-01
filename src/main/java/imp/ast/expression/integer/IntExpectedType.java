package imp.ast.expression.integer;

import imp.ast.typing.ExpectedType;
import imp.ast.typing.IntType;
import imp.ast.typing.Type;
import imp.ast.typing.TypingContext;

interface IntExpectedType extends ExpectedType {

    @Override
    default Type expectedType(TypingContext context) {
        return IntType.getInstance();
    }
}