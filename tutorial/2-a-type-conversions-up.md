# 2.a Type Conversions to Types with More Range

We can convert the data of one variable into one with a wider range. In this example, we have four variables. A signed 8-bit value is passed in as input, the output is a 64-bit binary floating point. In the code, the s8 is convered to an s16, then to an f32, and then finally to an f64.

```
Bgs testS
  s8 x
  f64 y
  s16 a
  f32 b
Ens

Fnc test
  s8tos16 a, x
  s16tof32 b, a
  f32tof64 y, b
Ret
```

The result will be a this list of x86 instructions (the intermediate stores are not shown.)

```
mov al, byte [rdi + x]
movsx ax, al
movsx eax, ax
cvtsi2ss xmm0, eax
cvtss2sd xmm0, xmm0
movsd [rdi + y], xmm0
```

The output is:

```
test(-21) = -21.000000
```	
