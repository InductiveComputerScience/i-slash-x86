#java -jar ../../Translator.jar test.idx test.idx.1 test.asm test.h
gcc -c main.c
gcc -c org.c
nasm -f elf64 test.asm -I ../.. -l test.lst
gcc main.o test.o org.o
./a.out

