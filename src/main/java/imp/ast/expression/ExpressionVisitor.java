package imp.ast.expression;

import imp.ast.expression.array.NewArrayExpression;
import imp.ast.expression.binary.bool.compare.*;
import imp.ast.expression.binary.bool.logic.AndExpression;
import imp.ast.expression.binary.bool.logic.ImpliesExpression;
import imp.ast.expression.binary.bool.logic.OrExpression;
import imp.ast.expression.binary.integer.*;
import imp.ast.expression.bool.ExistsExpression;
import imp.ast.expression.bool.ForallExpression;
import imp.ast.expression.constant.bool.FalseExpression;
import imp.ast.expression.constant.bool.TrueExpression;
import imp.ast.expression.constant.integer.IntExpression;
import imp.ast.expression.unary.bool.NotExpression;
import imp.ast.expression.unary.integer.NegExpression;

public interface ExpressionVisitor extends TypeVisitor {

    void visit(ArrayRefExpression arrayRefExpression);

    void visit(VarRefExpression varRefExpression);

    void visit(ForallExpression forallExpression);

    void visit(ExistsExpression existsExpression);

    void visit(ArrayLengthExpression arrayLengthExpression);

    void visit(FuncCallExpression funcCallExpression);

    void visit(FalseExpression falseExpression);

    void visit(IntExpression intExpression);

    void visit(NewArrayExpression newArrayExpression);

    void visit(TrueExpression trueExpression);

    void visit(NotExpression notExpression);

    void visit(NegExpression negExpression);

    void visit(ImpliesExpression impliesExpression);

    void visit(OrExpression orExpression);

    void visit(MulExpression mulExpression);

    void visit(EqExpression eqExpression);

    void visit(LessThanOrEqualExpression lessThanOrEqualExpression);

    void visit(SubExpression subExpression);

    void visit(ModExpression modExpression);

    void visit(GreaterThanOrEqualExpression greaterThanOrEqualExpression);

    void visit(AddExpression addExpression);

    void visit(LessThanExpression lessThanExpression);

    void visit(DivExpression divExpression);

    void visit(GreaterThanExpression greaterThanExpression);

    void visit(AndExpression andExpression);

}
