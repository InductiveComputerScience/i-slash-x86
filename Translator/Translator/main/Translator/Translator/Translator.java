package Translator.Translator;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.DataReference;
import DataStructures.Array.Structures.Structure;
import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import references.references.*;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.*;
import static FormulaTranslation.ArithmeticFormulaFunctionWriter.ArithmeticFormulaFunctionWriter.ArithmeticFormulaToTFormFunctions;
import static FormulaTranslation.BitwiseFormulaFunctionWriter.BitwiseFormulaFunctionWriter.BitwiseFormulaToTFormFunctions;
import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.BooleanFormulaToTFormFunctions;
import static JSON.Parser.Parser.ReadJSON;
import static arrays.arrays.arrays.CopyString;
import static arrays.arrays.arrays.StringsEqual;
import static charCharacters.Characters.Characters.charIsNumber;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static nnumbers.NumberToString.NumberToString.nCreateStringDecimalFromNumber;
import static references.references.references.CreateNumberReference;
import static strings.strings.strings.*;

public class Translator {

    public static boolean FindFunctionsAndStructures(char [] code, Array functions, Array structures, StringReference message){
        boolean success;
        StringReference [] lines, parts;
        double i, j;
        char [] line, name;
        Structure structure, function, entry;
        double state;
        Array entries;
        boolean done, found;
        Structure entryTypes;
        DataReference dataRef;

        entries = new Array();

        lines = SplitByCharacter(code, '\n');

        // Entry types for x86-64
        dataRef = new DataReference();
        entryTypes = new Structure();
        success = GetEntryTypes(dataRef, message);
        if(success){
            entryTypes = dataRef.data.structure;
        }

        // Pass 1
        for(i = 0d; i < lines.length && success; i = i + 1d){
            line = lines[(int)i].string;
            line = MergeWhitespace(line);
            line = Trim(line);

            if(line.length != 0 && !StartsWith(line, ";".toCharArray())) {
                parts = SplitByCharacter(line, ' ');
                //System.out.println(new String(line));

                /*for(j = 0d; j < parts.length && success; j = j + 1d){
                    part = parts[(int)j].string;
                    System.out.print(new String(part) + " ");
                }
                System.out.println();*/

                if(StringsEqual(parts[0].string, "Bgs".toCharArray())){
                    if(parts.length == 2){
                        structure = CreateStructure();
                        entries = CreateArray();
                        AddStringToStruct(structure, "name".toCharArray(), parts[1].string);
                        AddArrayToStruct(structure, "entries".toCharArray(), entries);
                        AddStructToArray(structures, structure);
                    }else{
                        success = false;
                        message.string = "Bgs must be followed by an identifier.".toCharArray();
                    }
                }

                if(StringsEqual(parts[0].string, "Fnc".toCharArray())){
                    if(parts.length == 2){
                        function = CreateStructure();
                        AddStringToStruct(function, "name".toCharArray(), parts[1].string);

                        AddStructToArray(functions, function);
                    }else{
                        success = false;
                        message.string = "Fnc must be followed by an identifier.".toCharArray();
                    }
                }
            }
        }

        // Pass 2
        state = 0;
        for(i = 0d; i < lines.length && success; i = i + 1d){
            line = lines[(int)i].string;
            line = MergeWhitespace(line);
            line = Trim(line);

            if(line.length != 0 && !StartsWith(line, ";".toCharArray())) {
                parts = SplitByCharacter(line, ' ');

                if(state == 0) {
                    if (StringsEqual(parts[0].string, "Bgs".toCharArray())) {
                        state = 1;
                        done = false;
                        for(j = 0d; j < ArrayLength(structures) && !done; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            entries = GetArrayFromStruct(structure, "entries".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }
                    }
                    if(StringsEqual(parts[0].string, "Fnc".toCharArray())){
                        state = 3;
                        done = false;
                        for(j = 0d; j < ArrayLength(functions) && !done; j = j + 1d){
                            function = ArrayIndex(functions, j).structure;
                            name = GetStringFromStruct(function, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }
                    }
                }else if(state == 1){
                    if(StringsEqual(parts[0].string, "Ens".toCharArray())){
                        state = 0;
                    }else if(StructHasKey(entryTypes, parts[0].string)){
                        entry = CreateStructure();
                        AddStringToStruct(entry, "type".toCharArray(), parts[0].string);
                        AddStringToStruct(entry, "name".toCharArray(), parts[1].string);
                        AddStructToArray(entries, entry);
                    }else if(StringsEqual(parts[0].string, "str".toCharArray())){
                        entry = CreateStructure();
                        AddStringToStruct(entry, "type".toCharArray(), parts[0].string);
                        AddStringToStruct(entry, "typeName".toCharArray(), parts[1].string);
                        AddStringToStruct(entry, "name".toCharArray(), parts[2].string);
                        AddStructToArray(entries, entry);

                        found = false;
                        for(j = 0d; j < ArrayLength(structures) && !found; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                found = true;
                            }
                        }

                        if(found){
                        }else{
                            message.string = "Structure in str declaration not found.".toCharArray();
                            success = false;
                        }
                    }else{
                        message.string = "Token not allowed in structure: ".toCharArray();
                        message.string = AppendString(message.string, parts[0].string);
                        success = false;
                    }
                }else if(state == 3){
                    if(StringsEqual(parts[0].string, "Ret".toCharArray())){
                        state = 0;
                    }
                }
            }
        }

        if(success) {
            if (state == 0) {

            } else {
                message.string = "Illegal state at the end of processing.".toCharArray();
                success = false;
            }
        }

        return success;
    }

    public static char[] MergeWhitespace(char[] line) {
        char [] newline;

        newline = CopyString(line);
        ReplaceCharacter(newline, '\t', ' ');
        ReplaceCharacter(newline, '\r', ' ');

        for(; ContainsString(newline, "  ".toCharArray());){
            newline = ReplaceString(newline, "  ".toCharArray(), " ".toCharArray());
        }

        return newline;
    }

    public static boolean GetEntryTypes(DataReference entryTypesRef, StringReference message) {
        char [] json;
        boolean success;

        json = ("{\n" +
                "                \"u8\": {\"nasmType\": \"resb\", \"ctype\": \"uint8_t\"},\n" +
                "                \"u16\": {\"nasmType\": \"resw\", \"ctype\": \"uint16_t\"},\n" +
                "                \"u32\": {\"nasmType\": \"resd\", \"ctype\": \"uint32_t\"},\n" +
                "                \"u64\": {\"nasmType\": \"resq\", \"ctype\": \"uint64_t\"},\n" +
                "                \"s8\": {\"nasmType\": \"resb\", \"ctype\": \"int8_t\"},\n" +
                "                \"s16\": {\"nasmType\": \"resw\", \"ctype\": \"int16_t\"},\n" +
                "                \"s32\": {\"nasmType\": \"resd\", \"ctype\": \"int32_t\"},\n" +
                "                \"s64\": {\"nasmType\": \"resq\", \"ctype\": \"int64_t\"},\n" +
                "                \"b1\": {\"nasmType\": \"resb\", \"ctype\": \"uint8_t\"},\n" +
                "                \"b8\": {\"nasmType\": \"resb\", \"ctype\": \"uint8_t\"},\n" +
                "                \"b16\": {\"nasmType\": \"resw\", \"ctype\": \"uint16_t\"},\n" +
                "                \"b32\": {\"nasmType\": \"resd\", \"ctype\": \"uint32_t\"},\n" +
                "                \"b64\": {\"nasmType\": \"resq\", \"ctype\": \"uint64_t\"},\n" +
                "                \"f32\": {\"nasmType\": \"resd\", \"ctype\": \"float\"},\n" +
                "                \"f64\": {\"nasmType\": \"resq\", \"ctype\": \"double\"},\n" +
                "                \"u8a\": {\"nasmType\": \"resq\", \"ctype\": \"uint8_t *\"},\n" +
                "                \"u16a\": {\"nasmType\": \"resq\", \"ctype\": \"uint16_t *\"},\n" +
                "                \"u32a\": {\"nasmType\": \"resq\", \"ctype\": \"uint32_t *\"},\n" +
                "                \"u64a\": {\"nasmType\": \"resq\", \"ctype\": \"uint64_t *\"},\n" +
                "                \"s8a\": {\"nasmType\": \"resq\", \"ctype\": \"int8_t *\"},\n" +
                "                \"s16a\": {\"nasmType\": \"resq\", \"ctype\": \"int16_t *\"},\n" +
                "                \"s32a\": {\"nasmType\": \"resq\", \"ctype\": \"int32_t *\"},\n" +
                "                \"s64a\": {\"nasmType\": \"resq\", \"ctype\": \"int64_t *\"},\n" +
                "                \"b1a\": {\"nasmType\": \"resq\", \"ctype\": \"uint8_t *\"},\n" +
                "                \"b8a\": {\"nasmType\": \"resq\", \"ctype\": \"uint8_t *\"},\n" +
                "                \"b16a\": {\"nasmType\": \"resq\", \"ctype\": \"uint16_t *\"},\n" +
                "                \"b32a\": {\"nasmType\": \"resq\", \"ctype\": \"uint32_t *\"},\n" +
                "                \"b64a\": {\"nasmType\": \"resq\", \"ctype\": \"uint64_t *\"},\n" +
                "                \"f32a\": {\"nasmType\": \"resq\", \"ctype\": \"float *\"},\n" +
                "                \"f64a\": {\"nasmType\": \"resq\", \"ctype\": \"double *\"},\n" +
                "                \"u8x8\": {\"nasmType\": \"resq\", \"ctype\": \"__m64\"},\n" +
                "                \"u8x16\": {\"nasmType\": \"reso\", \"ctype\": \"__m128\"},\n" +
                "                \"u8x32\": {\"nasmType\": \"resy\", \"ctype\": \"__m256\"},\n" +
                "                \"u8x64\": {\"nasmType\": \"resz\", \"ctype\": \"__m512\"},\n" +
                "                \"u8x8a\": {\"nasmType\": \"resq\", \"ctype\": \"__m64 *\"},\n" +
                "                \"u8x16a\": {\"nasmType\": \"reso\", \"ctype\": \"__m128 *\"},\n" +
                "                \"u8x32a\": {\"nasmType\": \"resy\", \"ctype\": \"__m256 *\"},\n" +
                "                \"u8x64a\": {\"nasmType\": \"resz\", \"ctype\": \"__m512 *\"},\n" +
                "                \"s8x8\": {\"nasmType\": \"resq\", \"ctype\": \"__m64\"},\n" +
                "                \"s8x16\": {\"nasmType\": \"reso\", \"ctype\": \"__m128\"},\n" +
                "                \"s8x32\": {\"nasmType\": \"resy\", \"ctype\": \"__m256\"},\n" +
                "                \"s8x64\": {\"nasmType\": \"resz\", \"ctype\": \"__m512\"},\n" +
                "                \"s8x8a\": {\"nasmType\": \"resq\", \"ctype\": \"__m64 *\"},\n" +
                "                \"s8x16a\": {\"nasmType\": \"reso\", \"ctype\": \"__m128 *\"},\n" +
                "                \"s8x32a\": {\"nasmType\": \"resy\", \"ctype\": \"__m256 *\"},\n" +
                "                \"s8x64a\": {\"nasmType\": \"resz\", \"ctype\": \"__m512 *\"},\n" +
                "                \"b8x8\": {\"nasmType\": \"resq\", \"ctype\": \"__m64\"},\n" +
                "                \"b8x16\": {\"nasmType\": \"reso\", \"ctype\": \"__m128\"},\n" +
                "                \"b8x32\": {\"nasmType\": \"resy\", \"ctype\": \"__m256\"},\n" +
                "                \"b8x64\": {\"nasmType\": \"resz\", \"ctype\": \"__m512\"},\n" +
                "                \"b8x8a\": {\"nasmType\": \"resq\", \"ctype\": \"__m64 *\"},\n" +
                "                \"b8x16a\": {\"nasmType\": \"reso\", \"ctype\": \"__m128 *\"},\n" +
                "                \"b8x32a\": {\"nasmType\": \"resy\", \"ctype\": \"__m256 *\"},\n" +
                "                \"b8x64a\": {\"nasmType\": \"resz\", \"ctype\": \"__m512 *\"},\n" +
                "                \"f32x4\": {\"nasmType\": \"reso\", \"ctype\": \"__m128\"},\n" +
                "                \"f32x8\": {\"nasmType\": \"resy\", \"ctype\": \"__m256\"},\n" +
                "                \"f32x16\": {\"nasmType\": \"resz\", \"ctype\": \"__m512\"},\n" +
                "                \"f32x4a\": {\"nasmType\": \"reso\", \"ctype\": \"__m128 *\"},\n" +
                "                \"f32x8a\": {\"nasmType\": \"resy\", \"ctype\": \"__m256 *\"},\n" +
                "                \"f32x16a\": {\"nasmType\": \"resz\", \"ctype\": \"__m512 *\"},\n" +
                "                \"f64x2\": {\"nasmType\": \"reso\", \"ctype\": \"__m128\"},\n" +
                "                \"f64x4\": {\"nasmType\": \"resy\", \"ctype\": \"__m256\"},\n" +
                "                \"f64x8\": {\"nasmType\": \"resz\", \"ctype\": \"__m512\"},\n" +
                "                \"f64x2a\": {\"nasmType\": \"reso\", \"ctype\": \"__m128 *\"},\n" +
                "                \"f64x4a\": {\"nasmType\": \"resy\", \"ctype\": \"__m256 *\"},\n" +
                "                \"f64x8a\": {\"nasmType\": \"resz\", \"ctype\": \"__m512 *\"}\n" +
                "            }").toCharArray();

        success = ReadJSON(json, entryTypesRef, message);

        return success;
    }

    public static boolean GetInstructionTypes(DataReference dataRef, StringReference message) {
        char [] json;
        boolean success;

        json = ("{\n" +
                "              \"Mov\": {\"args\": 1, \"typeDecider\": 1},\n" +
                "\n" +
                "              \"Add\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"Sub\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"Mul\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"Div\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"Mod\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"DivMod\": {\"args\": 3, \"typeDecider\": 1},\n" +
                "              \"MulDiv\": {\"args\": 3, \"typeDecider\": 1},\n" +
                "              \"Inc\": {\"args\": 0, \"typeDecider\": 1},\n" +
                "              \n" +
                "              \"Not\": {\"args\": 1, \"typeDecider\": 1},\n" +
                "              \"Shl\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"ShiftLeft\": {\"args\": 2, \"typeDecider\": 1, \"alias\": \"Shl\"},\n" +
                "              \"And\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \"Or\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \n" +
                "              \"Lt\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"Lte\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"LessThanOrEqual\": {\"args\": 2, \"typeDecider\": 0, \"alias\": \"Lte\"},\n" +
                "              \"LessThan\": {\"args\": 2, \"typeDecider\": 0, \"alias\": \"Lt\"},\n" +
                "              \"Lt.iiu64\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"Gt\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"Gte\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"MoreThanOrEqual\": {\"args\": 2, \"typeDecider\": 0, \"alias\": \"Gte\"},\n" +
                "              \"Eq\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "              \"Equal\": {\"args\": 2, \"typeDecider\": 0, \"alias\": \"Eq\"},\n" +
                "              \"Neq\": {\"args\": 2, \"typeDecider\": 0},\n" +
                "\n" +
                "              \"Idr\": {\"args\": 2, \"typeDecider\": 2},\n" +
                "              \"Idw\": {\"args\": 2, \"typeDecider\": 1},\n" +
                "              \n" +
                "              \"Rdrand\": {\"args\": 1, \"typeDecider\": 1},\n" +
                "              \n" +
                "              \"Del\": {\"args\": 0, \"typeDecider\": 1},\n" +
                "              \"s8tos16\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true},\n" +
                "              \"s16tof32\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true},\n" +
                "              \"f32tof64\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true},\n" +
                "              \"s16tos8\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true},\n" +
                "              \"f32tos16\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true},\n" +
                "              \"f64tof32\": {\"args\": 1, \"typeDecider\": 0, \"noTypePostfix\": true}\n" +
                "            }").toCharArray();

        success = ReadJSON(json, dataRef, message);

        return success;
    }

    public static boolean Translate(char[] code, Array functions, Array structures, StringReference message, StringReference output) {
        boolean success;
        StringReference [] lines, parts;
        double i, j, args, typeDecider;
        char [] line, name, fname, labelString, functionStructName, nasmType, iname;
        Structure structure, function, entryTypes, entryType, instructions, ins;
        double state, tabs, label;
        boolean done;
        LinkedListCharacters cc;
        boolean loopIf;
        NumberReference labels;
        Array ifStack, loopStack;
        DataReference dataRef;

        cc = CreateLinkedListCharacter();

        success = true;
        structure = new Structure();
        fname = "".toCharArray();
        tabs = 1;
        loopIf = false;
        labels = CreateNumberReference(0d);
        ifStack = CreateArray();
        loopStack = CreateArray();

        // Entry types for x86-64
        dataRef = new DataReference();
        entryTypes = new Structure();
        success = GetEntryTypes(dataRef, message);
        if(success){
            entryTypes = dataRef.data.structure;
        }

        // Entry types for x86-64
        dataRef = new DataReference();
        instructions = new Structure();
        success = GetInstructionTypes(dataRef, message);
        if(success){
            instructions = dataRef.data.structure;
        }

        // Start
        lines = SplitByCharacter(code, '\n');

        LinkedListCharactersAddString(cc, "%include \"x86.mac\"\n\n".toCharArray());

        state = 0;
        for(i = 0d; i < lines.length && success; i = i + 1d){
            line = lines[(int)i].string;
            line = MergeWhitespace(line);
            line = Trim(line);

            if(line.length != 0 && !StartsWith(line, ";".toCharArray())) {
                parts = SplitByCharacter(line, ' ');

                if(state == 0) {
                    if (StringsEqual(parts[0].string, "Bgs".toCharArray())) {
                        state = 1;
                        done = false;
                        for(j = 0d; j < ArrayLength(structures) && !done; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }
                    }
                    if(StringsEqual(parts[0].string, "Fnc".toCharArray())){
                        state = 3;
                        done = false;
                        for(j = 0d; j < ArrayLength(functions) && !done; j = j + 1d){
                            function = ArrayIndex(functions, j).structure;
                            name = GetStringFromStruct(function, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                                fname = name;
                            }
                        }

                        done = false;
                        for(j = 0d; j < ArrayLength(structures) && !done; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            functionStructName = ConcatenateCharacter(fname, 'S');
                            if(StringsEqual(name, functionStructName)){
                                done = true;
                            }
                        }
                    }
                }

                if(state == 1){
                    if(StringsEqual(parts[0].string, "Bgs".toCharArray())){
                        LinkedListCharactersAddString(cc, "struc ".toCharArray());
                        LinkedListCharactersAddString(cc, parts[1].string);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Ens".toCharArray())){
                        state = 0;

                        LinkedListCharactersAddString(cc, "endstruc".toCharArray());
                        LinkedListCharactersAddString(cc, "\n\n".toCharArray());
                    }else if(StructHasKey(entryTypes, parts[0].string)){
                        LinkedListCharactersAddString(cc, "  .".toCharArray());
                        LinkedListCharactersAddString(cc, parts[1].string);
                        entryType = GetStructFromStruct(entryTypes, parts[0].string);
                        nasmType = GetStringFromStruct(entryType, "nasmType".toCharArray());
                        LinkedListCharactersAddString(cc, ": ".toCharArray());
                        LinkedListCharactersAddString(cc, nasmType);
                        LinkedListCharactersAddString(cc, " 1".toCharArray());
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "str".toCharArray())){
                        LinkedListCharactersAddString(cc, "  .".toCharArray());
                        LinkedListCharactersAddString(cc, parts[2].string);
                        LinkedListCharactersAddString(cc, ": resq 1".toCharArray());
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else{
                        message.string = "Token not allowed in structure: ".toCharArray();
                        message.string = AppendString(message.string, parts[0].string);
                        success = false;
                    }
                }

                if(state == 3){
                    iname = parts[0].string;

                    if(StringsEqual(parts[0].string, "Fnc".toCharArray())){
                        LinkedListCharactersAddString(cc, "global ".toCharArray());
                        LinkedListCharactersAddString(cc, parts[1].string);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                        LinkedListCharactersAddString(cc, parts[1].string);
                        LinkedListCharactersAddString(cc, ":".toCharArray());
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Ret".toCharArray())){
                        LinkedListCharactersAddString(cc, "ret".toCharArray());
                        LinkedListCharactersAddString(cc, "\n\n".toCharArray());

                        state = 0;
                    }else if(StructHasKey(instructions, iname)){
                        ins = GetStructFromStruct(instructions, iname);
                        args = GetNumberFromStruct(ins, "args".toCharArray());
                        typeDecider = GetNumberFromStruct(ins, "typeDecider".toCharArray());
                        boolean noTypePostfix = GetBooleanFromStruct(ins, "noTypePostfix".toCharArray());
                        if(StructHasKey(ins, "alias".toCharArray())){
                            iname = GetStringFromStruct(ins, "alias".toCharArray());
                        }
                        if(args == 0){
                            parts[1].string = RemoveComma(parts[1].string);
                            PrintTabs(cc, tabs);
                            LinkedListCharactersAddString(cc, iname);
                            AppendUnaryArgument(cc, parts[1].string, fname, structure);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());
                        }else if(args == 1){
                            parts[1].string = RemoveComma(parts[1].string);
                            PrintTabs(cc, tabs);
                            LinkedListCharactersAddString(cc, iname);
                            AppendBinaryArguments(cc, parts[1].string, parts[2].string, fname, structure, typeDecider, noTypePostfix);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());
                        }else if(args == 2){
                            parts[1].string = RemoveComma(parts[1].string);
                            parts[2].string = RemoveComma(parts[2].string);
                            PrintTabs(cc, tabs);
                            LinkedListCharactersAddString(cc, iname);
                            AppendTernaryArguments(cc, parts[1].string, parts[2].string, parts[3].string, fname, structure, typeDecider);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());
                        }else if(args == 3){
                            parts[1].string = RemoveComma(parts[1].string);
                            parts[2].string = RemoveComma(parts[2].string);
                            parts[3].string = RemoveComma(parts[3].string);
                            PrintTabs(cc, tabs);
                            LinkedListCharactersAddString(cc, iname);
                            AppendQuadArguments(cc, parts[1].string, parts[2].string, parts[3].string, parts[4].string, fname, structure);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());
                        }else{
                            success = false;
                            message.string = "Unsupported argument amount.".toCharArray();
                        }
                    }else if(StringsEqual(parts[0].string, "Loop".toCharArray())){
                        label = GetNextLabel(labels);
                        ArrayPushNumber(loopStack, label);

                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Loop ".toCharArray());
                        LinkedListCharactersAddString(cc, "L".toCharArray());
                        labelString = nCreateStringDecimalFromNumber(label);
                        LinkedListCharactersAddString(cc, labelString);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());

                        tabs = tabs + 1d;
                        loopIf = true;
                    }else if(StringsEqual(parts[0].string, "EndLoop".toCharArray())){
                        tabs = tabs - 1d;

                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "EndLoop ".toCharArray());
                        label = ArrayPopNumber(loopStack);
                        LinkedListCharactersAddString(cc, "L".toCharArray());
                        labelString = nCreateStringDecimalFromNumber(label);
                        LinkedListCharactersAddString(cc, labelString);
                        LinkedListCharactersAddString(cc, ", ".toCharArray());
                        label = ArrayPopNumber(ifStack);
                        LinkedListCharactersAddString(cc, "L".toCharArray());
                        labelString = nCreateStringDecimalFromNumber(label);
                        LinkedListCharactersAddString(cc, labelString);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "If".toCharArray())){
                        if(loopIf){
                            tabs = tabs - 1d;
                            loopIf = false;
                        }

                        label = GetNextLabel(labels);
                        ArrayPushNumber(ifStack, label);

                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "If".toCharArray());
                        AppendUnaryArgument(cc, parts[1].string, fname, structure);
                        LinkedListCharactersAddString(cc, ", ".toCharArray());
                        LinkedListCharactersAddString(cc, "L".toCharArray());
                        labelString = nCreateStringDecimalFromNumber(label);
                        LinkedListCharactersAddString(cc, labelString);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());

                        tabs = tabs + 1d;
                    }else if(StringsEqual(parts[0].string, "Endb".toCharArray())){
                        tabs = tabs - 1d;

                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Endb ".toCharArray());

                        label = ArrayPopNumber(ifStack);
                        LinkedListCharactersAddString(cc, "L".toCharArray());
                        labelString = nCreateStringDecimalFromNumber(label);
                        LinkedListCharactersAddString(cc, labelString);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Len".toCharArray())){
                        parts[1].string = RemoveComma(parts[1].string);
                        parts[2].string = RemoveComma(parts[2].string);
                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Len".toCharArray());
                        AppendBinaryArgumentsLen(cc, parts[1].string, parts[2].string, fname);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Call".toCharArray())){
                        parts[1].string = RemoveComma(parts[1].string);
                        parts[2].string = RemoveComma(parts[2].string);
                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Call".toCharArray());
                        AppendBinaryArgumentsCall(cc, parts[1].string, parts[2].string, fname);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "New".toCharArray())){
                        parts[1].string = RemoveComma(parts[1].string);
                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "New".toCharArray());
                        AppendBinaryArgumentsNew(cc, parts[1].string, parts[2].string, fname);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Acw".toCharArray())){
                        parts[1].string = RemoveComma(parts[1].string);
                        parts[2].string = RemoveComma(parts[2].string);
                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Acw".toCharArray());
                        AppendTernaryArgumentsAcw(cc, parts[1].string, parts[2].string, parts[3].string, fname, structure, structures);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "Acr".toCharArray())){
                        parts[1].string = RemoveComma(parts[1].string);
                        parts[2].string = RemoveComma(parts[2].string);
                        PrintTabs(cc, tabs);
                        LinkedListCharactersAddString(cc, "Acr".toCharArray());
                        AppendTernaryArgumentsAcr(cc, parts[1].string, parts[2].string, parts[3].string, fname, structure, structures);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else{
                        message.string = "Token not allowed in function: ".toCharArray();
                        message.string = AppendString(message.string, parts[0].string);
                        success = false;
                    }
                }
            }
        }

        if(success) {
            if (state == 0) {
                output.string = LinkedListCharactersToArray(cc);

            } else {
                message.string = "Illegal state at the end of processing.".toCharArray();
                success = false;
            }
        }

        FreeLinkedListCharacter(cc);

        return success;
    }

    public static void AppendQuadArguments(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] arg3, char[] arg4, char[] fname, Structure structure) {
        char[] signature;
        signature = GetTernarySignature(arg2, arg3, arg4, structure, 1);
        LinkedListCharactersAddString(cc, signature);
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg2, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg3, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg4, fname);
    }

    public static double GetNextLabel(NumberReference labels) {
        labels.numberValue = labels.numberValue + 1d;
        return labels.numberValue;
    }

    public static void ArrayPushNumber(Array array, double n) {
        AddNumberToArray(array, n);
    }

    public static double ArrayPopNumber(Array array) {
        double n, i;

        i = ArrayLength(array) - 1d;
        n = ArrayIndex(array, i).number;
        ArrayRemove(array, i);

        return n;
    }

    public static void PrintTabs(LinkedListCharacters cc, double tabs) {
        double i;

        for(i = 0d; i < tabs; i = i + 1d){
            LinkedListCharactersAddString(cc, "  ".toCharArray());
        }
    }

    public static void AppendTernaryArguments(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] arg3, char[] fname, Structure structure, double typeDecider) {
        char[] signature;
        signature = GetTernarySignature(arg1, arg2, arg3, structure, typeDecider);
        LinkedListCharactersAddString(cc, signature);
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg2, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg3, fname);
    }

    public static char[] TranslateLiteral(char[] arg) {
        char [] lit;

        if(StringsEqual(arg, "true".toCharArray())){
            lit = "1".toCharArray();
        }else if(StringsEqual(arg, "false".toCharArray())){
            lit = "0".toCharArray();
        }else{
            lit = CopyString(arg);
        }

        return lit;
    }

    public static void AppendBinaryArgumentsNew(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] fname) {
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        LinkedListCharactersAddString(cc, arg2);
    }

    public static void AppendBinaryArgumentsLen(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] fname) {
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg2, fname);
    }

    public static void AppendTernaryArgumentsAcw(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] arg3, char[] fname, Structure structure, Array structures) {
        char[] signature;
        Array entries;
        char [] typeName;

        signature = GetTernarySignatureACW(arg1, arg2, arg3, structure, structures);
        LinkedListCharactersAddString(cc, signature);
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        if(!IsLiteral(arg2)){
            entries = GetArrayFromStruct(structure, "entries".toCharArray());

            typeName = GetTypeNameFromEntry(arg1, entries);

            LinkedListCharactersAddString(cc, typeName);
            LinkedListCharactersAddString(cc, ".".toCharArray());
        }else{
            arg2 = TranslateLiteral(arg2);
        }
        LinkedListCharactersAddString(cc, arg2);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg3, fname);
    }

    public static char[] GetTypeNameFromEntry(char[] argname, Array entries) {
        char[] typeName;
        double i;
        Structure entry;
        char[] name;
        boolean done;

        done = false;
        typeName = "".toCharArray();
        for(i = 0d; i < ArrayLength(entries) && !done; i = i + 1d){
            entry = ArrayIndex(entries, i).structure;
            name = GetStringFromStruct(entry, "name".toCharArray());
            typeName = GetStringFromStruct(entry, "typeName".toCharArray());

            if(StringsEqual(argname, name)){
                done = true;
            }
        }

        return typeName;
    }

    public static void AppendTernaryArgumentsAcr(LinkedListCharacters cc, char[] arg1, char[] arg2, char[] arg3, char[] fname, Structure structure, Array structures) {
        char[] signature;
        Array entries;
        char [] typeName;

        signature = GetTernarySignatureACR(arg1, arg2, arg3, structure, structures);
        LinkedListCharactersAddString(cc, signature);
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg2, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());

        entries = GetArrayFromStruct(structure, "entries".toCharArray());
        typeName = GetTypeNameFromEntry(arg2, entries);

        LinkedListCharactersAddString(cc, typeName);
        LinkedListCharactersAddString(cc, ".".toCharArray());

        LinkedListCharactersAddString(cc, arg3);
    }

    public static void AppendUnaryArgument(LinkedListCharacters cc, char[] arg1, char[] fname, Structure structure) {
        char[] signature;
        signature = GetSignature(arg1, structure);
        LinkedListCharactersAddString(cc, signature);
        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);
    }

    public static char[] GetSignature(char[] arg1, Structure structure) {
        boolean lit1;
        char [] type1;
        char [] signature;

        signature = new char [0];

        lit1 = IsLiteral(arg1);
        type1 = GetType(arg1, structure);

        signature = AppendString(signature, ".".toCharArray());

        if(lit1){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }

        signature = AppendString(signature, type1);

        return signature;
    }

    public static void AppendBinaryArguments(LinkedListCharacters cc, char [] arg1, char [] arg2, char[] fname, Structure structure, double typeDecider, boolean noTypePostfix) {
        char[] signature;
        signature = GetBinarySignature(arg1, arg2, structure, typeDecider, noTypePostfix);

        LinkedListCharactersAddString(cc, signature);

        LinkedListCharactersAddString(cc, " ".toCharArray());
        AppendArgument(cc, arg1, fname);

        LinkedListCharactersAddString(cc, ", ".toCharArray());
        AppendArgument(cc, arg2, fname);
    }

    public static void AppendArgument(LinkedListCharacters cc, char[] arg, char[] fname) {
        if(!IsLiteral(arg)){
            LinkedListCharactersAddString(cc, fname);
            LinkedListCharactersAddString(cc, "S.".toCharArray());
        }else{
            arg = TranslateLiteral(arg);
        }
        LinkedListCharactersAddString(cc, arg);
    }

    public static void AppendBinaryArgumentsCall(LinkedListCharacters cc, char [] arg1, char [] arg2, char[] fname) {
        LinkedListCharactersAddString(cc, " ".toCharArray());
        LinkedListCharactersAddString(cc, arg1);
        LinkedListCharactersAddString(cc, ", ".toCharArray());
        LinkedListCharactersAddString(cc, fname);
        LinkedListCharactersAddString(cc, "S.".toCharArray());
        LinkedListCharactersAddString(cc, arg2);
    }

    public static char[] RemoveComma(char[] string) {
        if(EndsWith(string, ",".toCharArray())){
            string = Substring(string, 0, string.length - 1d);
        }

        return string;
    }

    public static char[] GetBinarySignature(char[] arg1, char[] arg2, Structure structure, double typeDecider, boolean noTypePostfix) {
        boolean lit1, lit2;
        char [] type;
        char [] signature;

        signature = new char [0];

        lit2 = IsLiteral(arg2);

        if(typeDecider == 1d) {
            type = GetType(arg1, structure);
        }else if(typeDecider == 2d){
            type = GetType(arg2, structure);
        }else{
            if(!lit2){
                type = GetType(arg2, structure);
            }else {
                type = "unknown".toCharArray();
            }
        }

        signature = AppendString(signature, ".".toCharArray());

        if(lit2){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }

        if(!noTypePostfix) {
            signature = AppendString(signature, type);
        }

        return signature;
    }

    public static char[] GetTernarySignature(char [] arg1, char[] arg2, char[] arg3, Structure structure, double typeDecider) {
        boolean lit2, lit3;
        char [] type, type1, type2, type3;
        char [] signature;

        signature = new char [0];

        lit2 = IsLiteral(arg2);
        lit3 = IsLiteral(arg3);
        type1 = GetType(arg1, structure);
        type2 = GetType(arg2, structure);
        type3 = GetType(arg3, structure);

        if(typeDecider == 1){
            type = type1;
        }else if(typeDecider == 2){
            type = type2;
        }else if(typeDecider == 3){
            type = type3;
        }else{
            if(!lit2){
                type = type2;
            }else if(!lit3){
                type = type3;
            }else{
                type = "".toCharArray();
            }
        }

        if(typeDecider == 0 && lit2 && lit3){
            // No signature.
        }else {
            signature = AppendString(signature, ".".toCharArray());
            if (lit2) {
                signature = AppendString(signature, "i".toCharArray());
            } else {
                signature = AppendString(signature, "m".toCharArray());
            }
            if (lit3) {
                signature = AppendString(signature, "i".toCharArray());
            } else {
                signature = AppendString(signature, "m".toCharArray());
            }
            signature = AppendString(signature, type);
        }

        return signature;
    }

    public static char[] GetTernarySignatureACW(char [] arg1, char[] arg2, char[] arg3, Structure structure, Array structures) {
        boolean lit2, lit3;
        char [] type;
        char [] signature;
        Array entries;
        boolean done;
        char [] typeName, name;
        double i, j;
        Structure entry, str;

        signature = new char [0];

        lit2 = IsLiteral(arg2);
        lit3 = IsLiteral(arg3);

        entries = GetArrayFromStruct(structure, "entries".toCharArray());

        done = false;
        typeName = "".toCharArray();
        for(i = 0d; i < ArrayLength(entries) && !done; i = i + 1d){
            entry = ArrayIndex(entries, i).structure;
            name = GetStringFromStruct(entry, "name".toCharArray());
            typeName = GetStringFromStruct(entry, "typeName".toCharArray());

            if(StringsEqual(arg1, name)){
                done = true;
            }
        }
        done = false;
        type = "unknown".toCharArray();
        for(i = 0d; i < ArrayLength(structures) && !done; i = i + 1d){
            str = ArrayIndex(structures, i).structure;
            name = GetStringFromStruct(str, "name".toCharArray());

            if(StringsEqual(name, typeName)){
                //System.out.println(typeName);
                entries = GetArrayFromStruct(str, "entries".toCharArray());
                for(j = 0d; j < ArrayLength(entries) && !done; j = j + 1d){
                    entry = ArrayIndex(entries, j).structure;
                    name = GetStringFromStruct(entry, "name".toCharArray());
                    type = GetStringFromStruct(entry, "type".toCharArray());

                    if(StringsEqual(arg2, name)){
                        done = true;
                    }
                }
            }
        }

        signature = AppendString(signature, ".".toCharArray());

        if(lit2){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }
        if(lit3){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }

        signature = AppendString(signature, type);

        return signature;
    }

    public static char[] GetTernarySignatureACR(char [] arg1, char[] arg2, char[] arg3, Structure structure, Array structures) {
        boolean lit2, lit3;
        char [] type;
        char [] signature;
        Array entries;
        boolean done;
        char [] typeName, name;
        double i, j;
        Structure entry, str;

        signature = new char [0];

        lit2 = IsLiteral(arg2);
        lit3 = IsLiteral(arg3);

        entries = GetArrayFromStruct(structure, "entries".toCharArray());

        done = false;
        typeName = "".toCharArray();
        for(i = 0d; i < ArrayLength(entries) && !done; i = i + 1d){
            entry = ArrayIndex(entries, i).structure;
            name = GetStringFromStruct(entry, "name".toCharArray());
            typeName = GetStringFromStruct(entry, "typeName".toCharArray());

            if(StringsEqual(arg2, name)){
                done = true;
            }
        }
        done = false;
        type = "unknown".toCharArray();
        for(i = 0d; i < ArrayLength(structures) && !done; i = i + 1d){
            str = ArrayIndex(structures, i).structure;
            name = GetStringFromStruct(str, "name".toCharArray());

            if(StringsEqual(name, typeName)){
                //System.out.println(typeName);
                entries = GetArrayFromStruct(str, "entries".toCharArray());
                for(j = 0d; j < ArrayLength(entries) && !done; j = j + 1d){
                    entry = ArrayIndex(entries, j).structure;
                    name = GetStringFromStruct(entry, "name".toCharArray());
                    type = GetStringFromStruct(entry, "type".toCharArray());

                    if(StringsEqual(arg3, name)){
                        done = true;
                    }
                }
            }
        }

        signature = AppendString(signature, ".".toCharArray());

        if(lit2){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }
        if(lit3){
            signature = AppendString(signature, "i".toCharArray());
        }else{
            signature = AppendString(signature, "m".toCharArray());
        }

        signature = AppendString(signature, type);

        return signature;
    }

    public static char [] GetType(char[] arg, Structure structure) {
        double i;
        Array entries;
        Structure entry;
        char [] name, type;
        boolean done;

        entries = GetArrayFromStruct(structure, "entries".toCharArray());

        done = false;
        type = "".toCharArray();
        for(i = 0d; i < ArrayLength(entries) && !done; i = i + 1d){
            entry = ArrayIndex(entries, i).structure;
            name = GetStringFromStruct(entry, "name".toCharArray());
            type = GetStringFromStruct(entry, "type".toCharArray());

            if(StringsEqual(arg, name)){
                done = true;
            }
        }

        return type;
    }

    public static boolean IsLiteral(char[] arg) {
        return charIsNumber(arg[0]) || StringsEqual(arg, "true".toCharArray()) || StringsEqual(arg, "false".toCharArray()) || arg[0] == '\'';
    }

    public static boolean GenerateCHeader(char[] input, Array functions, Array structures, StringReference message, StringReference output) {
        boolean success;
        double i, j;
        LinkedListCharacters cc;
        Structure structure, entry, function;
        char [] name, type;
        Array entries;
        DataReference dataRef;
        Structure entryTypes;

        // Entry types for x86-64
        dataRef = new DataReference();
        entryTypes = new Structure();
        success = GetEntryTypes(dataRef, message);
        if(success){
            entryTypes = dataRef.data.structure;
        }

        cc = CreateLinkedListCharacter();
        success = true;

        LinkedListCharactersAddString(cc, "#include <stdint.h>\n\n".toCharArray());

        for(i = 0d; i < ArrayLength(structures); i = i + 1d){
            structure = ArrayIndex(structures, i).structure;

            name = GetStringFromStruct(structure, "name".toCharArray());

            LinkedListCharactersAddString(cc, "struct ".toCharArray());
            LinkedListCharactersAddString(cc, name);
            LinkedListCharactersAddString(cc, ";\n".toCharArray());
        }

        LinkedListCharactersAddString(cc, "\n".toCharArray());

        LinkedListCharactersAddString(cc, "#pragma pack(push, 1)\n".toCharArray());

        for(i = 0d; i < ArrayLength(structures); i = i + 1d){
            structure = ArrayIndex(structures, i).structure;

            name = GetStringFromStruct(structure, "name".toCharArray());
            entries = GetArrayFromStruct(structure, "entries".toCharArray());


            LinkedListCharactersAddString(cc, "struct ".toCharArray());
            LinkedListCharactersAddString(cc, name);
            LinkedListCharactersAddString(cc, "{\n".toCharArray());

            for(j = 0d; j < ArrayLength(entries); j = j + 1d){
                entry = ArrayIndex(entries, j).structure;

                name = GetStringFromStruct(entry, "name".toCharArray());
                type = GetStringFromStruct(entry, "type".toCharArray());

                LinkedListCharactersAddString(cc, "\t".toCharArray());
                if(StringsEqual(type, "str".toCharArray())){
                    type = GetStringFromStruct(entry, "typeName".toCharArray());
                    LinkedListCharactersAddString(cc, "struct ".toCharArray());
                    LinkedListCharactersAddString(cc, type);
                    LinkedListCharactersAddString(cc, " *".toCharArray());
                } else {
                    LinkedListCharactersAddString(cc, MapType(type, entryTypes));
                    LinkedListCharactersAddString(cc, " ".toCharArray());
                }
                LinkedListCharactersAddString(cc, name);
                LinkedListCharactersAddString(cc, ";\n".toCharArray());
            }

            LinkedListCharactersAddString(cc, "};\n\n".toCharArray());
        }

        LinkedListCharactersAddString(cc, "#pragma pack(pop)\n\n".toCharArray());

        for(i = 0d; i < ArrayLength(functions); i = i + 1d){
            function = ArrayIndex(functions, i).structure;

            name = GetStringFromStruct(function, "name".toCharArray());

            LinkedListCharactersAddString(cc, "void ".toCharArray());
            LinkedListCharactersAddString(cc, name);
            LinkedListCharactersAddString(cc, "(struct ".toCharArray());
            LinkedListCharactersAddString(cc, name);
            LinkedListCharactersAddString(cc, "S *args);\n".toCharArray());
        }

        output.string = LinkedListCharactersToArray(cc);
        FreeLinkedListCharacter(cc);

        return success;
    }

    public static char[] MapType(char[] type, Structure entryTypes) {
        char [] newType;
        Structure entryType;

        entryType = GetStructFromStruct(entryTypes, type);
        newType = GetStringFromStruct(entryType, "ctype".toCharArray());

        return newType;
    }

    public static void delete(Object object) {
        // Java has garbage collection.
    }

    public static boolean TranslateExpressions(char[] code, Array functions, Array structures, StringReference message, StringReference output) {
        boolean success;
        StringReference [] lines, parts, expandedLines;
        double i, j;
        char [] line, name, orgline, target, type;
        Structure structure, function;
        double state, tabs;
        boolean done, loopIf;
        LinkedListCharacters cc;
        StringReference expandedCode;

        cc = CreateLinkedListCharacter();

        success = true;
        tabs = 1;
        loopIf = false;

        lines = SplitByCharacter(code, '\n');

        state = 0;
        for(i = 0d; i < lines.length && success; i = i + 1d){
            orgline = lines[(int)i].string;
            line = MergeWhitespace(orgline);
            line = Trim(line);

            if(line.length != 0 && !StartsWith(line, ";".toCharArray())) {
                parts = SplitByCharacter(line, ' ');

                if(state == 0) {
                    if (StringsEqual(parts[0].string, "Bgs".toCharArray())) {
                        state = 1;
                        done = false;
                        for(j = 0d; j < ArrayLength(structures) && !done; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }
                    }
                    if(StringsEqual(parts[0].string, "Fnc".toCharArray())){
                        state = 3;
                        done = false;
                        for(j = 0d; j < ArrayLength(functions) && !done; j = j + 1d){
                            function = ArrayIndex(functions, j).structure;
                            name = GetStringFromStruct(function, "name".toCharArray());
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }

                        done = false;
                        for(j = 0d; j < ArrayLength(structures) && !done; j = j + 1d){
                            structure = ArrayIndex(structures, j).structure;
                            name = GetStringFromStruct(structure, "name".toCharArray());
                            name = AppendCharacter(name, 'S');
                            if(StringsEqual(name, parts[1].string)){
                                done = true;
                            }
                        }
                    }
                }

                if(state == 1){
                    if(StringsEqual(parts[0].string, "Ens".toCharArray())){
                        state = 0;
                        LinkedListCharactersAddString(cc, orgline);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }else{
                        LinkedListCharactersAddString(cc, orgline);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }
                }

                if(state == 3){
                    if(StringsEqual(parts[0].string, "Ret".toCharArray())){
                        state = 0;

                        LinkedListCharactersAddString(cc, orgline);
                        LinkedListCharactersAddString(cc, "\n\n".toCharArray());
                    }else if(StringsEqual(parts[0].string, "If".toCharArray()) && StringsEqual(parts[1].string, "exp".toCharArray())){

                        if(StringsEqual(parts[2].string, "b:".toCharArray())) {
                            if(loopIf) {
                                PrintTabs(cc, tabs + 1d);
                            }else{
                                PrintTabs(cc, tabs);
                            }
                            LinkedListCharactersAddString(cc, "; ".toCharArray());
                            LinkedListCharactersAddString(cc, line);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());

                            line = Substring(line, "If exp b:".toCharArray().length, line.length);

                            expandedCode = new StringReference();
                            success = BooleanFormulaToTFormFunctions(line, "".toCharArray(), "".toCharArray(), "b1".toCharArray(), false, false, false, "".toCharArray(), true, expandedCode, message);

                            if(success) {
                                expandedLines = SplitByString(expandedCode.string, "\n".toCharArray());

                                for (j = 0d; j < expandedLines.length - 1; j = j + 1d) {
                                    if (loopIf) {
                                        PrintTabs(cc, tabs + 1d);
                                    } else {
                                        PrintTabs(cc, tabs);
                                    }
                                    LinkedListCharactersAddString(cc, expandedLines[(int) j].string);
                                    LinkedListCharactersAddString(cc, "\n".toCharArray());
                                }

                                PrintTabs(cc, tabs);
                                LinkedListCharactersAddString(cc, expandedLines[(int) j].string);
                                LinkedListCharactersAddString(cc, "\n".toCharArray());
                            }
                        }else{
                            success = false;
                            message.string = "Unknown expression type in If, must be \"exp b\": ".toCharArray();
                            message.string = AppendString(message.string, parts[2].string);
                        }
                    }else if(StringsEqual(parts[0].string, "exp".toCharArray())){
                        type = Substring(parts[2].string, 0d, parts[2].string.length - 1d);

                        if(loopIf) {
                            PrintTabs(cc, tabs + 1d);
                        }else{
                            PrintTabs(cc, tabs);
                        }
                        LinkedListCharactersAddString(cc, "; ".toCharArray());
                        LinkedListCharactersAddString(cc, line);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());

                        line = Substring(line, parts[0].string.length + 1d + parts[1].string.length + 1d + parts[2].string.length, line.length);

                        line = Trim(line);
                        target = parts[3].string;
                        line = Substring(line, target.length, line.length);
                        line = Trim(line);
                        line = Substring(line, 1, line.length);
                        line = Trim(line);

                        expandedCode = new StringReference();

                        if(StringsEqual(parts[1].string, "bw".toCharArray()) && EndsWith(parts[2].string, ":".toCharArray())) {
                            success = BitwiseFormulaToTFormFunctions(line, "".toCharArray(), "".toCharArray(), type, false, false, false, target, expandedCode, message);
                        }else if(StringsEqual(parts[1].string, "a".toCharArray()) && EndsWith(parts[2].string, ":".toCharArray())) {
                            success = ArithmeticFormulaToTFormFunctions(line, "".toCharArray(), "".toCharArray(), type, false, false, false, target, expandedCode, message);
                        }else if(StringsEqual(parts[1].string, "b".toCharArray()) && EndsWith(parts[2].string, ":".toCharArray())) {
                            if(type.length == 0){
                                type = "b1".toCharArray();
                            }

                            success = BooleanFormulaToTFormFunctions(line, "".toCharArray(), "".toCharArray(), type, false, false, false, target, false, expandedCode, message);
                        }else{
                            success = false;
                            message.string = "Unknown expression type: ".toCharArray();
                            message.string = AppendString(message.string, parts[1].string);
                        }

                        if(success) {
                            expandedLines = SplitByString(expandedCode.string, "\n".toCharArray());

                            for (j = 0d; j < expandedLines.length - 1; j = j + 1d) {
                                if (loopIf) {
                                    PrintTabs(cc, tabs + 1d);
                                } else {
                                    PrintTabs(cc, tabs);
                                }
                                LinkedListCharactersAddString(cc, expandedLines[(int) j].string);
                                LinkedListCharactersAddString(cc, "\n".toCharArray());
                            }

                            PrintTabs(cc, tabs);
                            LinkedListCharactersAddString(cc, expandedLines[(int) j].string);
                            LinkedListCharactersAddString(cc, "\n".toCharArray());
                        }
                    }else{
                        LinkedListCharactersAddString(cc, orgline);
                        LinkedListCharactersAddString(cc, "\n".toCharArray());
                    }

                    if(StringsEqual(parts[0].string, "Loop".toCharArray())){
                        loopIf = true;
                    }
                    if(StringsEqual(parts[0].string, "If".toCharArray())){
                        tabs = tabs + 1d;
                        loopIf = false;
                    }
                    if(StringsEqual(parts[0].string, "EndLoop".toCharArray())){
                        tabs = tabs - 1d;
                        loopIf = false;
                    }
                    if(StringsEqual(parts[0].string, "Endb".toCharArray())){
                        tabs = tabs - 1d;
                    }
                }
            }
        }

        if(success) {
            if (state == 0) {
                output.string = LinkedListCharactersToArray(cc);

            } else {
                message.string = "Illegal state at the end of processing.".toCharArray();
                success = false;
            }
        }

        FreeLinkedListCharacter(cc);

        return success;
    }
}































