0.
  a. Setup on Linux
  b. Setup on Mac
  c. Setup on Windows

1. Arithmetic with s64
  A. Add
  B. Subtract
  C. Multiply
  D. Divide
  E. Expressions

2. Number type conversions
  - u8 -> s/u16, s/u32, s/u64 (movzx)
  - s8 -> s16, s32, s64       (movsx)
  - f32 -> f64                (CVTSS2SD)
  - s32 -> f64                (CVTSI2SD)

3. Packing (check range before use)
  - u64 -> u32, s64 -> s32  (mov) 
  - f64 -> f32              (CVTSD2SS)
  - f32 -> s64              (CVTTSS2SI)



