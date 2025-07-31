# 2.a Type Conversions to Types with Less Range

We can convert the data of one variable into one with a lower range. In this example, we have four variables. A signed 8-bit value is passed in as input, the output is a 64-bit binary floating point. In the code, the s8 is convered to an s16, then to an f32, and then finally to an f64.


```
Bgs testS
  f64 x
  s8 y
  f32 a
  s16 b
Ens

Fnc test
  f64tof32 a, x
  f32tos16 b, a
  s16tos8 y, b
Ret
```

The result will be a this list of x86 instructions (the intermediate stores are not shown.)

```
movsd xmm0, [rdi + x]
cvtsd2ss xmm0, xmm0
cvttss2si eax, xmm0
mov [rdi + %1], al
```

The output is:

```
test(-21.000000) = -21
```	
