# 3.c Array indexing structure

We can declare an instance of a structure by using the str keyword. In this example, we create an instance of the structure C called c. This structure can be accessed using the Acr instruction.


```
Bgs C
  f64 x
Ens

Bgs testS
  sta C x
  str C a
  f64 y
Ens

Fnc test
  Idr a, x, 50 
  Acr y, a, x
Ret
```

The result will be a this list of x86 instructions. It first indexes the array and the does the array access.

```
mov rax, [rdi + x]
mov rdx, 50
mov rax, [rax + rdx * 8]
mov [rdi + a], rax

mov rax, [rdi + a]
mov rax, [rax + x]
mov [rdi + y], rax
```

The output is:

```
test(...) = 1.234567
```
