package Translator.Translator;

import references.references.StringReference;

import static arrays.arrays.arrays.StringsEqual;

public class Translator2T {
    public static boolean Translate(Ast ast, StringReference message) {
        double i;

        for(i = 0d; i < ast.fnc.length; i = i + 1d){
            PrintFunction(ast.fnc[(int)i]);
        }

        return false;
    }

    private static void PrintFunction(Function fnc) {
        double i;

        System.out.println("Fnc " + new String(fnc.name));

        for(i = 0d; i < fnc.ins.length; i = i + 1d){
            PrintInstruction(fnc.ins[(int)i]);
        }

        System.out.println("Ret");

    }

    private static void PrintInstruction(Instruction ins) {
        double i;

        System.out.print("  " + new String(ins.name));

        if(ins.hasTypePostfix || ins.params.length > 0) {
            System.out.print(".");
        }

        if(ins.memoryPostfix != null){
            System.out.print(new String(ins.memoryPostfix));
        }else{
            System.out.print("_");
        }

        if(ins.hasTypePostfix) {
            if (ins.typePostfix != null) {
                System.out.print(new String(ins.typePostfix));
            } else {
                System.out.print("_");
            }
        }

        System.out.print(" ");

        for(i = 0d; i < ins.params.length; i = i + 1d){
            Param param = ins.params[(int) i];
            if(StringsEqual(param.type, "var".toCharArray())){
                System.out.print(param.varname);
            }else if(StringsEqual(param.type, "literal".toCharArray())){
                System.out.print(param.literal);
            }

            if(i + 1d < ins.params.length) {
                System.out.print(", ");
            }
        }

        System.out.print("\n");
    }
}
