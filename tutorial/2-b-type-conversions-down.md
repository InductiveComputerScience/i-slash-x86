# 2.a Type Conversions to Types with Less Range

We can convert the data of one variable into one with a lower range. In this example, we have four variables. A double precision floating point is passed in as a parameter. It is then converted down to an f32. During this process, the number will be rounded. If the number cannot fit an f32, pluss or minus infinity will be used instead. Next, the f32 will be converted to a signed 16-bit integer. If the number does not fit, an indefinate value will be set (0x8000?).	If it fits, the value will be truncated.


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
