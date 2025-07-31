# 3.a Array indexing

We can declare an array of type f64 by appending a to the type, f64a. We can access an element of this array using the Idr instruction. The following will fetch the 51th element from array x and assign it to y.


```
Bgs testS
  f64a x
  f64 y
Ens

Fnc test
  Idr y, x, 50
Ret
```

The result will be a this list of x86 instructions. The f64 type is 8 bytes wide, therefore, the array index is multiplied by 8 to get the byte address of the element.

```
mov rax, [rdi + x]
mov rdx, 50
mov rax, [rax + rdx * 8]
mov [rdi + y], rax
```

The output is:

```
test(...) = 1.234500
```	
