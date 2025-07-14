# 1.c. Multiply

We can mulitply one number with another using the * operator. This line will multiply one number with another using signed, 64-bit integer arithmetic.

```
exp a s64: a = x * 5
```

This is translated to instructions as follows:

```
Mul a, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + x]
mov rdx, 5
imul rax, rdx
mov qword [rdi + a], rax
```

First, we value of variable 2, `x`, is placed in `rax`, then the literal 5 is placed in `rdx`. The imul instruction is used to do signed multiplication the value of `rdx` and `rax`. Finally, the value of `rax` is placed in the target variable 1, `a`.

The output is:

```
test(21) = 105
```
