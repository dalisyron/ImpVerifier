package imp.ast.typing;

import imp.ast.typing.Type;
import imp.ast.typing.TypingContext;

public interface ExpectedType {

    Type expectedType(TypingContext context);
}