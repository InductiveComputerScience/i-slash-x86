# 5 If, else

We can get the compiler to automatically generate the list of instructions for a boolean-relational formula. The line starts with "exp" for expression, followed by "b" for boolean-relational followed by the type "b1", the result of the expression.


```
Bgs testS
  s64 a
  f64 y
  b1 tb10
  b1 x
Ens

Fnc test
	Lt x, a, 50

  If x
    Mov y, 1.0
  Else
    Mov y, 2.0
  Endb
Ret
```

Which will translate to the following x86 assembly:

```
mov rax, qword [rdi + a]
mov rdx, 50
cmp rax, rdx
setl al
mov byte [rdi + x], al

mov al, 0
mov ah, byte [rdi + x]
and ah, 1
cmp al, ah
je L1

mov rax, __?float64?__(1.0)
mov qword [rdi + y], rax

jmp L2
L1:

mov rax, __?float64?__(2.0)

mov qword [rdi + y], rax

L2:
```

The output is:

```
test(...) = 1
```	
