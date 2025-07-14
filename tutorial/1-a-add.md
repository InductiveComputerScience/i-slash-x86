# 1.a. Add

We can add two numbers using the + operator. This line will add two numbers using signed, 64-bit integer arithmetic.

```
exp a s64: y = x + 32
```

This is translated to instructions as follows:

```
Add y, x, 32
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
mov rdx, 32
add rax, rdx
mov qword [rdi + y], rax
```

First, we place the value of variable `x` in register `rax`, then the literal 32 is placed in register `rdx`. The `add` instruction is used for this. Finally, the value of `rax` is placed in the variable `y`.

The output is:

```
test(21) = 53
```
