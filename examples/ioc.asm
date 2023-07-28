%include "x86.mac"

struc NumberReference
  .numberValue: resq 1
endstruc

struc IndexOfCharacterS
  .string: resq 1
  .character: resd 1
  .c: resd 1
  .indexReference: resq 1
  .retval: resb 1
  .i: resq 1
  .found: resb 1
  .len: resq 1
  .tb10: resb 1
  .tb11: resb 1
  .tb12: resb 1
endstruc

global IndexOfCharacter
IndexOfCharacter:
  Mov.ib1 IndexOfCharacterS.found, 0
  Mov.iu64 IndexOfCharacterS.i, 0
  Loop L1
    Len IndexOfCharacterS.len, IndexOfCharacterS.string
    Lt.mmu64 IndexOfCharacterS.tb10, IndexOfCharacterS.i, IndexOfCharacterS.len
    Not.mb1 IndexOfCharacterS.tb11, IndexOfCharacterS.found
    And.mmb1 IndexOfCharacterS.tb12, IndexOfCharacterS.tb10, IndexOfCharacterS.tb11
  If.mb1 IndexOfCharacterS.tb12, L2
    Idr.mmu32a IndexOfCharacterS.c, IndexOfCharacterS.string, IndexOfCharacterS.i
    Eq.mmu32 IndexOfCharacterS.tb10, IndexOfCharacterS.c, IndexOfCharacterS.character
    If.mb1 IndexOfCharacterS.tb10, L3
      Mov.ib1 IndexOfCharacterS.found, 1
      Acw.mmu64 IndexOfCharacterS.indexReference, NumberReference.numberValue, IndexOfCharacterS.i
    Endb L3
    Inc.mu64 IndexOfCharacterS.i
  EndLoop L1, L2
  Mov.mu8 IndexOfCharacterS.retval, IndexOfCharacterS.found
ret

