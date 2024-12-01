package imp.ast.expression;

public class TypeCheckingException extends RuntimeException {

    public TypeCheckingException(Expression expression) {
        super("Type checking failed: could not resolve expression " + expression + " to expected type " + expression.expectedType(null));
    }
}