# Ivy Formal Verifier

Ivy is a library that provides a simple imperative language called **Imp** with a built-in deductive verifier using **SMT** (Satisfiability Modulo Theories) solvers. The core Imp language, as well as the specification syntax, closely matches that of the [Dafny](https://www.microsoft.com/en-us/research/project/dafny-a-language-and-program-verifier-for-functional-correctness/) project by Microsoft. In fact, Ivy is mostly our attempt to re-implement a simpler version of Dafny from the ground up for educational purposes.

Ivy parses and verifies Imp programs that include:
- **Pre-conditions** and **post-conditions** for methods
- **Loop invariants** in `while` loops
- Simplified control-flow constructs (if-else, while, assignment, etc.)
- Arithmetic and boolean expressions

Once the program is parsed, Ivy generates **verification conditions** and checks them using [Z3](https://github.com/Z3Prover/z3)'s Java API, automatically proving whether the program meets the requirements or not.

---

## Table of Contents

1. [Imp Language](#imp-language)
   - [Syntax](#syntax)
   - [Features](#features)
2. [Design & Architecture](#design--architecture)
   - [Imp Language](#imp-language-1)
   - [Clause Generator (AWP / AVC)](#clause-generator-awp--avc)
   - [Solver Integration](#solver-integration)
3. [Usage](#usage)
   - [Prerequisites](#prerequisites)
   - [Building and Running](#building-and-running)
   - [Example Usage](#example-usage)
4. [Testing](#testing)
5. [Limitations & Future Work](#limitations--future-work)
6. [Contributors](#contributors)
7. [References](#references)

---

## Imp Language

**Imp** is a small imperative language extended with logical annotations (inspired by Dafny). The language includes:

### Syntax

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
- **Composite Statements (S1; S2; ...; Sn)**  
  ```imp
  x = 5;
  y = 2;
  x = x * y;
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
4. **Methods**  
   - Syntax and Z3 conversion are supported for methods; however, verification for programs with method calls is still not implemented.

The concrete syntax is defined in [`Imp.g4`](./src/main/java/imp/parser/antlr/Imp.g4).  

---

## Design & Architecture

The library is organized into **four main modules**:

1. **Imp Language (package java/imp)**
2. **Z3 Interpreter (package java/interpreter)**
3. **Clause Generator (package java/verification/generator)**  
4. **Solver (ImpVerifier.java)**

### Imp Language

- **Lexer & Parser**  
  Implemented via [ANTLR](https://www.antlr.org/) using the file [`Imp.g4`](./src/main/java/imp/parser/antlr/Imp.g4). The generated code tokenizes and parses Imp source into a parse tree.
- **AST & AST Builder**  
  The `ASTBuilder` performs a parse-tree walk to construct a high-level, in-memory **AST** (Abstract Syntax Tree).  
- **Type Checker**  
  The `TypeChecker` ensures that programs are well-typed.
  
### Clause Generator (AWP / AVC)

Ivy uses **AWP** (Approximate Weakest Precondition) and **AVC** (Approximate Verification Condition) techniques to generate verification conditions.

### Solver Integration

Ivy uses the **Z3 Java API** to check the satisfiability of generated verification conditions.

---

## Usage

### Prerequisites

1. **Java** (JDK 8+)
2. **Maven** (for building)
3. **Z3 Java bindings**

### Building and Running

```bash
mvn clean package
```

Run the verifier on an `.imp` file:

```bash
java -cp lib/com.microsoft.z3.jar:lib/antlr4-runtime-4.13.2.jar:target/imp-verifier-1.0.jar imp.ImpVerifier path/to/yourProgram.imp
```

### Example Usage

```bash
java -cp lib/com.microsoft.z3.jar:lib/antlr4-runtime-4.13.2.jar:target/imp-verifier-1.0.jar imp.ImpVerifier SumOfFirstNNumbers.imp
```

---

## Testing

Run unit and integration tests with:

```bash
mvn test
```

---

## Limitations & Future Work

1. **Function calls and arrays** are syntactically allowed, but their verification is not yet implemented. 
2. **Counterexample reporting** from Z3 is not implemented. That is, currently we determine whether the program meets the specification or not, but we don't provide the user an input/execution-path on where the code violates the specifications. This can be integrated by re-interpreting the falsifying *model* from Z3 to the corresponding Imp control/data flow elements.

---

## Contributors

This project was done in collaboration with my amazing teammates:
- Adam ([@mistyrainforest](https://github.com/mistyrainforest))
- Yuehao Hu ([@yuehaohu](https://github.com/yuehaohu))

An earlier version of Ivy was our submission for the final project of the graduate-level course _CMPT 770 - Formal Verification_ at Simon Fraser University.

---

## References

- [Dafny Project by Microsoft](https://www.microsoft.com/en-us/research/project/dafny-a-language-and-program-verifier-for-functional-correctness/)
- [Z3 Theorem Prover](https://github.com/Z3Prover/z3)
- [ANTLR Parser Generator](https://www.antlr.org/)
- [Imp Vim Plugin](https://github.com/dalisyron/imp-vim-plugin)

---

Happy verifying! Feel free to open issues or contribute via pull requests.


