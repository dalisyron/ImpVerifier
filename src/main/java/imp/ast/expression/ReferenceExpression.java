package imp.ast.expression;

import imp.ast.ASTVisitor;

public sealed abstract class ReferenceExpression extends Expression permits ArrayRefExpression, VarRefExpression {

}
