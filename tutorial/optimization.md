## Main steps

 * Step 1: Find a delimited problem. For example, a level 0 function from progsbase libraries.
 * Step 2: Find the best algorithm for the problem. (Somewhat open ended.)
 * Step 3: Code it in I/x86. Strategically utilize instructions. (A little open ended.)
 * Step 4: Generate the assembly.
 * Step 5: Use registers for intermediate values. This reduces the number of instructions.



## Step 5 Notes

 * Used for variables (input, output and working): rdi
 * Cannot use without saving and restoring: r12, r13, r14, r15, rbx, rsp, rbp, (rip)
 * Can utilize these registers: rax, rcx, rdx, r8, r9, r10, r11 (8 byte).
 * xmm0 - xmm31 (16 byte) or
 * ymm0 - ymm31 (32 byte) or
 * zmm0 - zmm31 (64 byte)
