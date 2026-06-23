package Translator.Translator;

import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.DataReference;
import lists.LinkedListCharacters.Structures.LinkedListCharacters;
import references.references.NumberReference;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static DataStructures.Array.Structures.Structures.CreateArrayData;
import static arrays.arrays.arrays.StringsEqual;
import static charCharacters.Characters.Characters.charIsLetter;
import static charCharacters.Characters.Characters.charIsNumber;
import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;
import static references.references.references.CreateNumberReference;

public class Translator2 {
    public static boolean Tokenize(char[] input, DataReference tokensRef, StringReference message) {
        Array tokens, ins;
        double i, state;
        char c;
        LinkedListCharacters ll;
        char [] str;
        boolean success;

        success = true;
        message.string = "".toCharArray();

        ll = CreateLinkedListCharacter();

        tokens = CreateArray();

        state = 0d;
        for(i = 0; i < input.length + 1; i = i + 1d){
            if(i < input.length) {
                c = input[(int) i];
            }else{
                c = '\n';
            }

            if(state == 0d) {
                if (c == ' ' || c == '\t') {
                    // skip
                }else if(c == '\n'){
                    ArrayAddString(tokens, "<newline>".toCharArray());
                    System.out.println("<newline>");
                }else if(charIsLetter(c)){
                    state = 1d;
                    ll = CreateLinkedListCharacter();
                }else if(charIsNumber(c) || c == '-'){
                    state = 2d;
                    ll = CreateLinkedListCharacter();
                }else if(c == ';'){
                    state = 3d;
                }else if(c == ','){
                    ArrayAddString(tokens, "<comma>".toCharArray());
                    System.out.println("<comma>");
                }else{
                    success = false;
                    message.string = "Character not allowed in token.".toCharArray();
                }
            }

            // Identifier
            if(state == 1d){
                if(charIsLetter(c)){
                    LinkedListAddCharacter(ll, c);
                }else if(charIsNumber(c)){
                    LinkedListAddCharacter(ll, c);
                }else if(c == '_'){
                    LinkedListAddCharacter(ll, c);
                }else if(c == '\n' || c == ' ' || c == '\t' || c == ','){
                    // Done
                    str = LinkedListCharactersToArray(ll);
                    ArrayAddString(tokens, str);
                    System.out.println(new String(str));
                    state = 0d;

                    i = i - 1d;
                }else{
                    message.string = "Character not allowed in token.".toCharArray();
                    success = false;
                }
            }

            // Literal
            if(state == 2d){
                if(charIsNumber(c)){
                    LinkedListAddCharacter(ll, c);
                }else if(c == '.'){
                    LinkedListAddCharacter(ll, c);
                }else if(c == 'e'){
                    LinkedListAddCharacter(ll, c);
                }else if(c == '-'){
                    LinkedListAddCharacter(ll, c);
                }else if(c == '\n' || c == ' ' || c == '\t' || c == ','){
                    // Done
                    str = LinkedListCharactersToArray(ll);
                    ArrayAddString(tokens, str);
                    System.out.println(new String(str));
                    state = 0d;

                    i = i - 1d;
                }else{
                    message.string = "Character not allowed in token.".toCharArray();
                    success = false;
                }
            }

            // Comment
            if(state == 3d){
                if(c == '\n'){
                    state = 0d;
                }else{
                    // skip
                }
            }
        }

        tokensRef.data = CreateArrayData(tokens);
        return success;
    }

