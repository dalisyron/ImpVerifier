package imp.interpreter;

import com.microsoft.z3.*;
import imp.ast.Invariant;
import imp.ast.InvariantList;
import imp.ast.condition.EnsuresClause;
import imp.ast.condition.RequiresClause;
import imp.ast.expression.*;
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
import imp.ast.statement.Condition;
import imp.ast.typing.FunctionType;
import imp.ast.typing.Type;
import imp.ast.typing.VoidType;
import imp.ast.typing.data.DataType;
import imp.ast.typing.data.array.BoolArray;
import imp.ast.typing.data.array.IntArray;
import imp.ast.typing.data.value.BoolType;
import imp.ast.typing.data.value.IntType;
import imp.ast.typing.data.value.PrimitiveType;
import imp.ast.expression.Identifier;
import imp.ast.expression.VarRefExpression;
import imp.ast.expression.ArrayRefExpression;

import java.util.List;

public final class Z3Interpreter {

    private final Context ctx;
    private final Z3InterpreterVisitor visitor;

    private Z3Interpreter(Context ctx) {
        this.ctx = ctx;
        this.visitor = new Z3InterpreterVisitor(ctx);
    }

    public static Z3Interpreter create(Context ctx) {
        return new Z3Interpreter(ctx);
    }

    public Expr interpret(Expression expression) {
        return visitor.interpret(expression);
    }

    public BoolExpr interpret(InvariantList invariants) {
        BoolExpr I = ctx.mkTrue();
        for (Invariant invariant : invariants.invariants()) {
            Expression invariantExpression = invariant.expression();
            I = ctx.mkAnd(I, interpret(invariantExpression));
        }
        return I;
    }

    public BoolExpr interpret(RequiresClause condition) {
        return (BoolExpr) interpret(condition.expr());
    }

    public BoolExpr interpret(EnsuresClause ensuresClause) {
        return (BoolExpr) interpret(ensuresClause.expr());
    }

    public BoolExpr interpret(Condition condition) {
        return (BoolExpr) interpret(condition.expression());
    }

    private static final class Z3InterpreterVisitor implements ExpressionVisitor {

        private final Context ctx;
        private Expr lastInterpretedExpression;
        private Sort lastInterpretedSort;

        public Z3InterpreterVisitor(Context ctx) {
            this.ctx = ctx;
        }

        public Expr interpret(Expression expression) {
            expression.accept(this);
            return lastInterpretedExpression;
        }

        public Sort interpret(Type type) {
            type.accept(this);
            return lastInterpretedSort;
        }


        @Override
        public void visit(ArrayRefExpression arrayRefExpression) {
            PrimitiveType elementType = arrayRefExpression.getElementType();
            Sort elementSort = interpret(elementType);
            Expr arrayExpr = ctx.mkArrayConst(arrayRefExpression.arrayName().name(), ctx.mkIntSort(), elementSort);
            Expr indexExpr = interpret(arrayRefExpression.indexExpr());
            lastInterpretedExpression = ctx.mkSelect(arrayExpr, indexExpr);
        }

        @Override
        public void visit(VarRefExpression varRefExpression) {
            DataType type = varRefExpression.getType();
            Sort sort = interpret(type);
            lastInterpretedExpression = ctx.mkConst(varRefExpression.variableName().name(), sort);
        }

