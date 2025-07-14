# 1.a. Subtract

We can subtract one number from another using the - operator. This line will subtract one number from another using signed, 64-bit integer arithmetic.

```
exp a s64: a = x - 5
```

This is translated to instructions as follows:

```
Sub a, x, 5
```

Then to x86 instructions in Intel syntax:

```
mov rax, qword [rdi + %2]
mov rdx, %3
sub rax, rdx
mov qword [rdi + %1], rax
```

First, we value of variable 2, `x`, is placed in `rax`, then the literal 5 is placed in `rdx`. The sub instruction is used to subtract the value of `rdx` from `rax`. Finally, the value of `rax` is placed in the target variable 1, `a`.

The output is:

```
test(21) = 16
```
