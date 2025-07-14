# 1.b. Subtract

We can subtract one number from another using the - operator. This line will subtract one number from another using signed, 64-bit integer arithmetic.

```
exp a s64: y = x - 5
```

This is translated to instructions as follows:

```
Sub y, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
mov rdx, 5
sub rax, rdx
mov qword [rdi + y], rax
```

First, the value of variable `x` is placed in register `rax`, then the literal 5 is placed in register `rdx`. The `sub` instruction is used for this. Finally, the value of register `rax` is placed in the variable `y`.

The output is:

```
test(21) = 16
```
