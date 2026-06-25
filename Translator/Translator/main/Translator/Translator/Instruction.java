package Translator.Translator;

public class Instruction {
    public char [] name;
    public Param [] params;

    // Computed
    boolean hasTypePostfix;
    public char [] typePostfix;
    public char [] memoryPostfix;
    public char [] label1;
    public char [] label2;
    public double indentation;
}
