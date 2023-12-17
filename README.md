# I/x86

A programming language that gives direct control of x86 processors. It is translated directly into x86 assembly, line by line. It will always have native access to all features of x86-64 processors, including its newest extensions.

The idea behind the programming language is that all programming is assembly programming, and this language helps the programmer program assembly. Once the programmer is happy with the instructions used, the generated assembly code is easy to recognize, and can be hand-tuned to unlock the final percentages of performance.

The language is set up such that it is as simple as possible and is easy to use. The compiler is not complicated, only about 1500 lines of code that anyone can understand.

Benefits of the language includes:

 * Simple language, simple compiler.
 * Access to all features of the x86 processor.
 * Use all features of the x86 processor to speed up your programs.
 * All behavior is defined and specified (No undefined or unspecified behavior.)
 * What you see is what you get. (No as-if rule.)
 * All the code is executed. (No surprising code removal.)
 * It is easy to further optimize the resulting assembly as the code will be a direct translation. It is easy to recognize all the code.

## The Language

[More about the language here.](language.md)

### Operations

Use the operations in the x86 CPU directly like this:

```
Add x, a, b
Sub x, a, b
Mul x, a, b
Div x, a, b
```

For example, the Div instruction is translated like this:

```
mov rax, qword [a]
mov rdx, 0
div qword [b]
mov qword [x], rax
```

### Expressions

Or write inline expressions. Here `a / b + c * d` is the expression, `exp` marks the beginning of an expression, `a` marks the expression as an arithmetic expression and finally, `u64` says that the type of the expression is unsigned 64-bit.

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

```
Bgs fS
  u64 x
  u64 a
  u64 b
Ens
```

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
 a) Whether the parameters are variables or literals.
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
  u32a string
  u32 character
  u32 c
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

### Converting a String to Upper Case Using SIMD

```
Bgs ToUpperILS
  u8x32a str
  u64 len
  u64 len32
  u64 len1
  u64 i
  b1 tb10
  u8x32 a
  u8x32 z
  u8x32 x32
  u8x32 x
  u8x32 lcm
  b8x32 tb8x320
  b8x32 tb8x321
  b8x32 tb8x322
  u8x32 padding
Ens

Fnc ToUpperIL
  Len len, str
  Div len32, len, 32

  Mov a, 'a'
  Mov z, 'z'
  Mov x32, 32

  Mov i, 0
  Loop
  If exp b: i < len32
    Idr x, str, i

    exp b b8x32: lcm = (x >= a & x <= z) & x32
    exp a u8x32: x = x - lcm

    Idw str, i, x

    Inc i
  EndLoop
Ret
```
## Building the Compiler/Translator
To build the translator you need Java 8 or higher. Simply run the following command to compile, or use your favourite IDE.

```
mkdir build
cd Translator
javac exec/Run.java -cp exec:Translator/main:imports -d ../build
```

To then make an executable jar-file, run the following:

```
cd ../build
jar cfe ../Translator.jar Run *
```

## Running the Translator/Compiler

Running the translator/compiler using class files:

```
java -classpath build Run
```

Or run it using the jar file:

```
java -jar Translator.jar
```

It takes four arguments
1) The input file with I/x86-code. e.g. `ioc.idx`
2) The output file where expressions have been expanded. e.g. `ioc.idx.1`
3) The assembly output file for NASM. e.g. `ioc.asm`
4) The C header for calling the functions from C. e.g. `ioc.h`

```
java -jar Translator.jar ioc.idx ioc.idx.1 ioc.asm ioc.h
```

## Build and Run the Examples
To build and run the examples, you need Java 8, GCC and NASM. First, make sure to build the compiler/translator as described above.

```
cd examples
bash make.sh
bash make-toupper.sh
```

This should give output similar to this:

```
Found: 1, Index: 4
ToUpper (plain C -O3 AVX2): 39599 us
ToUpperBL (C branchless v1 -O3 AVX2): 3018 us
ToUpperBL2 (C branchless v2 -O3 AVX2): 2207 us
ToUpper (I/x86 using AVX2): 3447 us
```




















