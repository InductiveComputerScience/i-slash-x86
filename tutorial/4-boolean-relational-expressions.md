# 4 Boolean Relational Expressions

We can get the compiler to automatically generate the list of instructions for a boolean-relational formula. The line starts with "exp" for expression, followed by "b" for boolean-relational followed by the type "b1", the result of the expression.


```
exp b b1: y = a > 50 & a < 100
```

This will generate the following instructions:

```
MoreThan t0, a, 50 
LessThan t1, a, 100 
And y, t0, t1 
```

Which will translate to the following x86 assembly:

```
mov rax, [rdi + a]
mov rdx, 50
cmp rax, rdx
setg al
mov [rdi + t1], al

mov rax, [rdi + a]
mov rdx, 100
cmp rax, rdx
setl al
mov [rdi + t1], al

mov al, [rdi + t0]
mov ah, [rdi + t1]
and al, ah
and al, 1
mov [rdi + y], al
```

The output is:

```
test(...) = 1
```	
