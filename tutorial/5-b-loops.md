# 5.b Loops

Use Loops as follows.

```
Mov i, 0
Loop
If exp b: i < x
  exp a s64: y = y + i
  Inc i
EndLoop
```

Which will translate to the following x86 assembly:

```
mov rax, 0
mov [rdi + y], rax

mov rax, 0
mov [rdi + i], rax

L1:
  mov rax, [rdi + i]
  mov rdx, [rdi + x]
  cmp rax, rdx
  setl al
  mov [rdi + t0], al

  mov al, 0
  mov ah, [rdi + t0]
  and ah, 1
  cmp al, ah

je %2

  mov rax, [rdi + y]
  mov rdx, [rdi + i]
  add rax, rdx
  mov [rdi + y], rax

  inc [rdi + i]
jmp L1
L2:
```

The output is:

```
test(...) = 1
```  
