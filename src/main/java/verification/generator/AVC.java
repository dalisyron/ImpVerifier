package verification.generator;

import com.microsoft.z3.BoolExpr;
import com.microsoft.z3.Context;

import imp.ast.statement.AssignStatement;
import imp.ast.statement.BlockStatement;
import imp.ast.statement.IfStatement;
import imp.ast.statement.Statement;
import imp.ast.statement.VariableDeclaration;
import imp.ast.statement.WhileStatement;

public class AVC {
    private static AVC instance;

    private AVC() {
    }

    public BoolExpr avc(Context ctx, Statement statement, BoolExpr Q) {
        if (statement.getClass() == BlockStatement.class) {
            return Composition.getInstance().avc(ctx, (BlockStatement)statement, Q);
        } else if (statement.getClass() == AssignStatement.class) {
            return Assignment.getInstance().avc(ctx, (AssignStatement)statement, Q);
        } else if (statement.getClass() == IfStatement.class) {
            return IfElse.getInstance().avc(ctx, (IfStatement)statement, Q);
        } else if(statement.getClass() == WhileStatement.class) {
            return While.getInstance().avc(ctx, (WhileStatement)statement, Q);
        } else if(statement instanceof VariableDeclaration) {
            return Declaration.getInstance().avc(ctx, (VariableDeclaration)statement, Q);
        } else {
            throw new RuntimeException("Non-implemented AVC");
        }
    }

    public static AVC getInstance() {
        if (instance == null) {
            instance = new AVC();
        }
        return instance;
    }
}