    public static Array GetTypeInstructionTokens() {
        Array ins;

        ins = CreateArray();

        // Types
        ArrayAddString(ins, "u8".toCharArray());
        ArrayAddString(ins, "u16".toCharArray());
        ArrayAddString(ins, "u32".toCharArray());
        ArrayAddString(ins, "u64".toCharArray());
        ArrayAddString(ins, "u128".toCharArray());

        ArrayAddString(ins, "s8".toCharArray());
        ArrayAddString(ins, "s16".toCharArray());
        ArrayAddString(ins, "s32".toCharArray());
        ArrayAddString(ins, "s64".toCharArray());
        ArrayAddString(ins, "s128".toCharArray());

        ArrayAddString(ins, "b1".toCharArray());
        ArrayAddString(ins, "b8".toCharArray());
        ArrayAddString(ins, "b16".toCharArray());
        ArrayAddString(ins, "b32".toCharArray());
        ArrayAddString(ins, "b64".toCharArray());
        ArrayAddString(ins, "b128".toCharArray());
        ArrayAddString(ins, "b256".toCharArray());
        ArrayAddString(ins, "b512".toCharArray());

        ArrayAddString(ins, "f16".toCharArray());
        ArrayAddString(ins, "f32".toCharArray());
        ArrayAddString(ins, "f64".toCharArray());
        ArrayAddString(ins, "f80".toCharArray());

        ArrayAddString(ins, "u8a".toCharArray());
        ArrayAddString(ins, "u16a".toCharArray());
        ArrayAddString(ins, "u32a".toCharArray());
        ArrayAddString(ins, "u64a".toCharArray());
        ArrayAddString(ins, "u128a".toCharArray());

        ArrayAddString(ins, "s8a".toCharArray());
        ArrayAddString(ins, "s16a".toCharArray());
        ArrayAddString(ins, "s32a".toCharArray());
        ArrayAddString(ins, "s64a".toCharArray());
        ArrayAddString(ins, "s128a".toCharArray());

        ArrayAddString(ins, "b1a".toCharArray());
        ArrayAddString(ins, "b8a".toCharArray());
        ArrayAddString(ins, "b16a".toCharArray());
        ArrayAddString(ins, "b32a".toCharArray());
        ArrayAddString(ins, "b64a".toCharArray());
        ArrayAddString(ins, "b128a".toCharArray());
        ArrayAddString(ins, "b256a".toCharArray());
        ArrayAddString(ins, "b512a".toCharArray());

        ArrayAddString(ins, "f16a".toCharArray());
        ArrayAddString(ins, "f32a".toCharArray());
        ArrayAddString(ins, "f64a".toCharArray());
        ArrayAddString(ins, "f80a".toCharArray());

        ArrayAddString(ins, "u8x8".toCharArray());
        ArrayAddString(ins, "u8x16".toCharArray());
        ArrayAddString(ins, "u8x32".toCharArray());
        ArrayAddString(ins, "u8x64".toCharArray());
        ArrayAddString(ins, "u8x8a".toCharArray());
        ArrayAddString(ins, "u8x16a".toCharArray());
        ArrayAddString(ins, "u8x32a".toCharArray());
        ArrayAddString(ins, "u8x64a".toCharArray());

        ArrayAddString(ins, "u16x4".toCharArray());
        ArrayAddString(ins, "u16x8".toCharArray());
        ArrayAddString(ins, "u16x16".toCharArray());
        ArrayAddString(ins, "u16x32".toCharArray());
        ArrayAddString(ins, "u16x4a".toCharArray());
        ArrayAddString(ins, "u16x8a".toCharArray());
        ArrayAddString(ins, "u16x16a".toCharArray());
        ArrayAddString(ins, "u16x32a".toCharArray());

        ArrayAddString(ins, "u32x2".toCharArray());
        ArrayAddString(ins, "u32x4".toCharArray());
        ArrayAddString(ins, "u32x8".toCharArray());
        ArrayAddString(ins, "u32x16".toCharArray());
        ArrayAddString(ins, "u32x2a".toCharArray());
        ArrayAddString(ins, "u32x4a".toCharArray());
        ArrayAddString(ins, "u32x8a".toCharArray());
        ArrayAddString(ins, "u32x16a".toCharArray());

        ArrayAddString(ins, "u64x2".toCharArray());
        ArrayAddString(ins, "u64x4".toCharArray());
        ArrayAddString(ins, "u64x8".toCharArray());
        ArrayAddString(ins, "u64x2a".toCharArray());
        ArrayAddString(ins, "u64x4a".toCharArray());
        ArrayAddString(ins, "u64x8a".toCharArray());

        ArrayAddString(ins, "s8x8".toCharArray());
        ArrayAddString(ins, "s8x16".toCharArray());
        ArrayAddString(ins, "s8x32".toCharArray());
        ArrayAddString(ins, "s8x64".toCharArray());
        ArrayAddString(ins, "s8x8a".toCharArray());
        ArrayAddString(ins, "s8x16a".toCharArray());
        ArrayAddString(ins, "s8x32a".toCharArray());
        ArrayAddString(ins, "s8x64a".toCharArray());

        ArrayAddString(ins, "s16x4".toCharArray());
        ArrayAddString(ins, "s16x8".toCharArray());
        ArrayAddString(ins, "s16x16".toCharArray());
        ArrayAddString(ins, "s16x32".toCharArray());
        ArrayAddString(ins, "s16x4a".toCharArray());
        ArrayAddString(ins, "s16x8a".toCharArray());
        ArrayAddString(ins, "s16x16a".toCharArray());
        ArrayAddString(ins, "s16x32a".toCharArray());

        ArrayAddString(ins, "s32x2".toCharArray());
        ArrayAddString(ins, "s32x4".toCharArray());
        ArrayAddString(ins, "s32x8".toCharArray());
        ArrayAddString(ins, "s32x16".toCharArray());
        ArrayAddString(ins, "s32x2a".toCharArray());
        ArrayAddString(ins, "s32x4a".toCharArray());
        ArrayAddString(ins, "s32x8a".toCharArray());
        ArrayAddString(ins, "s32x16a".toCharArray());

        ArrayAddString(ins, "s64x2".toCharArray());
        ArrayAddString(ins, "s64x4".toCharArray());
        ArrayAddString(ins, "s64x8".toCharArray());
        ArrayAddString(ins, "s64x2a".toCharArray());
        ArrayAddString(ins, "s64x4a".toCharArray());
        ArrayAddString(ins, "s64x8a".toCharArray());

        ArrayAddString(ins, "b8x8".toCharArray());
        ArrayAddString(ins, "b8x16".toCharArray());
        ArrayAddString(ins, "b8x32".toCharArray());
        ArrayAddString(ins, "b8x64".toCharArray());
        ArrayAddString(ins, "b8x8a".toCharArray());
        ArrayAddString(ins, "b8x16a".toCharArray());
        ArrayAddString(ins, "b8x32a".toCharArray());
        ArrayAddString(ins, "b8x64a".toCharArray());

        ArrayAddString(ins, "b16x4".toCharArray());
        ArrayAddString(ins, "b16x8".toCharArray());
        ArrayAddString(ins, "b16x16".toCharArray());
        ArrayAddString(ins, "b16x32".toCharArray());
        ArrayAddString(ins, "b16x4a".toCharArray());
        ArrayAddString(ins, "b16x8a".toCharArray());
        ArrayAddString(ins, "b16x16a".toCharArray());
        ArrayAddString(ins, "b16x32a".toCharArray());

        ArrayAddString(ins, "b32x2".toCharArray());
        ArrayAddString(ins, "b32x4".toCharArray());
        ArrayAddString(ins, "b32x8".toCharArray());
        ArrayAddString(ins, "b32x16".toCharArray());
        ArrayAddString(ins, "b32x2a".toCharArray());
        ArrayAddString(ins, "b32x4a".toCharArray());
        ArrayAddString(ins, "b32x8a".toCharArray());
        ArrayAddString(ins, "b32x16a".toCharArray());

        ArrayAddString(ins, "b64x2".toCharArray());
        ArrayAddString(ins, "b64x4".toCharArray());
        ArrayAddString(ins, "b64x8".toCharArray());
        ArrayAddString(ins, "b64x2a".toCharArray());
        ArrayAddString(ins, "b64x4a".toCharArray());
        ArrayAddString(ins, "b64x8a".toCharArray());

        ArrayAddString(ins, "f16x8".toCharArray());
        ArrayAddString(ins, "f16x16".toCharArray());
        ArrayAddString(ins, "f16x32".toCharArray());

        ArrayAddString(ins, "f32x4".toCharArray());
        ArrayAddString(ins, "f32x8".toCharArray());
        ArrayAddString(ins, "f32x16".toCharArray());
        ArrayAddString(ins, "f32x4a".toCharArray());
        ArrayAddString(ins, "f32x8a".toCharArray());
        ArrayAddString(ins, "f32x16a".toCharArray());

        ArrayAddString(ins, "f64x2".toCharArray());
        ArrayAddString(ins, "f64x4".toCharArray());
        ArrayAddString(ins, "f64x8".toCharArray());
        ArrayAddString(ins, "f64x2a".toCharArray());
        ArrayAddString(ins, "f64x4a".toCharArray());
        ArrayAddString(ins, "f64x8a".toCharArray());

        return ins;
    }

