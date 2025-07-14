# 0.a. Setup on Linux

## I/86

A function has a start and an end. The start is the prefix `Fnc`, short for "FuNCtion" followed by the function name. The end is `Ret`, short for "return".

```
Fnc test
  ...
Ret
```

We must specify the variables to be used by the function. These include the input variables, the return variables and the local (working) variables used by the function.

This is done within a start and an end. The start is `Bgs`, short for "BeGin Structure" followed by the function name followed by "S". The end is "Ens", short for "ENd Structure".

```
Bgs testS
  ...
Ens
```

### Example

Let us look at an example function. This function assigns the input variable `x` to the return variable `retval`. Before the colon, we specify that what is after the colon is an expression using signed, 64-bit arithmetic. "exp" stands for "EXPression", `a` for "Arithmetic" and s64 for 64-bit signed integer.

```
Fnc test
  exp a s64: retval = x
Ret
```

Since we are using two variables, we need to declare them in the function structure. Both variables are going to be signed, 64-bit integers.

```
Bgs testS
  s64 x
  s64 retval
Ens
```

Here is the complete example:


```
Bgs testS
  s64 x
  s64 retval
Ens

Fnc test
  exp a s64: retval = x
Ret
```

### Building the code

We can build the I/x86 code using

```
java -jar Translator.jar test.idx test.idx.1 test.asm test.h
```

This will place the NASM-formatted assembly code in `test.asm` and the C-header in `test.h`.

## C part
To run the code, we need to make some C-code.

First, we need to include the C header.

```
#include "test.h"
```

Then we need to create a structure for the function to be called.

```
struct testS ts;
```

We fill in the input value:

```
ts.x = 21;
```

And run the function:

```
test(&ts);
```

A pointer to the structure is given as the only parameter to the function, providing it with input, output and local variables.

Finally, we can output the output variable:

```
printf("f(%ld) = %ld\n", ts.x, ts.retval);
```

Here is the complete code:

```
#include <stdio.h>
#include "test.h"

int main(){
  struct testS ts;
  ts.x = 21;
  test(&ts);
  printf("test(%ld) = %ld\n", ts.x, ts.retval);
  return 0;
}
```

### Building the C code

We can build the C code using the following command.

```
gcc -c main.c
```

## Assembling and linking
Finally, we can assemble and link the code

```
nasm -f elf64 test.asm -I ../.. -l test.lst
gcc main.o test.o
```

Run it using:

```
./a.out
```

We can see how the I/x86 code was translated by looking at the bottom of `test.lst`:

The assignment was turned into the following instruction:

```
Mov.ms64 testS.retval, testS.x
```

Which is finally turned into x86 assembly in Intel syntax:

```

mov rax, qword [rdi + %2]
mov qword [rdi + %1], rax

```

First of all, we see rdi, which contains a pointer to the function structure. We add the offset of variable 2, to get the value of the varible `x`, we place it in `rax`. Then we copy this value into the variable at offset `retval`.






























