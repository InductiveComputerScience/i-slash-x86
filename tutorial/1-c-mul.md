# 1.c. Multiply

We can mulitply one number with another using the * operator. This line will multiply one number with another using signed, 64-bit integer arithmetic.

```
exp a s64: y = x * 5
```

This is translated to instructions as follows:

```
Mul y, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
mov rdx, 5
imul rax, rdx
mov qword [rdi + y], rax
```

First, the value of variable `x`, is placed in register `rax`, then the literal 5 is placed in register `rdx`. The `imul` instruction is used to do the signed multiplication. Finally, the value of `rax` is placed in the variable `y`.

The output is:

```
test(21) = 105
```
