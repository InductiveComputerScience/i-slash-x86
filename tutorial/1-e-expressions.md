# 1.e. Expressions

We can divide one number with another using the / operator. This line will divide one number with another using signed, 64-bit integer arithmetic.

```
exp a s64: y = x - x*3 + 45/x
```

This is translated to instructions as follows:

```
Mul t0, x, 3 
Sub t0, x, t0 
Div t1, 45, x 
Add y, t0, t1 
```

The result will be a this list of x86 instructions.

```
mov rax, qword [rdi + x]
mov rdx, 3
imul rax, rdx
mov qword [rdi + t0], rax

mov rax, qword [rdi + x]
mov rdx, qword [rdi + t0]
sub rax, rdx
mov qword [rdi + t0], rax

mov rax, 45
cqo
mov rcx, qword [rdi + x]
idiv rcx
mov qword [rdi + t1], rax

mov rax, qword [rdi + t0]
mov rdx, qword [rdi + t1]
add rax, rdx
mov qword [rdi + y], rax
```

The output is:

```
test(21) = -40
```
