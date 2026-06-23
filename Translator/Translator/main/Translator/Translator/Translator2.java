package Translator.Translator;

import DataStructures.Array.Structures.Array;

import static DataStructures.Array.Arrays.Arrays.ArrayAddString;
import static DataStructures.Array.Arrays.Arrays.CreateArray;

public class Translator2 {
    public static Array Tokenize(char[] input) {
        Array tokens, ins;

        tokens = CreateArray();
        ins = GetInstructionTokens();

        return tokens;
    }

    public static Array GetInstructionTokens() {
        Array ins;

        ins = CreateArray();

        // Basics
        ArrayAddString(ins, "Bgs".toCharArray());
        ArrayAddString(ins, "Ens".toCharArray());
        ArrayAddString(ins, "Fnc".toCharArray());
        ArrayAddString(ins, "Ret".toCharArray());

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
}
