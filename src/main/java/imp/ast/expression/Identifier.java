package imp.ast.expression;

import com.microsoft.z3.*;
import imp.ast.ASTNode;
import imp.ast.ASTVisitor;
import imp.ast.typing.FunctionType;
import imp.ast.typing.Type;
import imp.ast.typing.data.DataType;
import imp.interpreter.Z3ASTInterpreter;

import java.util.Objects;

public final class Identifier extends ASTNode implements Z3ASTInterpreter {

    private final String name;
    private Type type;

    public Identifier(String name) {
        Objects.requireNonNull(name);

        if (name.isBlank()) {
            throw new IllegalArgumentException("Identifier name cannot be blank");
        }

        if (!Character.isLetter(name.charAt(0))) {
            throw new IllegalArgumentException("Identifier name must start with a letter");
        }

        for (int i = 1; i < name.length(); i++) {
            if (!Character.isLetterOrDigit(name.charAt(i))) {
                throw new IllegalArgumentException("Identifier name must contain only letters and digits");
            }
        }

        this.name = name;
    }

    public String name() {
        return name;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Identifier that = (Identifier) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public void accept(ASTVisitor v) {
        v.visit(this);
    }

    public AST interpret(Context ctx) {
        if (type instanceof DataType) {
            Sort sort = ((DataType) type).interpret(ctx);
            return ctx.mkConst(name, sort);
        } else if (type instanceof FunctionType functionType) {
            Sort[] argSorts = functionType.getParameterTypes().stream().map(dataType -> dataType.interpret(ctx)).toArray(Sort[]::new);
            Sort returnSort = functionType.getReturnType().interpret(ctx);
            return ctx.mkFuncDecl(name, argSorts, returnSort);
        } else {
            throw new IllegalStateException("Unknown type: " + type);
        }
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Type getType() {
        return type;
    }
}
