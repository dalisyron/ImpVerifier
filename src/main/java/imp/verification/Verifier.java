package imp.verification;

import imp.ast.expression.ArrayLengthExpression;
import imp.ast.expression.ArrayRefExpression;
import imp.ast.expression.FuncCallExpression;
import imp.ast.expression.VarRefExpression;
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
import imp.ast.statement.*;
import imp.ast.typing.VoidType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.value.IntType;

public class Verifier {

    private static class VerifierVisitor implements StatementVisitor {

        @Override
        public void visit(ArrayRefExpression arrayRefExpression) {

        }

        @Override
        public void visit(VarRefExpression varRefExpression) {

        }

        @Override
        public void visit(ForallExpression forallExpression) {

        }

        @Override
        public void visit(ExistsExpression existsExpression) {

        }

        @Override
        public void visit(ArrayLengthExpression arrayLengthExpression) {

        }

        @Override
        public void visit(FuncCallExpression funcCallExpression) {

        }

        @Override
        public void visit(FalseExpression falseExpression) {

        }

        @Override
        public void visit(IntExpression intExpression) {

        }

        @Override
        public void visit(NewArrayExpression newArrayExpression) {

        }

        @Override
        public void visit(TrueExpression trueExpression) {

        }

        @Override
        public void visit(NotExpression notExpression) {

        }

        @Override
        public void visit(NegExpression negExpression) {

        }

        @Override
        public void visit(ImpliesExpression impliesExpression) {

        }

        @Override
        public void visit(OrExpression orExpression) {

        }

        @Override
        public void visit(MulExpression mulExpression) {

        }

        @Override
        public void visit(EqExpression eqExpression) {

        }

        @Override
        public void visit(LessThanOrEqualExpression lessThanOrEqualExpression) {

        }

        @Override
        public void visit(SubExpression subExpression) {

        }

        @Override
        public void visit(ModExpression modExpression) {

        }

        @Override
        public void visit(GreaterThanOrEqualExpression greaterThanOrEqualExpression) {

        }

        @Override
        public void visit(AddExpression addExpression) {

        }

        @Override
        public void visit(LessThanExpression lessThanExpression) {

        }

        @Override
        public void visit(DivExpression divExpression) {

        }

        @Override
        public void visit(GreaterThanExpression greaterThanExpression) {

        }

        @Override
        public void visit(AndExpression andExpression) {

        }

        @Override
        public void visit(IntType intType) {

        }

        @Override
        public void visit(BoolType boolType) {

        }

        @Override
        public void visit(IntArray intArray) {

        }

        @Override
        public void visit(BoolArray boolArray) {

        }

        @Override
        public void visit(VoidType voidType) {

        }

        @Override
        public void visit(AssignStatement assignStatement) {

        }

        @Override
        public void visit(BlockStatement blockStatement) {

        }

        @Override
        public void visit(IfStatement ifStatement) {

        }

        @Override
        public void visit(VariableDeclaration variableDeclaration) {

        }

        @Override
        public void visit(WhileStatement whileStatement) {

        }

        @Override
        public void visit(FuncCallStatement funcCallStatement) {

        }
    }
}
