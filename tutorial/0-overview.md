# 0. Setup
 - a. Setup on Linux
 - b. Setup on Mac
 - c. Setup on Windows

# 1. Arithmetic with s64
 - a. Add
 - b. Subtract
 - c. Multiply
 - d. Divide
 - e. Expressions

# 2. Number type conversions
 - a. To types of wider range
 - b. To types of lower range

# 3. Arrays
 - a. Array indexing
 - b. Structure access
 - c. Array indexing structure

# 4. Boolean relational expressions

# 5. Control flow
 - a. If-else
 - b. Loops
 - c. Function calls

# 6. Bitwise expressions

# 7. Various computational instructions
 - a. Conditional move (branchless programming)
 - b. Conditional set (branchless programming)
 - c. DivMod and MulDiv
 - d. Increase and decrease
 - e. Exchange (xchg) (concurrency)
 - f. Exchange and add  (concurrency)
 - g. Rotate
 - h. Compare and exchange (concurrency)
 - i. CRC32
 - j. Convert with saturate
 - k. Saturated addition and subtraction

# 8. Bit computations
 - a. Byte swap
 - b. Bit scan
 - c. Bit test
 - d. Population count
 - e. Leading zero count
 - f. Trailing zero count
 - g. Bitwise and-not
 - h. Bitfield extract
 - i. Extract lowest set bit
 - j. Generate a bitmask
 - k. Clear the lowest set bit
 - l. Zero out high-order bits
 - m. Parallel Bit Deposit
 - n. Parallel Bit Extract

# 9. String instructions
 - a. Block move (MOVSX)
 - b. Block compare (CMPSX)
 - c. Find index of value in block (SCASX)
 - ??(d. LODX and STOSX -- load into or store from ax)

# 10. Various infrastructural instructions
 - RDTSC -- Read time stamp counter
 - RDPMC -- Read performance counters
 - RDRAND -- Read random number
 - ??(CPUID)
 - Prefetch -- (PREFETCHT)

# 11. Various other instructions
 - Nop -- No operation

# 12. SIMD basics
 - a. Add, Subtract, Multiply, Divide, Absolute, Round
 - b. Comparisons
 - c. Conversions
 - d. Bitwise operations
 - e. Multiply-and-add
 - f. SIMD expressions
 - g. Array index SIMD (PINSR, PEXTR)
 - h. Min, max, average (PMIN, PMAX, PAVG)
 - i. Square root and reciprocals (SQRTPD)

# 13. SIMD advanced data movement
 - a. Interleave (PUNPCK)
 - b. Shuffle (PSHUF)
 - c. Create bit mask (PMOVMSKB)
 - d. Selective copy (PBLENDV)
 - e. Combine and extract (PALIGNR)
 
# 14. SIMD advanced calculations
 - a. Sum of absolute difference (PSADBW)
 - b. Pairwise horizontal add and subtract (PHADD, PHSUB)
 - c. Pairwiser multiply-and-add (PMADDUB)
 - d. Alternating subtract and add (ADDSUBPS)
 - e. Conditionally negate (PSIGN)
 - f. Dot-product (DPPS)
 - g. Multiply and store high part of result (PMULHW)
 - h. Approximate of reciprocals (RCPPS, RSQRT)

# Not included
 - x. Move selected values based on mask (MASKMOVQ)
 - x. Down-convert with saturation (packusdw, packssdw)

