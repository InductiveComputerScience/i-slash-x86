# 0. Setup
  a. Setup on Linux
  b. Setup on Mac
  c. Setup on Windows

# 1. Arithmetic with s64
  A. Add
  B. Subtract
  C. Multiply
  D. Divide
  E. Expressions

# 2. Number type conversions
  - u8 -> s/u16, s/u32, s/u64 (movzx)    -- u8tou16, u8tos16, u16tou32, u16tos32, u32tou64, u32tos64
  - s8 -> s16, s32, s64       (movsx)    -- s8tos16, s16tos32, s32tos64
  - f32 -> f64                (CVTSS2SD) -- f16tof32, f32tof64
  - u/s32 -> f64              (CVTSI2SD) -- u8tof16, s8tof16, u16tof32, s16tof32, u32tof64, s32tof64
  - f16 -> f32
  - f16 -> f64

# 3. Packing (check range before use)
  - u64 -> u32, s64 -> s32  (mov)
  - f64 -> f32              (CVTSD2SS)
  - f32 -> s64              (CVTTSS2SI)
  - f32 -> f16
  - f64 -> f16



