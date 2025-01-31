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
import imp.ast.typing.FunctionType;
import imp.ast.typing.VoidType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.value.IntType;

public interface TypeVisitor {
    void visit(IntType intType);

    void visit(BoolType boolType);

    void visit(IntArray intArray);

    void visit(BoolArray boolArray);

    void visit(VoidType voidType);
}