    public static Array GetInstructionTokens() {
        Array ins;

        ins = CreateArray();

        // Basics
        // ArrayAddString(ins, "Bgs".toCharArray());
        // ArrayAddString(ins, "Ens".toCharArray());
        // ArrayAddString(ins, "Fnc".toCharArray());
        // ArrayAddString(ins, "Ret".toCharArray());

        // Types
        // Computations
        ArrayAddString(ins, "Mov".toCharArray());
        ArrayAddString(ins, "Broadcast".toCharArray());

        ArrayAddString(ins, "Add".toCharArray());
        ArrayAddString(ins, "Sub".toCharArray());
        ArrayAddString(ins, "Mul".toCharArray());
        ArrayAddString(ins, "Div".toCharArray());
        ArrayAddString(ins, "Mod".toCharArray());
        ArrayAddString(ins, "DivMod".toCharArray());
        ArrayAddString(ins, "MulDiv".toCharArray());
        ArrayAddString(ins, "MulFull".toCharArray());
        ArrayAddString(ins, "Inc".toCharArray());
        ArrayAddString(ins, "Dec".toCharArray());
        ArrayAddString(ins, "Round".toCharArray());
        ArrayAddString(ins, "Floor".toCharArray());
        ArrayAddString(ins, "Ceil".toCharArray());
        ArrayAddString(ins, "Truncate".toCharArray());
        ArrayAddString(ins, "Abs".toCharArray());
        ArrayAddString(ins, "Not".toCharArray());
        ArrayAddString(ins, "Shl".toCharArray());
        ArrayAddString(ins, "Shr".toCharArray());
        ArrayAddString(ins, "Sar".toCharArray());
        ArrayAddString(ins, "ShiftLeft".toCharArray());
        ArrayAddString(ins, "ShiftRight".toCharArray());
        ArrayAddString(ins, "ShiftArithmeticRight".toCharArray());
        ArrayAddString(ins, "And".toCharArray());
        ArrayAddString(ins, "Andnot".toCharArray());
        ArrayAddString(ins, "Or".toCharArray());
        ArrayAddString(ins, "Xor".toCharArray());
        ArrayAddString(ins, "Ror".toCharArray());
        ArrayAddString(ins, "Rol".toCharArray());
        ArrayAddString(ins, "Bswap".toCharArray());
        ArrayAddString(ins, "Bsf".toCharArray());
        ArrayAddString(ins, "Bsr".toCharArray());
        ArrayAddString(ins, "Bt".toCharArray());

        ArrayAddString(ins, "Lt".toCharArray());
        ArrayAddString(ins, "Lte".toCharArray());
        ArrayAddString(ins, "LessThanOrEqual".toCharArray());
        ArrayAddString(ins, "LessThan".toCharArray());
        ArrayAddString(ins, "MoreThanOrEqual".toCharArray());
        ArrayAddString(ins, "MoreThan".toCharArray());
        ArrayAddString(ins, "Gt".toCharArray());
        ArrayAddString(ins, "Gte".toCharArray());
        ArrayAddString(ins, "MoreThanOrEqual".toCharArray());
        ArrayAddString(ins, "Eq".toCharArray());
        ArrayAddString(ins, "Equal".toCharArray());
        ArrayAddString(ins, "Neq".toCharArray());
        ArrayAddString(ins, "Unequal".toCharArray());

        ArrayAddString(ins, "Idr".toCharArray());
        ArrayAddString(ins, "Idw".toCharArray());
        ArrayAddString(ins, "Idro".toCharArray());

        ArrayAddString(ins, "Rdrand".toCharArray());

        ArrayAddString(ins, "New".toCharArray());
        ArrayAddString(ins, "Del".toCharArray());

        ArrayAddString(ins, "s8tos16".toCharArray());
        ArrayAddString(ins, "s16tof32".toCharArray());
        ArrayAddString(ins, "f32tof64".toCharArray());
        ArrayAddString(ins, "s16tos8".toCharArray());
        ArrayAddString(ins, "f32tos16".toCharArray());
        ArrayAddString(ins, "f64tof32".toCharArray());
        ArrayAddString(ins, "u8tou16".toCharArray());
        ArrayAddString(ins, "u8tou32".toCharArray());
        ArrayAddString(ins, "u8x8tou16x8".toCharArray());
        ArrayAddString(ins, "u8x4tou32x4".toCharArray());
        ArrayAddString(ins, "u16tou32".toCharArray());
        ArrayAddString(ins, "u32tou64".toCharArray());
        ArrayAddString(ins, "f64x2tof32x2".toCharArray());
        ArrayAddString(ins, "f64x4tof32x4".toCharArray());
        ArrayAddString(ins, "f32x4tof16x4".toCharArray());
        ArrayAddString(ins, "f32x8tof16x8".toCharArray());
        ArrayAddString(ins, "f32x4tof64x4".toCharArray());

        ArrayAddString(ins, "Xu32x4".toCharArray());
        ArrayAddString(ins, "Xb8".toCharArray());
        ArrayAddString(ins, "Xb16".toCharArray());
        ArrayAddString(ins, "Xb32".toCharArray());
        ArrayAddString(ins, "Xb64".toCharArray());
        ArrayAddString(ins, "Xb128".toCharArray());
        ArrayAddString(ins, "Xb256".toCharArray());

        ArrayAddString(ins, "Xu16x8a".toCharArray());
        ArrayAddString(ins, "u32tou16s".toCharArray());

        ArrayAddString(ins, "Cmov".toCharArray());
        ArrayAddString(ins, "Set".toCharArray());
        ArrayAddString(ins, "Xchg".toCharArray());
        ArrayAddString(ins, "Xadd".toCharArray());
        ArrayAddString(ins, "CmpXchg".toCharArray());
        ArrayAddString(ins, "Crc32".toCharArray());

        ArrayAddString(ins, "Movs".toCharArray());
        ArrayAddString(ins, "Cmps".toCharArray());
        ArrayAddString(ins, "Scas".toCharArray());
        ArrayAddString(ins, "Popcnt".toCharArray());
        ArrayAddString(ins, "Lzcnt".toCharArray());
        ArrayAddString(ins, "Tzcnt".toCharArray());
        ArrayAddString(ins, "Rdtsc".toCharArray());
        ArrayAddString(ins, "Nop".toCharArray());
        ArrayAddString(ins, "Rdpmc".toCharArray());

        ArrayAddString(ins, "Bextr".toCharArray());
        ArrayAddString(ins, "Blsi".toCharArray());
        ArrayAddString(ins, "Blsmsk".toCharArray());
        ArrayAddString(ins, "Blsr".toCharArray());
        ArrayAddString(ins, "Bzhi".toCharArray());
        ArrayAddString(ins, "Pdep".toCharArray());
        ArrayAddString(ins, "Pext".toCharArray());
        ArrayAddString(ins, "ExtractMask".toCharArray());
        ArrayAddString(ins, "Shuffle".toCharArray());
        ArrayAddString(ins, "MulStoreHigh".toCharArray());
        ArrayAddString(ins, "AddSaturated".toCharArray());
        ArrayAddString(ins, "SubSaturated".toCharArray());
        ArrayAddString(ins, "MultiplyAndAdd".toCharArray());
        ArrayAddString(ins, "Avg".toCharArray());
        ArrayAddString(ins, "Max".toCharArray());
        ArrayAddString(ins, "Min".toCharArray());
        ArrayAddString(ins, "Sqrt".toCharArray());
        ArrayAddString(ins, "ApproxReciprocal".toCharArray());
        ArrayAddString(ins, "ApproxReciprocalSqrt".toCharArray());
        ArrayAddString(ins, "InterleaveLow".toCharArray());
        ArrayAddString(ins, "InterleaveHigh".toCharArray());
        ArrayAddString(ins, "SelectiveCopy".toCharArray());
        ArrayAddString(ins, "CombineExtract".toCharArray());
        ArrayAddString(ins, "SumAbsoluteDifference".toCharArray());
        ArrayAddString(ins, "PairwiseAdd".toCharArray());
        ArrayAddString(ins, "PairwiseSub".toCharArray());
        ArrayAddString(ins, "PairwiseMulAdd".toCharArray());
        ArrayAddString(ins, "AlternatingSubAdd".toCharArray());
        ArrayAddString(ins, "ConditionalNegate".toCharArray());
        ArrayAddString(ins, "DotProduct".toCharArray());
        ArrayAddString(ins, "Gather".toCharArray());
        ArrayAddString(ins, "MovAndDuplicate".toCharArray());
        ArrayAddString(ins, "MovAndDuplicateOdd".toCharArray());

        ArrayAddString(ins, "StringSubset".toCharArray());
        ArrayAddString(ins, "StringRangeCheck".toCharArray());
        ArrayAddString(ins, "MatchString".toCharArray());
        ArrayAddString(ins, "FindSubstring".toCharArray());

        // Control
        ArrayAddString(ins, "If".toCharArray());
        ArrayAddString(ins, "Loop".toCharArray());
        ArrayAddString(ins, "EndLoop".toCharArray());
        ArrayAddString(ins, "Else".toCharArray());
        ArrayAddString(ins, "Endb".toCharArray());
        ArrayAddString(ins, "Call".toCharArray());

        // Structural
        ArrayAddString(ins, "Len".toCharArray());
        ArrayAddString(ins, "Acw".toCharArray());
        ArrayAddString(ins, "Acr".toCharArray());

        return ins;
    }

