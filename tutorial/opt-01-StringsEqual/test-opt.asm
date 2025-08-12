%include "x86.mac"

struc testS
  .a: resq 1
  .al: resq 1
  .b: resq 1
  .bl: resq 1
  .equal: resb 1
endstruc

global test
test:
	mov r8, rdi

	;Eq.mmu64 testS.equal, testS.al, testS.bl
	mov rcx, qword [r8 + testS.al]
	cmp rcx, qword [r8 + testS.bl]
	sete byte [r8 + testS.equal]

	;If.mb1 testS.equal, L1
	jne L1

		;Cmps.mmu32a testS.equal, testS.a, testS.b, testS.al
		cld
		mov rsi, qword [r8 + testS.b]
		mov rdi, qword [r8 + testS.a]
		rep cmpsd
		setz byte [r8 + testS.equal]
  L1:
ret

struc GetTSS
  .t: resq 1
endstruc

global GetTS
GetTS:
  Rdtsc.mu64 GetTSS.t
ret

