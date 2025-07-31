# 3.b Structure access

We can declare an instance of a structure by using the str keyword. In this example, we create an instance of the structure C called c. This structure can be accessed using the Acr instruction.


```
Bgs C
  f64 x
Ens

Bgs testS
  str C c
  f64 y
Ens

Fnc test
  Acr y, c, x
Ret
```

The result will be a this list of x86 instructions. It first takes the value of c and then adds the offset of entry x. The value at this address is fetched and assigned to y.

```
mov rax, [rdi + c]
mov rax, [rax + x]
mov [rdi + y], rax
```

The output is:

```
test(...) = 1.234567
```