        @Override
        public void visit(ForallExpression forallExpression) {
            DataType dataType = forallExpression.getType();
            Sort sort = interpret(dataType);
            Expr boundVar = ctx.mkConst(forallExpression.variable().name(), sort);
            Expr bodyExpr = interpret(forallExpression.body());
            if (!(bodyExpr instanceof BoolExpr)) {
                throw new IllegalStateException("Forall body must be a BoolExpr");
            }
            lastInterpretedExpression = ctx.mkForall(
                    new Expr[] { boundVar },
                    (BoolExpr) bodyExpr,
                    1,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        public void visit(ExistsExpression existsExpression) {
            DataType dataType = existsExpression.getType();
            Sort sort = interpret(dataType);
            Expr boundVar = ctx.mkConst(existsExpression.variable().name(), sort);
            Expr bodyExpr = interpret(existsExpression.body());
            if (!(bodyExpr instanceof BoolExpr)) {
                throw new IllegalStateException("Exists body must be a BoolExpr");
            }
            lastInterpretedExpression = ctx.mkExists(
                    new Expr[] { boundVar },
                    (BoolExpr) bodyExpr,
                    1,
                    null,
                    null,
                    null,
                    null
            );
        }

        @Override
        public void visit(ArrayLengthExpression arrayLengthExpression) {
            throw new UnsupportedOperationException("Not Implemented");
        }

        @Override
        public void visit(FuncCallExpression funcCallExpression) {
            // First make the FuncDecl
            Sort[] argSorts = funcCallExpression.getFunctionType().getParameterTypes().stream().map(this::interpret).toArray(Sort[]::new);
            Sort returnSort = interpret(funcCallExpression.getFunctionType().getReturnType());
            FuncDecl funcDecl = ctx.mkFuncDecl(funcCallExpression.identifier().name(), argSorts, returnSort);


            List<Expression> args = funcCallExpression.arguments();
            Expr[] interpretedArgs = new Expr[args.size()];
            for (int i = 0; i < args.size(); i++) {
                interpretedArgs[i] = interpret(args.get(i));
            }
            lastInterpretedExpression = ctx.mkApp(funcDecl, interpretedArgs);
        }

        @Override
        public void visit(FalseExpression falseExpression) {
            lastInterpretedExpression = ctx.mkFalse();
        }

        @Override
        public void visit(IntExpression intExpression) {
            lastInterpretedExpression = ctx.mkInt(intExpression.value());
        }

        @Override
        public void visit(NewArrayExpression newArrayExpression) {
            throw new UnsupportedOperationException("Not Supported");
        }

        @Override
        public void visit(TrueExpression trueExpression) {
            lastInterpretedExpression = ctx.mkTrue();
        }

        @Override
        public void visit(NotExpression notExpression) {
            Expr child = interpret(notExpression.expression());
            if (!(child instanceof BoolExpr)) {
                throw new IllegalStateException("NotExpression child must be BoolExpr");
            }
            lastInterpretedExpression = ctx.mkNot((BoolExpr) child);
        }

        @Override
        public void visit(NegExpression negExpression) {
            Expr child = interpret(negExpression.expression());
            if (!(child instanceof ArithExpr)) {
                throw new IllegalStateException("NegExpression child must be ArithExpr");
            }
            lastInterpretedExpression = ctx.mkUnaryMinus((ArithExpr) child);
        }

        @Override
        public void visit(ImpliesExpression impliesExpression) {
            Expr leftExpr = interpret(impliesExpression.left());
            Expr rightExpr = interpret(impliesExpression.right());
            if (!(leftExpr instanceof BoolExpr) || !(rightExpr instanceof BoolExpr)) {
                throw new IllegalStateException("ImpliesExpression children must be BoolExpr");
            }
            lastInterpretedExpression = ctx.mkImplies((BoolExpr) leftExpr, (BoolExpr) rightExpr);
        }

        @Override
        public void visit(OrExpression orExpression) {
            Expr leftExpr = interpret(orExpression.left());
            Expr rightExpr = interpret(orExpression.right());
            if (!(leftExpr instanceof BoolExpr) || !(rightExpr instanceof BoolExpr)) {
                throw new IllegalStateException("OrExpression children must be BoolExpr");
            }
            lastInterpretedExpression = ctx.mkOr((BoolExpr) leftExpr, (BoolExpr) rightExpr);
        }

        @Override
        public void visit(MulExpression mulExpression) {
            Expr leftExpr = interpret(mulExpression.left());
            Expr rightExpr = interpret(mulExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("MulExpression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkMul((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(EqExpression eqExpression) {
            Expr leftExpr = interpret(eqExpression.left());
            Expr rightExpr = interpret(eqExpression.right());
            lastInterpretedExpression = ctx.mkEq(leftExpr, rightExpr);
        }

        @Override
        public void visit(LessThanOrEqualExpression lessThanOrEqualExpression) {
            Expr leftExpr = interpret(lessThanOrEqualExpression.left());
            Expr rightExpr = interpret(lessThanOrEqualExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("<= expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkLe((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(SubExpression subExpression) {
            Expr leftExpr = interpret(subExpression.left());
            Expr rightExpr = interpret(subExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("SubExpression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkSub((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(ModExpression modExpression) {
            Expr leftExpr = interpret(modExpression.left());
            Expr rightExpr = interpret(modExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("ModExpression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkMod((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(GreaterThanOrEqualExpression greaterThanOrEqualExpression) {
            Expr leftExpr = interpret(greaterThanOrEqualExpression.left());
            Expr rightExpr = interpret(greaterThanOrEqualExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException(">= expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkGe((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(AddExpression addExpression) {
            Expr leftExpr = interpret(addExpression.left());
            Expr rightExpr = interpret(addExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("+ expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkAdd((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(LessThanExpression lessThanExpression) {
            Expr leftExpr = interpret(lessThanExpression.left());
            Expr rightExpr = interpret(lessThanExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("< expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkLt((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(DivExpression divExpression) {
            Expr leftExpr = interpret(divExpression.left());
            Expr rightExpr = interpret(divExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("/ expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkDiv((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(GreaterThanExpression greaterThanExpression) {
            Expr leftExpr = interpret(greaterThanExpression.left());
            Expr rightExpr = interpret(greaterThanExpression.right());
            if (!(leftExpr instanceof IntExpr) || !(rightExpr instanceof IntExpr)) {
                throw new IllegalStateException("> expression children must be IntExpr");
            }
            lastInterpretedExpression = ctx.mkGt((IntExpr) leftExpr, (IntExpr) rightExpr);
        }

        @Override
        public void visit(AndExpression andExpression) {
            Expr leftExpr = interpret(andExpression.left());
            Expr rightExpr = interpret(andExpression.right());
            if (!(leftExpr instanceof BoolExpr) || !(rightExpr instanceof BoolExpr)) {
                throw new IllegalStateException("AndExpression children must be BoolExpr");
            }
            lastInterpretedExpression = ctx.mkAnd((BoolExpr) leftExpr, (BoolExpr) rightExpr);
        }

        @Override
        public void visit(IntType intType) {
            lastInterpretedSort = ctx.mkIntSort();
        }

        @Override
        public void visit(BoolType boolType) {
            lastInterpretedSort = ctx.mkBoolSort();
        }

        @Override
        public void visit(IntArray intArray) {
            lastInterpretedSort = ctx.mkArraySort(ctx.mkIntSort(), ctx.mkIntSort());
        }

        @Override
        public void visit(BoolArray boolArray) {
            lastInterpretedSort = ctx.mkArraySort(ctx.mkIntSort(), ctx.mkBoolSort());
        }

        @Override
        public void visit(VoidType voidType) {
            lastInterpretedSort = ctx.mkUninterpretedSort("unit");
        }

    }
}
