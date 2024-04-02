%include "x86.mac"

struc RdrandGuaranteedS
  .n: resq 1
  .c: resb 1
  .tb10: resb 1
endstruc

global RdrandGuaranteed
RdrandGuaranteed:
  Mov.ib1 RdrandGuaranteedS.c, 0
  Loop L1
    Eq.mib1 RdrandGuaranteedS.tb10, RdrandGuaranteedS.c, 0
  If.mb1 RdrandGuaranteedS.tb10, L2
    Rdrand.mu64 RdrandGuaranteedS.n, RdrandGuaranteedS.c
  EndLoop L1, L2
ret

struc ToUpperILS
  .str: resy 1
  .len: resq 1
  .len32: resq 1
  .len1: resq 1
  .i: resq 1
  .tb10: resb 1
  .a: resy 1
  .z: resy 1
  .x32: resy 1
  .x: resy 1
  .lcm: resy 1
  .tb8x320: resy 1
  .tb8x321: resy 1
  .tb8x322: resy 1
  .padding: resy 1
endstruc

%define x32 ymm6
%define z ymm7
%define a ymm8
%define x ymm9
%define i r8
%define b r9b
%define str r11

global ToUpperIL
ToUpperIL:
  Len ToUpperILS.len, ToUpperILS.str
  Div.rmiu64 r10, ToUpperILS.len, 32
  Mov.riu8x32 a, 'a'
  Mov.riu8x32 z, 'z'
  Mov.riu8x32 x32, 32
  Mov.riu64 i, 0
  Mov.rmu64 str, ToUpperILS.str
  Loop L3
    Lt.rrru64 b, i, r10
  If.rb1 b, L4
    Idr.rrru8x32a x, str, i
    Gte.rrru8x32 ymm4, x, a
    Lte.rrru8x32 ymm5, x, z
    And.rrrb8x32 ymm4, ymm4, ymm5
    And.rrru8x32 ymm10, ymm4, x32
    Sub.rrru8x32 x, x, ymm10
    Idw.rrru8x32a str, i, x
    Inc.ru64 i
  EndLoop L3, L4
ret

%undef a
%undef z
%undef x32
%undef i
%undef x
%undef b
%undef str





