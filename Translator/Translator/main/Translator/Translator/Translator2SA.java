package Translator.Translator;

import DataStructures.Array.Structures.Array;
import references.references.StringReference;

import static DataStructures.Array.Arrays.Arrays.*;
import static Translator.Translator.Translator2.StringIsInArray;
import static arrays.arrays.arrays.StringsEqual;

public class Translator2SA {
    public static boolean StaticAnalysis(Ast ast, StringReference message) {
        boolean success;
        double i, j, k;
        Function fnc;

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
                fnc = ast.fnc[(int) i];
                success = CheckExistenceOfFunctionStructure(fnc, ast.st, message);
            }
        }

        if(success){
            for(i = 0; i < ast.fnc.length && success; i = i + 1d) {
                fnc = ast.fnc[(int) i];

                for(j = 0; j < fnc.ins.length && success; j = j + 1d) {
                    Instruction ins = fnc.ins[(int) j];

                    for(k = 0; k < ins.params.length && success; k = k + 1d) {
                        Param param = ins.params[(int) k];

                        if(StringsEqual(param.type, "var".toCharArray())) {
                            success = CheckThatVariableIsDeclared(param, fnc.functionStructure, message);
                        }
                    }
                }
            }
        }

        if(success){
            // Check parameter count
            for(i = 0; i < ast.fnc.length && success; i = i + 1d) {
                fnc = ast.fnc[(int) i];

                for(j = 0; j < fnc.ins.length && success; j = j + 1d) {
                    Instruction ins = fnc.ins[(int) j];

                    success = CheckParameterCount(ins, message);
                }
            }
        }

        if(success){
            // Check parameter types
            for(i = 0; i < ast.fnc.length && success; i = i + 1d) {
                fnc = ast.fnc[(int) i];

                for(j = 0; j < fnc.ins.length && success; j = j + 1d) {
                    Instruction ins = fnc.ins[(int) j];

                    success = CheckParameterTypes(ins, message);
                }
            }
        }

        if(success){
            // Check control flow and compute labels
            // TODO
        }

        return success;
    }

    public static boolean CheckParameterCount(Instruction ins, StringReference message) {
        boolean valid, found;
        Array p0, p1, p2, p3, p4, p5;
        Array [] ps;
        double i, j;

        valid = false;

        p0 = CreateArray();

        ArrayAddString(p0, "Loop".toCharArray());
        ArrayAddString(p0, "EndLoop".toCharArray());
        ArrayAddString(p0, "Endb".toCharArray());
        ArrayAddString(p0, "Else".toCharArray());
        ArrayAddString(p0, "Nop".toCharArray());

        p1 = CreateArray();

        ArrayAddString(p1, "Inc".toCharArray());
        ArrayAddString(p1, "Dec".toCharArray());
        ArrayAddString(p1, "Del".toCharArray());
        ArrayAddString(p1, "Rdrand".toCharArray());
        ArrayAddString(p1, "Rdtsc".toCharArray());
        ArrayAddString(p1, "If".toCharArray());

        p2 = CreateArray();

        ArrayAddString(p2, "Round".toCharArray());
        ArrayAddString(p2, "Floor".toCharArray());
        ArrayAddString(p2, "Ceil".toCharArray());
        ArrayAddString(p2, "Truncate".toCharArray());
        ArrayAddString(p2, "Abs".toCharArray());
        ArrayAddString(p2, "Mov".toCharArray());
        ArrayAddString(p2, "Broadcast".toCharArray());
        ArrayAddString(p2, "Len".toCharArray());
        ArrayAddString(p2, "Not".toCharArray());
        ArrayAddString(p2, "Call".toCharArray());
        ArrayAddString(p2, "New".toCharArray());
        ArrayAddString(p2, "Set".toCharArray());
        ArrayAddString(p2, "Movzx".toCharArray());
        ArrayAddString(p2, "u8tou16".toCharArray());
        ArrayAddString(p2, "u8tou32".toCharArray());
        ArrayAddString(p2, "u16tou32".toCharArray());
        ArrayAddString(p2, "u32tou64".toCharArray());
        ArrayAddString(p2, "u8tos16".toCharArray());
        ArrayAddString(p2, "u16tos32".toCharArray());
        ArrayAddString(p2, "u32tos64".toCharArray());
        ArrayAddString(p2, "s8tos16".toCharArray());
        ArrayAddString(p2, "s16tos32".toCharArray());
        ArrayAddString(p2, "s32tos64".toCharArray());
        ArrayAddString(p2, "u16tof32".toCharArray());
        ArrayAddString(p2, "s16tof32".toCharArray());
        ArrayAddString(p2, "u32tof64".toCharArray());
        ArrayAddString(p2, "s32tof64".toCharArray());
        ArrayAddString(p2, "f32tof64".toCharArray());
        ArrayAddString(p2, "u16tou8".toCharArray());
        ArrayAddString(p2, "u32tou16".toCharArray());
        ArrayAddString(p2, "u64tou32".toCharArray());
        ArrayAddString(p2, "s16tou8".toCharArray());
        ArrayAddString(p2, "s32tou16".toCharArray());
        ArrayAddString(p2, "s64tou32".toCharArray());
        ArrayAddString(p2, "s16tos8".toCharArray());
        ArrayAddString(p2, "s32tos16".toCharArray());
        ArrayAddString(p2, "s64tos32".toCharArray());
        ArrayAddString(p2, "f32tou16".toCharArray());
        ArrayAddString(p2, "f32tos16".toCharArray());
        ArrayAddString(p2, "f64tou32".toCharArray());
        ArrayAddString(p2, "f64tos32".toCharArray());
        ArrayAddString(p2, "f64tof32".toCharArray());
        ArrayAddString(p2, "u8x8tou16x8".toCharArray());
        ArrayAddString(p2, "u8x4tou32x4".toCharArray());
        ArrayAddString(p2, "u8x2tou64x2".toCharArray());
        ArrayAddString(p2, "u16x4tou32x4".toCharArray());
        ArrayAddString(p2, "u32x2tou64x2".toCharArray());
        ArrayAddString(p2, "s8x8tos16x8".toCharArray());
        ArrayAddString(p2, "s8x4tos32x4".toCharArray());
        ArrayAddString(p2, "s64x2tos32x2".toCharArray());
        ArrayAddString(p2, "f64x2tof32x2".toCharArray());
        ArrayAddString(p2, "f32x4tof16x4".toCharArray());
        ArrayAddString(p2, "f64x4tof32x4".toCharArray());
        ArrayAddString(p2, "f32x8tof16x8".toCharArray());
        ArrayAddString(p2, "f32x4tof64x4".toCharArray());
        ArrayAddString(p2, "Xu64".toCharArray());
        ArrayAddString(p2, "Xu8a".toCharArray());
        ArrayAddString(p2, "Xu16a".toCharArray());
        ArrayAddString(p2, "Xu64a".toCharArray());
        ArrayAddString(p2, "Xb8".toCharArray());
        ArrayAddString(p2, "Xb16".toCharArray());
        ArrayAddString(p2, "Xb32".toCharArray());
        ArrayAddString(p2, "Xb64".toCharArray());
        ArrayAddString(p2, "Xb128".toCharArray());
        ArrayAddString(p2, "Xb256".toCharArray());
        ArrayAddString(p2, "Xu32x4".toCharArray());
        ArrayAddString(p2, "Xu16x8a".toCharArray());
        ArrayAddString(p2, "Xu8x16a".toCharArray());
        ArrayAddString(p2, "Xu16x8a".toCharArray());
        ArrayAddString(p2, "Xu16x16a".toCharArray());
        ArrayAddString(p2, "Xu16x16a".toCharArray());
        ArrayAddString(p2, "Xu16x32a".toCharArray());
        ArrayAddString(p2, "u32tou16s".toCharArray());
        ArrayAddString(p2, "s32tos16s".toCharArray());
        ArrayAddString(p2, "Xchg".toCharArray());
        ArrayAddString(p2, "Xadd".toCharArray());
        ArrayAddString(p2, "Bswap".toCharArray());
        ArrayAddString(p2, "Bsf".toCharArray());
        ArrayAddString(p2, "Bsr".toCharArray());
        ArrayAddString(p2, "Crc32".toCharArray());
        ArrayAddString(p2, "Popcnt".toCharArray());
        ArrayAddString(p2, "Lzcnt".toCharArray());
        ArrayAddString(p2, "Tzcnt".toCharArray());
        ArrayAddString(p2, "Rdpmc".toCharArray());
        ArrayAddString(p2, "Blsi".toCharArray());
        ArrayAddString(p2, "Blsmsk".toCharArray());
        ArrayAddString(p2, "Blsr".toCharArray());
        ArrayAddString(p2, "ExtractMask".toCharArray());
        ArrayAddString(p2, "Sqrt".toCharArray());
        ArrayAddString(p2, "ApproxReciprocal".toCharArray());
        ArrayAddString(p2, "ApproxReciprocalSqrt".toCharArray());
        ArrayAddString(p2, "MovAndDuplicate".toCharArray());
        ArrayAddString(p2, "MovAndDuplicateOdd".toCharArray());

        p3 = CreateArray();
        ArrayAddString(p3, "Add".toCharArray());
        ArrayAddString(p3, "Sub".toCharArray());
        ArrayAddString(p3, "Mul".toCharArray());
        ArrayAddString(p3, "MulFull".toCharArray());
        ArrayAddString(p3, "Div".toCharArray());
        ArrayAddString(p3, "DivFull".toCharArray());
        ArrayAddString(p3, "Mod".toCharArray());
        ArrayAddString(p3, "AddSaturated".toCharArray());
        ArrayAddString(p3, "SubSaturated".toCharArray());
        ArrayAddString(p3, "Lt".toCharArray());
        ArrayAddString(p3, "LessThan".toCharArray());
        ArrayAddString(p3, "Lte".toCharArray());
        ArrayAddString(p3, "Gt".toCharArray());
        ArrayAddString(p3, "Gte".toCharArray());
        ArrayAddString(p3, "And".toCharArray());
        ArrayAddString(p3, "Andnot".toCharArray());
        ArrayAddString(p3, "Idr".toCharArray());
        ArrayAddString(p3, "Eq".toCharArray());
        ArrayAddString(p3, "Neq".toCharArray());
        ArrayAddString(p3, "Unequal".toCharArray());
        ArrayAddString(p3, "Acw".toCharArray());
        ArrayAddString(p3, "Acr".toCharArray());
        ArrayAddString(p3, "Shl".toCharArray());
        ArrayAddString(p3, "Shr".toCharArray());
        ArrayAddString(p3, "Sar".toCharArray());
        ArrayAddString(p3, "Or".toCharArray());
        ArrayAddString(p3, "Xor".toCharArray());
        ArrayAddString(p3, "Idw".toCharArray());
        ArrayAddString(p3, "Ror".toCharArray());
        ArrayAddString(p3, "Cmov".toCharArray());
        ArrayAddString(p3, "Rol".toCharArray());
        ArrayAddString(p3, "Bt".toCharArray());
        ArrayAddString(p3, "Movs".toCharArray());
        ArrayAddString(p3, "Bzhi".toCharArray());
        ArrayAddString(p3, "Pdep".toCharArray());
        ArrayAddString(p3, "Pext".toCharArray());
        ArrayAddString(p3, "Shuffle".toCharArray());
        ArrayAddString(p3, "MulStoreHigh".toCharArray());
        ArrayAddString(p3, "Avg".toCharArray());
        ArrayAddString(p3, "Min".toCharArray());
        ArrayAddString(p3, "Max".toCharArray());
        ArrayAddString(p3, "InterleaveLow".toCharArray());
        ArrayAddString(p3, "InterleaveHigh".toCharArray());
        ArrayAddString(p3, "SumAbsoluteDifference".toCharArray());
        ArrayAddString(p3, "PairwiseAdd".toCharArray());
        ArrayAddString(p3, "PairwiseSub".toCharArray());
        ArrayAddString(p3, "PairwiseMulAdd".toCharArray());
        ArrayAddString(p3, "AlternatingSubAdd".toCharArray());
        ArrayAddString(p3, "ConditionalNegate".toCharArray());

        p4 = CreateArray();
        ArrayAddString(p4, "DivMod".toCharArray());
        ArrayAddString(p4, "MulDiv".toCharArray());
        ArrayAddString(p4, "Idro".toCharArray());
        ArrayAddString(p4, "Cmoveq".toCharArray());
        ArrayAddString(p4, "Cmps".toCharArray());
        ArrayAddString(p4, "Scas".toCharArray());
        ArrayAddString(p4, "Bextr".toCharArray());
        ArrayAddString(p4, "SelectiveCopy".toCharArray());
        ArrayAddString(p4, "MultiplyAndAdd".toCharArray());
        ArrayAddString(p4, "CombineExtract".toCharArray());
        ArrayAddString(p4, "Gather".toCharArray());
        ArrayAddString(p4, "StringSubset".toCharArray());
        ArrayAddString(p4, "StringRangeCheck".toCharArray());
        ArrayAddString(p4, "MatchString".toCharArray());
        ArrayAddString(p4, "FindSubstring".toCharArray());
        ArrayAddString(p4, "FindSubstring".toCharArray());

        p5 = CreateArray();
        ArrayAddString(p5, "DotProduct".toCharArray());

        ps = new Array[6];
        ps[0] = p0;
        ps[1] = p1;
        ps[2] = p2;
        ps[3] = p3;
        ps[4] = p4;
        ps[5] = p5;

        found = false;
        for(i = 0d; i < ps.length; i = i + 1d){
            Array p = ps[(int) i];
            for(j = 0d; j < ArrayLength(p); j = j + 1d){
                if(StringsEqual(ins.name, ArrayIndexString(p, j))){
                    if(ins.params.length == i) {
                        valid = true;
                        found = true;
                    }else{
                        valid = false;
                        message.string = ("Wrong parameter count for instruction: " + new String(ins.name)).toCharArray();
                    }
                }
            }
        }

        if(found){
        }else{
            message.string = ("Parameter count of instruction unknown: " + new String(ins.name)).toCharArray();
        }

        return valid;
    }

    public static boolean CheckThatVariableIsDeclared(Param param, Struct fncSt, StringReference message) {
        double i;
        boolean found;

        found = false;

        for(i = 0d; i < fncSt.vars.length; i = i + 1d){
            Var var = fncSt.vars[(int) i];

            if(StringsEqual(param.varname, var.name)){
                found = true;
                param.var = var;
            }
        }

        if(found){

        }else{
            message.string = ("Variable not declared: " + new String(param.varname)).toCharArray();
        }

        return found;
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

    public static boolean CheckParameterTypes(Instruction ins, StringReference message) {
        boolean valid;
        char [] targetType, memoryPostfix;
        Array sameAsAssigneeNumber, numbersToBits;
        double i;

        valid = true;

        sameAsAssigneeNumber = CreateArray();

        // All parameters are the same, all are numbers. The type of the assigned var is the type of the instruction.
        ArrayAddString(sameAsAssigneeNumber, "Add".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Sub".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Mul".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Div".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "DivMod".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Mod".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "MulDiv".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "AddSaturated".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "SubSaturated".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Round".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Floor".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Ceil".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Truncate".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Abs".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Inc".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Dec".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "MulStoreHigh".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "MultiplyAndAdd".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Avg".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Min".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Max".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "Sqrt".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "ApproxReciprocal".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "ApproxReciprocalSqrt".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "SumAbsoluteDifference".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "PairwiseAdd".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "PairwiseSub".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "PairwiseMulAdd".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "AlternatingSubAdd".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "ConditionalNegate".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "MovAndDuplicate".toCharArray());
        ArrayAddString(sameAsAssigneeNumber, "MovAndDuplicateOdd".toCharArray());

        // These return a boolean. The type is the type of one of the: b <- num or bw <- num. If both are immediates, use f64
        numbersToBits = CreateArray();
        ArrayAddString(numbersToBits, "Lt".toCharArray());
        ArrayAddString(numbersToBits, "Lte".toCharArray());
        ArrayAddString(numbersToBits, "Gt".toCharArray());
        ArrayAddString(numbersToBits, "Gte".toCharArray());
        ArrayAddString(numbersToBits, "Eq".toCharArray());
        ArrayAddString(numbersToBits, "Neq".toCharArray());

        // These take a bitfield and return a number.
        Array bitfieldToNumber = CreateArray();
        ArrayAddString(bitfieldToNumber, "Popcnt".toCharArray());
        ArrayAddString(bitfieldToNumber, "Lzcnt".toCharArray());
        ArrayAddString(bitfieldToNumber, "Tzcnt".toCharArray());
        ArrayAddString(bitfieldToNumber, "Blsi".toCharArray());

        // These are bitfields for all inputs
        Array sameAsAssigneeBitfields = CreateArray();
        ArrayAddString(sameAsAssigneeBitfields, "Not".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "And".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "Andnot".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "Or".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "Xor".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "Pdep".toCharArray());
        ArrayAddString(sameAsAssigneeBitfields, "Pext".toCharArray());

        // bw <- bw, number
        Array bitwiseAndNumberToBitwise = CreateArray();
        ArrayAddString(bitwiseAndNumberToBitwise, "Shl".toCharArray());
        ArrayAddString(bitwiseAndNumberToBitwise, "Shr".toCharArray());
        ArrayAddString(bitwiseAndNumberToBitwise, "Sar".toCharArray());
        ArrayAddString(bitwiseAndNumberToBitwise, "Ror".toCharArray());
        ArrayAddString(bitwiseAndNumberToBitwise, "Rol".toCharArray());

        /*

        Other:
        Mov
        Idr
        Idro
        Acw
        Acr
        MulFull
        DivFull
        Call
        New
        Del
        Idw
        Rdrand
        Set
        Movzx
        StringSubset
        StringSubset
        StringRangeCheck
        MatchString
        FindSubstring

        */

        if(StringIsInArray(ins.name, sameAsAssigneeNumber)) {
            Param assigneeType = ins.params[0];
            targetType = assigneeType.var.type;
            char first, last;

            first = targetType[0];
            last = targetType[targetType.length - 1];

            if (first == 's' || first == 'u' || first == 'f') {
                if (last != 'a') {

                } else {
                    valid = false;
                    message.string = "Instruction does not work on arrays.".toCharArray();
                }
            } else {
                valid = false;
                message.string = "Instruction only works on number variables.".toCharArray();
            }

            if (valid) {

                ins.typePostfix = targetType;

                memoryPostfix = new char[0];

                if (ins.params.length >= 1) {
                    memoryPostfix = new char[ins.params.length - 1];

                    for (i = 0; i < ins.params.length - 1d; i = i + 1d) {
                        if (StringsEqual(ins.params[(int) (i + 1)].type, "var".toCharArray())) {
                            memoryPostfix[(int) i] = 'm';
                        } else {
                            memoryPostfix[(int) i] = 'i';
                        }
                    }
                }

                ins.memoryPostfix = memoryPostfix;
            }

        }else if(StringIsInArray(ins.name, sameAsAssigneeBitfields)){
            Param assigneeType = ins.params[0];
            targetType = assigneeType.var.type;
            char first, last;

            first = targetType[0];
            last = targetType[targetType.length - 1];

            if(first == 'b'){
                if(last != 'a'){

                }else{
                    valid = false;
                    message.string = "Instruction does not work on arrays.".toCharArray();
                }
            }else{
                valid = false;
                message.string = "Instruction only works on bitfield variables.".toCharArray();
            }

            if(valid) {

                ins.typePostfix = targetType;

                memoryPostfix = new char[0];

                if (ins.params.length >= 1) {
                    memoryPostfix = new char[ins.params.length - 1];

                    for (i = 0; i < ins.params.length - 1d; i = i + 1d) {
                        if (StringsEqual(ins.params[(int) (i + 1)].type, "var".toCharArray())) {
                            memoryPostfix[(int) i] = 'm';
                        } else {
                            memoryPostfix[(int) i] = 'i';
                        }
                    }
                }

                ins.memoryPostfix = memoryPostfix;
            }

        }else{
            //valid = false;
        }

        return valid;
    }

}
