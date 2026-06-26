package Translator.Translator;

import DataStructures.Array.Structures.Array;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.ArrayAddString;
import static DataStructures.Array.Arrays.Arrays.CreateArray;
import static Translator.Translator.Translator2.StringIsInArray;
import static arrays.arrays.arrays.StringsEqual;

public class Translator2T {
    public static boolean Translate(Ast ast, StringReference message) {
        double i;

        System.out.println("%include \"x86.mac\"\n");

        for(i = 0d; i < ast.st.length; i = i + 1d){
            PrintStructure(ast.st[(int)i], message);
        }

        for(i = 0d; i < ast.fnc.length; i = i + 1d){
            PrintFunction(ast.fnc[(int)i]);
        }

        return false;
    }

    public static void PrintStructure(Struct st, StringReference message) {
        double i;

        System.out.println("struc " + new String(st.name));

        for(i = 0d; i < st.vars.length; i = i + 1d){
            PrintVariable(st.vars[(int)i], message);
        }

        System.out.println("endstruc");
        System.out.println();
    }

    public static boolean PrintVariable(Var var, StringReference message) {
        Array resb, resw, resd, resq, reso, resy, resz;
        boolean success;

        success = true;

        resb = CreateArray();
        ArrayAddString(resb, "u8".toCharArray());
        ArrayAddString(resb, "s8".toCharArray());
        ArrayAddString(resb, "b1".toCharArray());
        ArrayAddString(resb, "b8".toCharArray());

        resw = CreateArray();
        ArrayAddString(resw, "u16".toCharArray());
        ArrayAddString(resw, "s16".toCharArray());
        ArrayAddString(resw, "b16".toCharArray());
        ArrayAddString(resw, "f16".toCharArray());

        resd = CreateArray();
        ArrayAddString(resd, "u32".toCharArray());
        ArrayAddString(resd, "s32".toCharArray());
        ArrayAddString(resd, "b32".toCharArray());
        ArrayAddString(resd, "f32".toCharArray());

        resq = CreateArray();
        ArrayAddString(resq, "u64".toCharArray());
        ArrayAddString(resq, "s64".toCharArray());
        ArrayAddString(resq, "b64".toCharArray());
        ArrayAddString(resq, "f64".toCharArray());

        ArrayAddString(resq, "f80".toCharArray());

        // MMX:
        ArrayAddString(resq, "u8x8".toCharArray());
        ArrayAddString(resq, "u16x4".toCharArray());
        ArrayAddString(resq, "u32x2".toCharArray());
        ArrayAddString(resq, "s8x8".toCharArray());
        ArrayAddString(resq, "s16x4".toCharArray());
        ArrayAddString(resq, "s32x2".toCharArray());
        ArrayAddString(resq, "b8x8".toCharArray());
        ArrayAddString(resq, "b16x4".toCharArray());
        ArrayAddString(resq, "b32x2".toCharArray());

        // Pointers
        ArrayAddString(resq, "u8a".toCharArray());
        ArrayAddString(resq, "u16a".toCharArray());
        ArrayAddString(resq, "u32a".toCharArray());
        ArrayAddString(resq, "u64a".toCharArray());
        ArrayAddString(resq, "u128a".toCharArray());
        ArrayAddString(resq, "s8a".toCharArray());
        ArrayAddString(resq, "s16a".toCharArray());
        ArrayAddString(resq, "s32a".toCharArray());
        ArrayAddString(resq, "s64a".toCharArray());
        ArrayAddString(resq, "s128a".toCharArray());
        ArrayAddString(resq, "b1a".toCharArray());
        ArrayAddString(resq, "b8a".toCharArray());
        ArrayAddString(resq, "b16a".toCharArray());
        ArrayAddString(resq, "b32a".toCharArray());
        ArrayAddString(resq, "b64a".toCharArray());
        ArrayAddString(resq, "b128a".toCharArray());
        ArrayAddString(resq, "b256a".toCharArray());
        ArrayAddString(resq, "b512a".toCharArray());
        ArrayAddString(resq, "f16a".toCharArray());
        ArrayAddString(resq, "f32a".toCharArray());
        ArrayAddString(resq, "f64a".toCharArray());
        ArrayAddString(resq, "f80a".toCharArray());
        ArrayAddString(resq, "u8x8a".toCharArray());
        ArrayAddString(resq, "u8x16a".toCharArray());
        ArrayAddString(resq, "u8x32a".toCharArray());
        ArrayAddString(resq, "u8x64a".toCharArray());
        ArrayAddString(resq, "u16x4a".toCharArray());
        ArrayAddString(resq, "u16x8a".toCharArray());
        ArrayAddString(resq, "u16x16a".toCharArray());
        ArrayAddString(resq, "u16x32a".toCharArray());
        ArrayAddString(resq, "u32x2a".toCharArray());
        ArrayAddString(resq, "u32x4a".toCharArray());
        ArrayAddString(resq, "u32x8a".toCharArray());
        ArrayAddString(resq, "u32x16a".toCharArray());
        ArrayAddString(resq, "u64x2a".toCharArray());
        ArrayAddString(resq, "u64x4a".toCharArray());
        ArrayAddString(resq, "u64x8a".toCharArray());
        ArrayAddString(resq, "s8x8a".toCharArray());
        ArrayAddString(resq, "s8x16a".toCharArray());
        ArrayAddString(resq, "s8x32a".toCharArray());
        ArrayAddString(resq, "s8x64a".toCharArray());
        ArrayAddString(resq, "s16x4a".toCharArray());
        ArrayAddString(resq, "s16x8a".toCharArray());
        ArrayAddString(resq, "s16x16a".toCharArray());
        ArrayAddString(resq, "s16x32a".toCharArray());
        ArrayAddString(resq, "s32x2a".toCharArray());
        ArrayAddString(resq, "s32x4a".toCharArray());
        ArrayAddString(resq, "s32x8a".toCharArray());
        ArrayAddString(resq, "s32x16a".toCharArray());
        ArrayAddString(resq, "s64x2a".toCharArray());
        ArrayAddString(resq, "s64x4a".toCharArray());
        ArrayAddString(resq, "s64x8a".toCharArray());
        ArrayAddString(resq, "b8x8a".toCharArray());
        ArrayAddString(resq, "b8x16a".toCharArray());
        ArrayAddString(resq, "b8x32a".toCharArray());
        ArrayAddString(resq, "b8x64a".toCharArray());
        ArrayAddString(resq, "b16x4a".toCharArray());
        ArrayAddString(resq, "b16x8a".toCharArray());
        ArrayAddString(resq, "b16x16a".toCharArray());
        ArrayAddString(resq, "b16x32a".toCharArray());
        ArrayAddString(resq, "b32x2a".toCharArray());
        ArrayAddString(resq, "b32x4a".toCharArray());
        ArrayAddString(resq, "b32x8a".toCharArray());
        ArrayAddString(resq, "b32x16a".toCharArray());
        ArrayAddString(resq, "b64x2a".toCharArray());
        ArrayAddString(resq, "b64x4a".toCharArray());
        ArrayAddString(resq, "b64x8a".toCharArray());
        ArrayAddString(resq, "f32x4a".toCharArray());
        ArrayAddString(resq, "f32x8a".toCharArray());
        ArrayAddString(resq, "f32x16a".toCharArray());
        ArrayAddString(resq, "f64x2a".toCharArray());
        ArrayAddString(resq, "f64x4a".toCharArray());
        ArrayAddString(resq, "f64x8a".toCharArray());

        reso = CreateArray();
        ArrayAddString(reso, "u128".toCharArray());
        ArrayAddString(reso, "s128".toCharArray());
        ArrayAddString(reso, "b128".toCharArray());

        // SSE
        ArrayAddString(reso, "u8x16".toCharArray());
        ArrayAddString(reso, "u16x8".toCharArray());
        ArrayAddString(reso, "u32x4".toCharArray());
        ArrayAddString(reso, "u64x2".toCharArray());
        ArrayAddString(reso, "s8x16".toCharArray());
        ArrayAddString(reso, "s16x8".toCharArray());
        ArrayAddString(reso, "s32x4".toCharArray());
        ArrayAddString(reso, "s64x2".toCharArray());
        ArrayAddString(reso, "b8x16".toCharArray());
        ArrayAddString(reso, "b16x8".toCharArray());
        ArrayAddString(reso, "b32x4".toCharArray());
        ArrayAddString(reso, "b64x2".toCharArray());
        ArrayAddString(reso, "f16x8".toCharArray());
        ArrayAddString(reso, "f32x4".toCharArray());
        ArrayAddString(reso, "f64x2".toCharArray());

        // AVX
        resy = CreateArray();
        ArrayAddString(resy, "b256".toCharArray());
        ArrayAddString(resy, "u8x32".toCharArray());
        ArrayAddString(resy, "u16x16".toCharArray());
        ArrayAddString(resy, "u32x8".toCharArray());
        ArrayAddString(resy, "u64x4".toCharArray());
        ArrayAddString(resy, "s8x32".toCharArray());
        ArrayAddString(resy, "s16x16".toCharArray());
        ArrayAddString(resy, "s32x8".toCharArray());
        ArrayAddString(resy, "s64x4".toCharArray());
        ArrayAddString(resy, "b8x32".toCharArray());
        ArrayAddString(resy, "b16x16".toCharArray());
        ArrayAddString(resy, "b32x8".toCharArray());
        ArrayAddString(resy, "b64x4".toCharArray());
        ArrayAddString(resy, "f16x16".toCharArray());
        ArrayAddString(resy, "f32x8".toCharArray());
        ArrayAddString(resy, "f64x4".toCharArray());


        // AVX-512
        resz = CreateArray();
        ArrayAddString(resz, "b512".toCharArray());
        ArrayAddString(resz, "u8x64".toCharArray());
        ArrayAddString(resz, "u16x32".toCharArray());
        ArrayAddString(resz, "u32x16".toCharArray());
        ArrayAddString(resz, "u64x8".toCharArray());
        ArrayAddString(resz, "s8x64".toCharArray());
        ArrayAddString(resz, "s16x32".toCharArray());
        ArrayAddString(resz, "s32x16".toCharArray());
        ArrayAddString(resz, "s64x8".toCharArray());
        ArrayAddString(resz, "b8x64".toCharArray());
        ArrayAddString(resz, "b16x32".toCharArray());
        ArrayAddString(resz, "b32x16".toCharArray());
        ArrayAddString(resz, "b64x8".toCharArray());
        ArrayAddString(resz, "f16x32".toCharArray());
        ArrayAddString(resz, "f32x16".toCharArray());
        ArrayAddString(resz, "f64x8".toCharArray());


        System.out.print("  .");

        System.out.print(var.name);

        System.out.print(": ");

        if(StringIsInArray(var.type, resb)){
            System.out.print("resb");
        }else if(StringIsInArray(var.type, resw)){
            System.out.print("resw");
        }else if(StringIsInArray(var.type, resd)){
            System.out.print("resd");
        }else if(StringIsInArray(var.type, resq)){
            System.out.print("resq");
        }else if(StringIsInArray(var.type, reso)){
            System.out.print("reso");
        }else if(StringIsInArray(var.type, resy)){
            System.out.print("reso");
        }else if(StringIsInArray(var.type, resz)){
            System.out.print("reso");
        }else{
            success = false;
            message.string = "Could not find NASM type for type.".toCharArray();
        }


        System.out.print(" 1");

        System.out.println();

        return success;
    }

    public static void PrintFunction(Function fnc) {
        double i;
        char [] functionStructureName;

        System.out.println("global " + new String(fnc.name));

        functionStructureName = (new String(fnc.name) + "S").toCharArray();

        System.out.println(new String(fnc.name) + ":");

        for(i = 0d; i < fnc.ins.length; i = i + 1d){
            PrintInstruction(fnc.ins[(int)i], functionStructureName);
        }

        System.out.println("ret");

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
        return false;
    }
}
