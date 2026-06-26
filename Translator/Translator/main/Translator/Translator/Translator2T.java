package Translator.Translator;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.DataReference;
import DataStructures.Array.Structures.Structure;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.ArrayAddString;
import static DataStructures.Array.Arrays.Arrays.CreateArray;
import static DataStructures.Array.Structures.Structures.*;
import static Translator.Translator.Translator.GetEntryTypes;
import static Translator.Translator.Translator2.StringIsInArray;
import static arrays.arrays.arrays.StringsEqual;

public class Translator2T {
    public static boolean Translate(Ast ast, StringReference message) {
        double i;
        boolean success;

        success = true;

        System.out.println("%include \"x86.mac\"\n");

        for(i = 0d; i < ast.st.length && success; i = i + 1d){
            success = PrintStructure(ast.st[(int)i], message);
        }

        for(i = 0d; i < ast.fnc.length && success; i = i + 1d){
            success = PrintFunction(ast.fnc[(int)i], message);
        }

        return success;
    }

    public static boolean PrintStructure(Struct st, StringReference message) {
        double i;
        boolean success;

        success = true;

        System.out.println("struc " + new String(st.name));

        for(i = 0d; i < st.vars.length; i = i + 1d){
            PrintVariable(st.vars[(int)i], message);
        }

        System.out.println("endstruc");
        System.out.println();

        return success;
    }

    public static boolean PrintVariable(Var var, StringReference message) {
        boolean success;
        DataReference entryTypesRef;

        success = true;

        entryTypesRef = new DataReference();

        success = GetEntryTypes(entryTypesRef, message);

        if(success) {
            if(StructHasKey(entryTypesRef.data.structure, var.type)) {
                Structure data = GetStructFromStruct(entryTypesRef.data.structure, var.type);
                if(StructHasKey(entryTypesRef.data.structure, "nasmType".toCharArray())) {
                    char[] nasmType = GetStringFromStruct(data, "nasmType".toCharArray());

                    System.out.print("  .");

                    System.out.print(var.name);

                    System.out.print(": ");

                    System.out.print(nasmType);

                    System.out.print(" 1");

                    System.out.println();
                }else{
                    success = false;
                    message.string = "Could not find NASM type for type.".toCharArray();
                }
            }else{
                success = false;
                message.string = "Could not find NASM type for type.".toCharArray();
            }
        }

        return success;
    }

    public static boolean PrintFunction(Function fnc, StringReference message) {
        double i;
        char [] functionStructureName;
        boolean success;

        success = true;

        System.out.println("global " + new String(fnc.name));

        functionStructureName = (new String(fnc.name) + "S").toCharArray();

        System.out.println(new String(fnc.name) + ":");

        for(i = 0d; i < fnc.ins.length; i = i + 1d){
            PrintInstruction(fnc.ins[(int)i], functionStructureName);
        }

        System.out.println("ret");
        System.out.println("");

        return success;
    }

    public static void PrintInstruction(Instruction ins, char [] fncStName) {
        double i;

        for(i = 0; i < ins.indentation; i = i + 1d){
            System.out.print("  ");
        }

        System.out.print(new String(ins.name));

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
                System.out.print(new String(fncStName) + "." + new String(param.varname));
            }else if(StringsEqual(param.type, "literal".toCharArray())){
                System.out.print(param.literal);
            }

            if(i + 1d < ins.params.length) {
                System.out.print(", ");
            }
        }

        if(ins.label1 != null){
            if(ins.params.length > 0){
                System.out.print(", ");
            }

            System.out.print(new String(ins.label1));

            if(ins.label2 != null){
                System.out.print(", " + new String(ins.label2));
            }
        }

        System.out.print("\n");
    }

    public static boolean WriteCHeader(Ast ast, StringReference message) {
        double i;
        boolean success;

        success = true;

        System.out.println("#include <stdint.h>");
        System.out.println("#include <stdbool.h>");
        System.out.println("#include <uchar.h>");
        System.out.println("#include <immintrin.h>");
        System.out.println("");

        for(i = 0d; i < ast.st.length && success; i = i + 1d){
            success = PrintCStructure(ast.st[(int)i], message);
        }

        for(i = 0d; i < ast.fnc.length && success; i = i + 1d){
            success = PrintCFunctionPrototype(ast.fnc[(int)i], message);
        }

        return success;
    }

    private static boolean PrintCFunctionPrototype(Function fnc, StringReference message) {
        boolean success;
        char [] functionStructureName;

        success = true;

        functionStructureName = (new String(fnc.name) + "S").toCharArray();

        System.out.println("void " + new String(fnc.name) + "(struct " + new String(functionStructureName) + " *args);");
        System.out.println();

        return success;
    }

    private static boolean PrintCStructure(Struct st, StringReference message) {
        double i;
        boolean success;

        success = true;


        System.out.println("#pragma pack(push, 1)");
        System.out.println("struct " + new String(st.name) + "{");

        for(i = 0d; i < st.vars.length; i = i + 1d){
            PrintCVariable(st.vars[(int)i], message);
        }

        System.out.println("};");
        System.out.println("#pragma pack(pop)");
        System.out.println();

        return success;
    }

    private static void PrintCVariable(Var var, StringReference message) {
        DataReference entryTypesRef;
        boolean success;

        entryTypesRef = new DataReference();

        success = GetEntryTypes(entryTypesRef, message);

        if(success){
            Structure data = GetStructFromStruct(entryTypesRef.data.structure, var.type);
            char[] ctype = GetStringFromStruct(data, "ctype".toCharArray());

            System.out.print("  ");
            System.out.print(ctype);
            System.out.print(" ");
            System.out.print(var.name);
            System.out.println(";");
        }

    }
}