    public static boolean Parse(Array tokens, Ast ast, StringReference message) {
        Array ins;
        boolean success;
        double functions, structures, i, st, fnc;
        char [] token;
        NumberReference tokenRef;
        Struct struct;
        Function function;

        success = true;

        ins = GetInstructionTokens();

        // Count structures and functions
        functions = 0d;
        structures = 0d;
        for(i = 0d; i < ArrayLength(tokens); i = i + 1d){
            token = ArrayIndexString(tokens, i);
            if(StringsEqual(token, "Bgs".toCharArray())){
                structures = structures + 1d;
            }
            if(StringsEqual(token, "Fnc".toCharArray())){
                functions = functions + 1d;
            }
        }

        ast.fnc = new Function[(int)functions];
        ast.st = new Struct[(int)structures];

        // Parse functions and structures
        st = 0d;
        fnc = 0d;
        tokenRef = CreateNumberReference(0d);
        for(; tokenRef.numberValue < ArrayLength(tokens) && success; ){
            token = ArrayIndexString(tokens, tokenRef.numberValue);
            if(StringsEqual(token, "Bgs".toCharArray())){
                struct = new Struct();
                ast.st[(int)st] = struct;

                success = ParseStructure(struct, tokens, tokenRef, message);

                st = st + 1d;
            }else if(StringsEqual(token, "Fnc".toCharArray())){
                function = new Function();
                ast.fnc[(int)fnc] = function;

                success = ParseFunction(function, tokens, tokenRef, message);

                fnc = fnc + 1d;
            }else if(StringsEqual(token, "<newline>".toCharArray())){
                // OK
            }else{
                success = false;
                message.string = ("Unexpected token outside functions and structures: " + new String(token)).toCharArray();
            }
        }

        return success;
    }

