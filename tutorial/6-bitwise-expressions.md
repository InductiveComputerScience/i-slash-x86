# 6. Boolean Relational Expressions

We can get the compiler to automatically generate the list of instructions for a boolean-relational formula. The line starts with "exp" for expression, followed by "b" for boolean-relational followed by the type "b1", the result of the expression.


```
exp bw b32: y = d2 | d1 << 8 | d0 << 16
```

This will generate the following instructions:

```
ShiftLeft t0, d1, 8
Or t0, d2, t0
ShiftLeft t1, d0, 16
Or y, t0, t1
```

Which will translate to the following x86 assembly:

```
mov eax, [rdi + d1]
shl eax, 8
mov [rdi + t0], eax

mov eax, [rdi + d2]
mov edx, [rdi + t0]
or eax, edx
mov [rdi + t0], eax

mov eax, [rdi + %2]
shl eax, 16
mov [rdi + %1], eax

mov eax, [rdi + %2]
mov edx, [rdi + %3]
or eax, edx
mov [rdi + %1], eax
```

The output is:

```
test(...) = abcdef
```	
