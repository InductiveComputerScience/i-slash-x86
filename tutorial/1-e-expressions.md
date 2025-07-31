# 1.e. Expressions

We can get the compiler to automatically create a list of operations from an expression. We can start the line with "exp", for expression, followed by "a" for arithmetic expression and then the type, s64.

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
mov rax, [rdi + x]
mov rdx, 3
imul rax, rdx
mov [rdi + t0], rax

mov rax, [rdi + x]
mov rdx, [rdi + t0]
sub rax, rdx
mov [rdi + t0], rax

mov rax, 45
cqo
mov rcx, [rdi + x]
idiv rcx
mov [rdi + t1], rax

mov rax, [rdi + t0]
mov rdx, [rdi + t1]
add rax, rdx
mov [rdi + y], rax
```

The output is:

```
test(21) = -40
```