    public static boolean ParseStructure(Struct struct, Array tokens, NumberReference tokenRef, StringReference message) {
        boolean success;
        char [] token;

        success = true;

        token = GetNextToken(tokens, tokenRef);

        success = IsValidIdentifier(token, message);
        if(success){
            struct.name = token;

            System.out.println("Bgs " + new String(struct.name));

            token = GetNextToken(tokens, tokenRef);

            success = IsNewline(token, message);

        }

        return success;
    }

    private static boolean IsNewline(char[] token, StringReference message) {
        boolean valid;

        valid = StringsEqual(token, "<newline>".toCharArray());

        if(valid){

        }else{
            message.string = "Newline expected.".toCharArray();
        }

        return valid;
    }

    public static char[] GetNextToken(Array tokens, NumberReference tokenRef) {
        Inc(tokenRef);
        return ArrayIndexString(tokens, tokenRef.numberValue);
    }

    public static void Inc(NumberReference tokenRef) {
        tokenRef.numberValue = tokenRef.numberValue + 1d;
    }

    public static boolean IsValidIdentifier(char[] token, StringReference message) {
        boolean valid;
        double i;
        char c;

        valid = token.length > 0;

        if(valid) {
            c = token[0];
            valid = charIsLetter(c);

            for (i = 0d; i < token.length && valid; i = i + 1d) {
                c = token[(int) i];
                if (charIsLetter(c) || charIsNumber(c) || c == '_') {
                    // OK
                }else{
                    valid = false;
                    message.string = ("Invalid identifier: " + new String(token)).toCharArray();
                }
            }
        }

        return valid;
    }

    public static boolean ParseFunction(Function function, Array tokens, NumberReference tokenRef, StringReference message) {
        boolean success;

        success = true;

        return success;
    }
}











