java -classpath ../build Run toupper.idx toupper.idx.1 toupper.asm toupper.h
gcc -c toupper-test.c -m64
nasm -f elf64 toupper.asm -I ..
gcc -c toupper-c.c -m64 -O3 -S -mavx2
gcc -c toupper-c.s
gcc -no-pie toupper-test.o toupper-c.o toupper.o -m64
./a.out

