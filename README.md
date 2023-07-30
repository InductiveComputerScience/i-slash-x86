# I/x86

A programming language that gives direct control of x86 processors. It is translated directly into x86 assembly, line by line. It will always have native access to all features of x86-64 processors, including its newest extensions.

The language is set up such that it is as simple as possible and is easy to use. The compiler is not complicated, only about 1500 lines of code that anyone can understand.

Benefits of the language includes:

 * Simple language, simple compiler.
 * Access to all features of the x86 processor.
 * Use all features of the x86 processor to speed up your programs.
 * All behavior is defined and specified (No undefined or unspecified behavior.)
 * What you see is what you get. (No as-if rule.)
 * All the code is executed. (No surprising code removal.)
 * It is easy to further optimize the resulting assembly as the code will be a direct translation.

## The Language

### Operations

Use the operations in the x86 CPU directly like this:

```
Add x, a, b
Sub x, a, b
Mul x, a, b
Div x, a, b
```

### Expression

Or write inline expressions. Here a / b + c * d is the expression, "exp" marks the beginning of an expression, "a" marks the expression as an Arithmetic expression and finally, u64 says that the type of the expression is unsigned 64-bit.

```
exp a u64: x = a / b + c * d
```

Here, the type is 64-bit float, or double precision.

```
exp a f64: h1 = (x2 - x)/(x2 - x1)*q11 + (x - x1)/(x2 - x1)*q12
```

There are also bitwise expressions

```
exp bw b32: total = d2 | d1 << 8 | d0 << 16
```

And boolean-relational expressions

```
exp b: i < len & !found
```

In the future, there will be more kinds of expressions. You can easily extend the compiler with your own expression types.

The three expression types listed here are based on the open-source formula translators:

 * [Arithmetic Formula Translation](https://repo.progsbase.com/repoviewer/no.inductive.libraries/FormulaTranslation/0.1.6///ArithmeticFormulaToTFormFunctions/)
 * [Bitwise Formula Translation](https://repo.progsbase.com/repoviewer/no.inductive.libraries/FormulaTranslation/0.1.6///BitwiseFormulaToTFormFunctions/)
 * [Boolean-Relational Formula Translation](https://repo.progsbase.com/repoviewer/no.inductive.libraries/FormulaTranslation/0.1.6///BooleanFormulaToTFormFunctions/)

### Functions

Make a function `f` as follows:

```
Fnc f
  ...
Ret
```

The input, output and local variables are set up in a structure with the same name as the function followed by `S`.

Bgs fS
  u64 x
  u64 a
  u64 b
Ens

### Calling Functions

Call functions by passing it an instance of its structure. You set the input before calling the function and read the output after calling the function.

```
Call f, i
```

A header is generated for C/C++ for easily calling the functions:

```
#include "code.h"

...

struct fS s;
s.a = 5;
s.b = 5;
f(&s);
// s.x contains the return value.
```


## How the Compiler Works

### Overview

1. The compiler traverses the code to convert all expressions to a series of instructions.
2. The compiler traverses the code to identify the type of each instruction. The type includes:
 a) Wether the parameters are variables or literals.
 b) The type of the operands.
3. A C-header is generated.
3. NASM processes the macros and assembles the code.

### Details

1. The compiler will translate the expression into a series of CPU operations (by traversing the expression left-first):

```
; exp a u64: x = a / b + c * d
Div t0, a, b
Mul t1, c, d
Add x, t0, t1
```

2. Then identify the types. Here "mm" means both parameters are memory, u64 means unsigned 64-bit integer division.

```
Div.mmu64 t0, a, b
...
```

Then convert each of these to a series of CPU-instructions with explicit handling of CPU registers. This one performs 64-bit unsigned integer division of two variables.

```
mov rax, qword [rdi + a]
mov rdx, 0
mov rcx, qword [rdi + b]
div rcx
mov qword [rdi + t0], rax
...
```

This can then be passed to an assembler, NASM, to complete the compilation.


## Examples

### Finding the index of a character in a string
```
Fnc IndexOfCharacter
  Mov found, false
  Mov i, 0
  Loop
    Len len, string
  If exp b: i < len & !found
    Idr c, string, i
    If exp b: c = character
      Mov found, true
      Acw indexReference, numberValue, i
    Endb
    Inc i
  EndLoop
  Mov retval, found
Ret
```

The input, output and local variables are given in a separate structure.

```
Bgs NumberReference
  u64 numberValue
Ens

Bgs IndexOfCharacterS
  u16a string
  u16 character
  u16 c
  str NumberReference indexReference
  u8 retval
  u64 i
  b1 found
  u64 len
  b1 tb10
  b1 tb11
  b1 tb12
Ens
```

A header for C is also generated, so that you can call the functions from C or C++.



### Getting Random Numbers Using Rdrand

```
Bgs RdrandGuaranteedS
  u64 n
  b1 c
  b1 tb10
Ens

Fnc RdrandGuaranteed
  Mov c, 0
  Loop
  If exp b: c = 0
    Rdrand n, c
  EndLoop
Ret
```


