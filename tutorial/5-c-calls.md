# 5.c Calls

This is how functions are called.


```
Acw fns, x, x
Call fn, fns
Acr y, fns, x
```

Which will translate to the following x86 assembly:

```
mov rax, qword [rdi + fns]
mov rdx, qword [rdi + x]
mov qword [rdx + x], rax

push rdi
mov rdi, [rdi + fns]
call fn
pop rdi

mov rax, [rdi + fns]
mov rax, [rax + x]
mov [rdi + y], rax
```

The output is:

```
test(...) = 1
```
