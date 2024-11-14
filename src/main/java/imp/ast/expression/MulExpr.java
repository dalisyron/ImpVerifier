package imp.ast.expression;

import imp.ast.Expression;

public record MulExpr(Expression left, Expression right) implements Expression {

    @Override
    public String toString() {
        String leftStr = formatExpression(left, 2);
        String rightStr = formatExpression(right, 2);
        return leftStr + " * " + rightStr;
    }

    private String formatExpression(Expression expr, int parentPrecedence) {
        String exprStr = expr.toString();
        int exprPrecedence = getExpressionPrecedence(expr);

        // Add parentheses if the expression's precedence is lower than the parent's
        if (exprPrecedence < parentPrecedence) {
            exprStr = "(" + exprStr + ")";
        }
        return exprStr;
    }

    // Method to get precedence of an expression
    private int getExpressionPrecedence(Expression expr) {
        if (expr instanceof AddExpr) return 1;
        if (expr instanceof MulExpr) return 2;
        return 3; // IntegerExpr and VariableExpr
    }
}
