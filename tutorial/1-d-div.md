# 1.d. Division

We can divide one number with another using the / operator. This line will divide one number with another using signed, 64-bit integer arithmetic.

```
exp a s64: y = x / 5
```

This is translated to instructions as follows:

```
Div y, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
cqo
mov rcx, 5
idiv rcx
mov qword [rdi + y], rax
```

First, the value of variable `x` is placed in register `rax`. The value is sign-extended into the 128-bit combination of two registers `rdx` and `rax` using the `cqo` instruction. Then the literal 5 is placed in register `rcx`. The `idiv` instruction is used to do the signed division. Finally, the value of register `rax` is placed in the variable `y`.

The output is:

```
test(21) = 4
```
