#javac ../Translator/exec/Run.java -cp ../Translator/exec:../Translator/Translator/main:../Translator/imports -d ../Translator/out -target 8 -source 8
java -classpath ../Translator/out Run ioc.idx ioc.idx.1 ioc.asm ioc.h
gcc -c test.c -m64
nasm -f elf64 ioc.asm -I ..
gcc -no-pie test.o ioc.o -m64
./a.out
