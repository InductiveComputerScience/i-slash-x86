mkdir -p build
cd Translator
javac exec/Run.java -cp exec:Translator/main:imports -d ../build
cd ../build
jar cfe ../Translator.jar Run *
cd ..
