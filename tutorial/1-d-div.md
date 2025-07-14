# 1.a. Division

We can divide one number with another using the / operator. This line will divide one number with another using signed, 64-bit integer arithmetic.

```
exp a s64: a = x / 5
```

This is translated to instructions as follows:

```
Div a, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
cqo
mov rcx, 5
idiv rcx
mov qword [rdi + a], rax
```

First, we value of variable 2, `x`, is placed in `rax`. The value is sign-extended into the 128-bit combination `rdx:rax` using the cqo instruction. Then the literal 5 is placed in `rcx`. The idiv instruction is used to do signed division the value of `rdx:rax` with `rcx`. Finally, the value of `rax` is placed in the target variable 1, `a`.

The output is:

```
test(21) = 4
```
