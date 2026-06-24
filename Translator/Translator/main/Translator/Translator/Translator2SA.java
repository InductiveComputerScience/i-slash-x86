package Translator.Translator;

import DataStructures.Array.Structures.Array;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.ArrayAddString;
import static DataStructures.Array.Arrays.Arrays.CreateArray;
import static Translator.Translator.Translator2.StringIsInArray;
import static arrays.arrays.arrays.StringsEqual;

public class Translator2SA {
    public static boolean StaticAnalysis(Ast ast, StringReference message) {
        boolean success;
        double i;

        success = true;

        // Checks
        if(success) {
            success = CheckUniqueStructureNames(ast.st, message);
        }

        if(success){
            success = CheckUniqueFunctionNames(ast.fnc, message);
        }

        if(success){
            for(i = 0; i < ast.fnc.length && success; i = i + 1d) {
                success = CheckExistenceOfFunctionStructure(ast.fnc[(int) i], ast.st, message);
            }
        }



        return success;
    }

    public static boolean CheckExistenceOfFunctionStructure(Function fnc, Struct [] sts, StringReference message) {
        double i;
        boolean found;
        char [] expectedName;

        found = false;
        expectedName = (new String(fnc.name) + "S").toCharArray();

        for(i = 0d; i < sts.length; i = i + 1d){
            Struct st = sts[(int)i];

            if(StringsEqual(st.name, expectedName)){
                found = true;
                fnc.functionStructure = st;
            }
        }

        if(found){

        }else{
            message.string = ("Function structure not found: " + new String(expectedName)).toCharArray();
        }

        return found;
    }

    public static boolean CheckUniqueFunctionNames(Function[] fncs, StringReference message) {
        double i;
        boolean valid;
        Array names;

        names = CreateArray();
        valid = true;

        for(i = 0d; i < fncs.length; i = i + 1d){
            Function fnc = fncs[(int) i];

            if(!StringIsInArray(fnc.name, names)){
                ArrayAddString(names, fnc.name);
            }else{
                valid = false;
                message.string = ("Two functions have the name name: " + new String(fnc.name)).toCharArray();
            }
        }

        return valid;
    }

    public static boolean CheckUniqueStructureNames(Struct[] sts, StringReference message) {
        double i;
        boolean valid;
        Array names;

        names = CreateArray();
        valid = true;

        for(i = 0d; i < sts.length; i = i + 1d){
            Struct struct = sts[(int) i];

            if(!StringIsInArray(struct.name, names)){
                ArrayAddString(names, struct.name);
            }else{
                valid = false;
                message.string = ("Two structures have the name name: " + new String(struct.name)).toCharArray();
            }
        }

        return valid;
    }

}
