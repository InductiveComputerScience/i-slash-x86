# A Simple Way to Create a Powerfull Language

The following is a description of a simple way to create a powerful programming language. It uses few traditional techniques, but instead translates a program almost directly to assembly/machine code.

The compiler/translator is [available as open source here](https://github.com/InductiveComputerScience/i-slash-x86).

The way this document described the language is that we start with standard x86-64 assembly and removes part of it gradually as to become a real programming language. The compiler then works in reverse, gradually converting a program to assembly by simple steps.

The way to create a programming language has significant benefits:

* This makes the compiler a lot simpler, and we can be sure that the program does exactly what we tell it to: No undefined behavior, no unspecified behavior, no as-if rule, no surprising code removal. In this language, your wish is the computer's command.
* The simplicity of the compiler means the compiler and language is easier to understand, work with and extend.

## Semantics -- The Meaning of Programs

What we write in a programming language must have some meaning: We need data types and operations on them. We can create our own specification, with types, operators and other behavior, or we can use an existing one.

For example, for the C programming language, the specification sets up a "C virtual machine" and explains all the behavior of the language based on it, and not on any real computer. This is why coding C feels a bit spurious. Most professionals will code C as if they are coding the CPU directly, which often works OK.

 Whatever we choose, we must translate our code to assembly or machine code in order to actually run the code.

A simple choice is the CPU itsef. It has specifications for both data types and operations. If we choose it, we don't have to convert from one meaning to another. In C, for example, the compiler must generate code for the C virtual machine to run on the actual CPU.

For the language described here, let us choose the Intel/AMD x86-64 CPU. Note, however, the approach that follows will work for any CPU.

## The Compiler

Thus, we start with x86-64 assembly. We can use an assembler to convert assembly to machine code. For this example, we will choose NASM, a free assembler that works with x86-64 assembly.

### Registers

The first thing we would like to remove from our language, is the need to work with registers. Working with registers is too low level, and is covered at the end as further optimizations.

For example, on the Intel x86-64, if we would like to compute `x = a / b`, we need to place `0` in `rdx`, `a` in `rax`, divide by `b`, and finally read the result from `rax`. Remembering this is tedious.

```
mov rax, qword [a]
mov rdx, 0
div qword [b]
mov qword [x], rax
```

Let's instead use a register-free form, or, rather, a memory-to-memory form. The following instruction computes `x = a / b` without having to work with the registers or with addressing.

```
Div.mmu64 x, a, b
```

To make this work, we need to define the following macro for NASM:

```
%macro Div.mmu64 3
  mov rax, qword [rdi + %2]
  mov rdx, 0
  div qword [rdi + %3]
  mov qword [rdi + %1], rax
%endmacro
```

The syntax of the macro name is as follows:

* "Div" is the name of the main instruction in the macro. It is capitalized to distinguish it from the real instruction. It can also name the functionality of the macro, which might include a few more instructions, for example DivMod would return the quotient and modulus of the division.
* ".mmu64" shows two things: 
  * "mm" means that the two operands are memory operands. 
  * "u64" means that the type of the operands are unsigned 64-bit integers. 

### Literals

We want to be able to write literals directly into our language. We need to make separate macros for instructions with literal operands, as they are done differently on x86-64 assembly. This one, for example, takes two literals and is therefore called "ii". If only the first or second operand is a literal, it would be "im" or "mi", respectively.

```
Div.iiu64 x, 6, 2
```

```
%macro Div.iiu64 3
  mov rax, %2
  mov rdx, 0
  mov rcx, %3
  div rcx
  mov qword [rdi + %1], rax
%endmacro
```

### Types

It is annoying to having to put types on all our instructions. Instead, we can put type specifications in a separate structure and compute the types. We can also compute whether the operands are literals or memory. The convention is that for a function `X`, its structure is called `XS`. It contains the input, output and working memory (local variables) of the function.

 * `Bgs` means "BeGin Structure". 
 * `Ens` means "ENd Structure".
 * `Fnc` means "begin FuNCtion"
 * `Ret` ends the function.

```
Bgs fS
  u64 x, a, b
Ens

Fnc f
  Div x, a, b
Ret
```

A simple translator can compute the following:

```
Bgs fS
  u64 x, a, b
Ens

Fnc f
  Div.mmu64 x, a, b
Ret
```

#### Interoperability with C

We can also use this step to produce a C-header for calling the functions we make from C.

```
#pragma pack(push, 1)
struct fS{
  uint64_t x;
  uint64_t a;
  uint64_t b;
};
#pragma pack(pop)

void f(struct fS *args);
```

We can now call the function as follows:

```
#include "ourfunctions.h"

int main(){
  struct fS s;

  s.a = 6;
  s.b = 3;

  f(&s);
  
  // Result is in s.x
}
```

### Labels
Another aspect of assembly is the use of labels for control flow. For example:

```
If c, L1
  Div x, a, b
Endb L1
```

In this example, the `If` instruction continues if `c` is `true` and jumps to `L1` if  `c` is `false`. `Endb` simply ends with the label. Here are the macros:

```
%macro If.mb1 2
  mov al, 0
  mov ah, [rdi + %1]
  cmp al, ah
  je %2
%endmacro

%macro Endb 1
  %1:
%endmacro
```

We don't want to remember to match up those labels, so we will use a program to compute the labels. Thus, the label-free version becomes:

```
If c
  Div x, a, b
Endb
```

Notice the use of indentation to signal the beginning and end of the If code block.

The same for goes for loops. Here we also indent the computation of the loop-condition for clarity.

```
; initializer
Mov i, 0
Loop L1
  ; condition
  Lt c, i, 5
If c, L2
  ; body
  ...
  ; increment
  Inc i
EndLoop L1, L2
```

The macros are simply:

```
%macro Loop 1
  %1:
%endmacro

%macro EndLoop 2
  jmp %1
  %2:
%endmacro
```

We can also easily compute all those labels. Thus the code becomes:

```
; initializer
Mov i, 0
Loop
  ; condition
  Lt c, i, 5
If c
  ; body
  ...
  ; increment
  Inc i
EndLoop
```

Now we have register-free, label-free and type-suffix-free assembly. A great start for a simple but powerful language! It even has full support for all CPU-features, types and instructions.

### Expressions

We also want to be able to write expressions. The following formula is part of a bilinear scale up.

`h1 = (x2 - x)/(x2 - x1)*q11 + (x - x1)/(x2 - x1)*q12`

If we coded this in the language as it is now, it would look like this:

```
Sub t0, x2, x 
Sub t1, x2, x1 
Div t2, t0, t1 
Mul t0, t2, q11 
Sub t1, x, x1 
Sub t2, x2, x1 
Div t3, t1, t2 
Mul t1, t3, q12 
Add h1, t0, t1 
```

Let's instead use a formula translator that will automatically code this for us based on an expression. We start the line with `exp` indicating an expression. We then give the type `a` for "arithmetic" and the type `f64` for 64-bit floating point. 

```
exp a f64: h1 = (x2 - x)/(x2 - x1)*q11 + (x - x1)/(x2 - x1)*q12
```

This will then produce the following code. Notice that the expression is put in a comment above the automatically generated code. The temporary variables are named `t`, followed by the type, followed by a number. We need four temporary variables to do this expression.

```
; exp a f64: h1 = (x2 - x)/(x2 - x1)*q11 + (x - x1)/(x2 - x1)*q12
Sub tf640, x2, x 
Sub tf641, x2, x1 
Div tf642, tf640, tf641 
Mul tf640, tf642, q11 
Sub tf641, x, x1 
Sub tf642, x2, x1 
Div tf643, tf641, tf642 
Mul tf641, tf643, q12 
Add h1, tf640, tf641 
```

In order to convert the expression into the code above, we need to set up a lexer, parser and code generator. This is quite straight forwards for an expression of a single type with variables and literals of a single type.

Other expression types include boolean-relational. `b1` is the default type, if none is given.

```
exp b: i < len & !found
```

Which converts to:

```
; exp b: i < len & !found
Lt tb10, i, len 
Not tb11, found 
And tb12, tb10, tb11 
```

and bitwise formulas. This one has 32 bits, and is a part of the base64 encoding algorithm.

```
exp bw32: total = d2 | d1 << 8 | d0 << 16
```

This  converts to:

```
; exp bw32: total = d2 | d1 << 8 | d0 << 16
Shl tb320, d1, 8
Or tb321, d2, tb320
Shl tb320, d0, 16
Or total, tb321, tb320
```

This can support all types and many, many more expression types. For example, arithmetic expressions can easily be extended to SIMD, u8x32 for example, which is a type of 32 u8s, or bytes.

### Further Optimization
We can use NASM to generate the final assembly for us. Then it is quite simple to hand-tune the assembly by using registers instead of temporary variables. It should be possible to create almost completely optimal assembly with this approach.

```
nasm -E asm.asm > asm-exp.asm
```

Thus we can hand-optimize the following:

```
; If exp b: i < len & !found

; LessThan tb10, i, len 
mov rax, qword [rdi + IndexOfCharacterS.i]
mov rdx, qword [rdi + IndexOfCharacterS.len]
cmp rax, rdx
setb al
mov byte [rdi + IndexOfCharacterS.tb10], al

; Not tb11, found 
mov al, byte [rdi + IndexOfCharacterS.found]
not al
and al, 1
mov byte [rdi + IndexOfCharacterS.tb11], al

; And tb12, tb10, tb11 
mov al, byte [rdi + IndexOfCharacterS.tb10]
mov ah, byte [rdi + IndexOfCharacterS.tb11]
and al, ah
and al, 1
mov byte [rdi + IndexOfCharacterS.tb12], al

; If tb12
mov al, 0
mov ah, byte [rdi + IndexOfCharacterS.tb12]
and ah, 1
cmp al, ah
je L2
```

To:

* Place `byte [rdi + IndexOfCharacterS.tb10]` in r8l
* Place `byte [rdi + IndexOfCharacterS.tb11]` in r9l
* Place `byte [rdi + IndexOfCharacterS.tb12]` also in r8l

```
; If exp b: i < len & !found

; LessThan $r8l, i, len 
mov rax, qword [rdi + IndexOfCharacterS.i]
mov rdx, qword [rdi + IndexOfCharacterS.len]
cmp rax, rdx
setb al
mov r8l, al

; Not $r9l, found 
mov al, byte [rdi + IndexOfCharacterS.found]
not al
and al, 1
mov r9l, al

; And $r8l, $r8l, $r9l 
mov al, r8l
mov ah, r9l
and al, ah
mov r8l, al

; If tb12
mov al, 0
mov ah, r8l
cmp al, ah
je L2
```

The finally, collapse the aliased registers:

```
; If exp b: i < len & !found

; $r8l = i < len 
mov rax, qword [rdi + IndexOfCharacterS.i]
cmp rax, qword [rdi + IndexOfCharacterS.len]
setb r8l

; $r9l = !found 
mov r9l, byte [rdi + IndexOfCharacterS.found]
not r9l
and r9l, 1

; $r8l = $r8l & $r9l 
and r8l, r9l

; If $r8l
cmp r8l, 0
je L2
```

An optimizing C compiler:

```
gcc -O3 -S -masm=intel testasm.c
```

Given something like this:

```
_Bool IndexOfCharacter(
  wchar_t *string, 
  size_t stringLength, 
  wchar_t character, 
  struct NumberReference *indexReference, 
  size_t i
){
  return i < stringLength && !found;
}
```

Will code it out to something like the following. Notice that this code is a little less like the original than the version produced by our new language.

```
; i < len && !found

; $eax = i < len
mov rax, qword [rdi + IndexOfCharacterS.i]
cmp rax, qword [rdi + IndexOfCharacterS.len]
setb al

; $edx = !found
movzx edx, byte [rdi + IndexOfCharacterS.found]
xor edx, 1

; $eax = $eax & $edx
and eax, edx

; If $eax
...
```

Both gives about the same number of instructions. However, in general, it only makes sense to use this language if one of these holds true:

 * one can utilize the special instructions better than the optimizing compiler.
 * if one wants to make sure the program does exactly what it was programmed to do.
 * if one wants to use a simple language.

### Optimization of Bug-Free Code
If one has developed code that is completely portable and bug-free using the [progsbase language](https://www.progsbase.com) and development techniques, then creating an optimized version of a function is ideal using this language. 

 * The bug-free version can be used as a testing oracle for the optimized version.
 * We know that the implementation will not change as it is bug-free.

## Examples

### IndexOfCharacter

The following is an example of a function for finding the first index of a character in a UTF-16 string.

```
Bgs NumberReference
  u64 numberValue
Ens

Bgs IndexOfCharacterS
  u16a string
  u16 character, c
  str NumberReference indexReference
  u8 retval
  u64 i, len
  b1 found
  b1 tb10, tb11, tb12
Ens

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

### GetBilinarInterpolation

```
Bgs GetBilinarInterpolationS
  f64 q11, q12, q21, q22
  f64 x, y, x1, x2, y1, y2
  f64 h1, h2, v
  f64 tf640, tf641, tf642, tf643
Ens

Fnc GetBilinarInterpolation
  exp a f64: h1 = (x2 - x)/(x2 - x1)*q11 + (x - x1)/(x2 - x1)*q12
  exp a f64: h2 = (x2 - x)/(x2 - x1)*q21 + (x - x1)/(x2 - x1)*q22
  exp a f64: v = (y2 - y)/(y2 - y1)*h1 + (y - y1)/(y2 - y1)*h2
Ret
```
