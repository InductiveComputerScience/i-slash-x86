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
	;Eq.mmu64 testS.equal, testS.al, testS.bl
	mov rax, qword [rdi + testS.al]
	mov rdx, qword [rdi + testS.bl]
	cmp rax, rdx
	sete al
	mov byte [rdi + testS.equal], al

	;If.mb1 testS.equal, L1
	mov al, 0
	mov ah, byte [rdi + testS.equal]
	and ah, 1
	cmp al, ah
	je L1

		;Cmps.mmu32a testS.equal, testS.a, testS.b, testS.al
		cld
		mov rsi, qword [rdi + testS.b]
		mov rcx, qword [rdi + testS.al]
		mov rbx, rdi
		mov rdi, qword [rdi + testS.a]
		rep cmpsd
		mov rdi, rbx
		setz byte [rdi + testS.equal]
  L1:
ret

struc GetTSS
  .t: resq 1
endstruc

global GetTS
GetTS:
  Rdtsc.mu64 GetTSS.t
ret

