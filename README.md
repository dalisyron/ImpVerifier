# Ivy Formal Verifier

Ivy is a library (and command-line tool) that provides a new, simplified imperative language called **Imp** with a built-in deductive verifier using **SMT** (Satisfiability Modulo Theories) solvers. Inspired by [Dafny](https://www.microsoft.com/en-us/research/project/dafny-a-language-and-program-verifier-for-functional-correctness/) from Microsoft, Ivy re-implements a simpler version of Dafny-like verification for educational purposes.

Ivy parses, typechecks, and verifies Imp programs that include:
- **Pre-conditions** and **post-conditions** for methods
- **Loop invariants** in `while` loops
- A subset of integer and boolean expressions
- Simplified control-flow constructs (if-else, while, assignment, etc.)

Once the program is parsed, Ivy generates **verification conditions** and checks them using [Z3](https://github.com/Z3Prover/z3)'s Java API, automatically proving correctness if all verification conditions are satisfiable (or unsatisfiable in ways that guarantee correctness).

---

## Table of Contents

1. [Imp Language](#imp-language)
   - [Features](#features)
   - [Syntax](#syntax)
2. [Design & Architecture](#design--architecture)
   - [Parser](#parser)
   - [Clause Generator (AWP / AVC)](#clause-generator-awp--avc)
   - [Solver Integration](#solver-integration)
3. [Usage](#usage)
   - [Prerequisites](#prerequisites)
   - [Building and Running](#building-and-running)
   - [Example Usage](#example-usage)
4. [Testing](#testing)
5. [Limitations & Future Work](#limitations--future-work)
6. [References](#references)

---

## Imp Language

**Imp** is a small imperative language extended with logical annotations (inspired by Dafny). The language includes:

- **Assignments**  
  ```imp
  x = x + 1;
  ```
- **If-Else**  
  ```imp
  if (x < 10) {
      x = x + 1;
  } else {
      x = x - 1;
  }
  ```
- **Statement Composition (Blocks)**  
  ```imp
  {
    x = 5;
    y = 2;
    x = x * y;
  }
  ```
- **While Loops** with **invariants**  
  ```imp
  while (x < 10)
      invariant x >= 0
      invariant x <= 10
  {
      x = x + 1;
  }
  ```
- **Method Declarations** with **pre-conditions** (`requires`) and **post-conditions** (`ensures`)  
  ```imp
  method Foo(int n) returns (int result)
      requires n >= 0
      ensures result == 2 * n
  {
      result = 2 * n;
  }
  ```

### Features

1. **Expressions**  
   - Boolean: `&&`, `||`, `!`, `==>`, `<`, `>`, `<=`, `>=`, `==`
   - Integer: `+`, `-`, `*`, `/`, `%`, unary minus
   - Quantifiers: `forall (int i) :: ...`, `exists (bool b) :: ...`
2. **Pre/Post Conditions**  
   - `requires <bool expr>`
   - `ensures <bool expr>`
3. **Loop Invariants**  
   - `while (cond) invariant <bool expr> { ... }`
4. **Methods** (Syntax is supported; nested function calls can appear, but full function-spec verification is currently simplified.)

The concrete syntax is defined in [`Imp.g4`](./src/main/java/imp/parser/antlr/Imp.g4).  

---

## Design & Architecture

The verifier is organized into **three main modules**:

1. **Parser**  
2. **Clause Generator**  
3. **Solver**  

### Parser

- **Lexer & Parser**  
  Implemented via [ANTLR](https://www.antlr.org/) using the file [`Imp.g4`](./src/main/java/imp/parser/antlr/Imp.g4). The generated code tokenizes and parses Imp source into an **AST**.
- **AST & AST Builder**  
  The `ASTBuilder` performs a parse-tree walk to construct a high-level, in-memory **AST** (Abstract Syntax Tree).  
- **Type Checker**  
  The `TypeChecker` ensures that programs are well-typed (e.g., no assigning a `bool` to an `int`, all referenced variables are declared, array indices are `int`, etc.).
- **Z3 AST Conversion**  
  Each node in the Imp AST can also be converted into a corresponding Z3 expression. For instance, `x + y` in Imp is converted into `ctx.mkAdd(xExpr, yExpr)` in Z3.

### Clause Generator (AWP / AVC)

Following the classic rules of **weakest precondition** or **verification condition** generation, Ivy uses:
- **AWP** (Annotated Weakest Precondition)  
- **AVC** (Annotated Verification Condition)  

Each statement type (assignment, if-else, while loop, etc.) has an implementation of `awp(...)` and `avc(...)`:
1. `AWP`: Recursively computes the weakest precondition that ensures a given post-condition `Q` holds.  
2. `AVC`: Computes any required auxiliary conditions needed to prove correctness (e.g., loop invariants, if-else paths).

The library includes separate classes for each statement form:
- `Assignment`  
- `IfElse`  
- `While`  
- `Declaration`  
- `Composition` (for block statements)  

By splitting out the logic per statement type, new statements (like `for` loops) can be integrated by adding corresponding `awp` and `avc` implementations.

### Solver Integration

Ivy uses the **Z3 Java API** to interpret the generated verification conditions and check their satisfiability. If Z3 proves the conditions unsatisfiable in the “right way” (meaning no counterexample to correctness), Ivy concludes that the original Imp program meets its specifications (pre/post/invariants).

---

## Usage

### Prerequisites

1. **Java** (JDK 8+)
2. **Maven** (for building) or a similar Java build tool
3. **Z3 Java bindings**  
   - The repository includes a reference to `com.microsoft.z3.jar`. Ensure it is available in your classpath.

### Building and Running

1. **Clone** this repository and navigate to its root directory.
2. **Build** with Maven:
   ```bash
   mvn clean package
   ```
3. **Run** the verifier on an `.imp` file. For example:
   ```bash
   java \
     -cp lib/com.microsoft.z3.jar:lib/antlr4-runtime-4.13.2.jar:target/imp-verifier-1.0.jar \
     verification.ImpVerifier path/to/yourProgram.imp
   ```
   - The above command references:
     - `com.microsoft.z3.jar`: Z3 solver library
     - `antlr4-runtime-4.13.2.jar`: ANTLR runtime
     - `imp-verifier-1.0.jar`: The built Ivy library/tool

### Example Usage

Suppose you have a program `SumOfFirstNNumbers.imp`:

```imp
method SumOfFirstNNaturalNumbers(int n) returns (int sum)
    requires n >= 0
    ensures sum == n * (n + 1) / 2
{
    sum = 0;
    int i = 1;
    while (i <= n)
        invariant sum == i * (i - 1) / 2
        invariant i >= 1 && i <= n + 1
    {
        sum = sum + i;
        i = i + 1;
    }
}
```

You can verify it by running:

```bash
java \
  -cp lib/com.microsoft.z3.jar:lib/antlr4-runtime-4.13.2.jar:target/imp-verifier-1.0.jar \
  verification.ImpVerifier SumOfFirstNNumbers.imp
```

The output will indicate whether the method’s verification conditions are valid (e.g., “Method SumOfFirstNNaturalNumbers is valid”).

---

## Testing

- **Unit Tests**  
  Found in `src/test/java/...`, covering individual statement translation to Z3, type checking, etc.
- **Integration Tests**  
  Also in `src/test/java/...`, which exercise the full parse-check-verify pipeline on example `.imp` files, some ported from Dafny samples.
- **How to run tests**  
  ```bash
  mvn test
  ```

---

## Limitations & Future Work

1. **No arrays of arrays or nested loops** (limited array and loop support).
2. **Function calls** are allowed syntactically, but advanced modular verification is not yet implemented.
3. **For-loops**, advanced data structures, etc. are not fully supported.
4. **Loop invariants** may need to be stronger than typical Dafny proofs (due to the weakest postcondition approach).
5. **Error messages** are fairly minimal if verification fails.

**Potential improvements**:
- Add **nested loop** support
- Implement **arrays of arrays** or array resizing
- More robust **counterexample** reporting from Z3
- Expanding **method contracts** to enable richer modular verification

---

## References

- [Dafny Project by Microsoft](https://www.microsoft.com/en-us/research/project/dafny-a-language-and-program-verifier-for-functional-correctness/)  
- [Z3 Theorem Prover](https://github.com/Z3Prover/z3)  
- [ANTLR Parser Generator](https://www.antlr.org/)  

Additionally, a Vim syntax highlighting plugin for `.imp` files is available here:  
[https://github.com/dalisyron/imp-vim-plugin](https://github.com/dalisyron/imp-vim-plugin)

---

Happy verifying! Feel free to open issues or contribute via pull requests.
