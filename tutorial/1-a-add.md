# 1.a. Add

We can add two numbers using the + operator. This line will add two numbers using signed, 64-bit integer arithmetic.

```
exp a s64: a = x + 32
```

This is translated to instructions as follows:

```
Add a, x, 32
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
mov rdx, %3
add rax, rdx
mov qword [rdi + a], rax
```

First, we value of variable 2, `x`, is placed in `rax`, then the literal 32 is placed in `rdx`. The add instruction is used to add the value of `rdx` into `rax`. Finally, the value of `rax` is placed in the target variable 1, `a`.

The output is:

```
test(21) = 53
```
