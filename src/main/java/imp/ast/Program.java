
package imp.ast;

import imp.ast.method.MethodDeclaration;

import java.util.List;

public record Program(List<MethodDeclaration> procedures) {
}
