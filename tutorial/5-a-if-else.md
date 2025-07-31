# 5.a If, else

Use if and else as follows.

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

  mov rax, 1
  mov qword [rdi + y], rax

jmp L2
L1:

  mov rax, 2
  mov qword [rdi + y], rax

L2:
```

The output is:

```
test(...) = 1
```
