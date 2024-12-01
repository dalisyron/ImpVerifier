package imp.ast.expression.bool;

import imp.ast.typing.ExpectedType;
import imp.ast.typing.BoolType;
import imp.ast.typing.TypingContext;

interface BoolExpectedType extends ExpectedType {

    @Override
    default BoolType expectedType(TypingContext context) {
        return BoolType.getInstance();
    }
}