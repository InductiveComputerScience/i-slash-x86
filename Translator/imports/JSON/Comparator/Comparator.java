package JSON.Comparator;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import static nnumbers.NumberToString.NumberToString.*;

import static nnumbers.StringToNumber.StringToNumber.*;

import static math.math.math.*;

import static arrays.arrays.arrays.*;

import static charCharacters.Characters.Characters.*;

import DataStructures.Array.Structures.*;
import static DataStructures.Array.Structures.Structures.*;

import static DataStructures.Array.Arrays.Arrays.*;


import static JSON.Writer.Writer.*;

import static JSON.Parser.Parser.*;

import static JSON.TokenReader.TokenReader.*;

import static JSON.Validator.Validator.*;

import static JSON.LengthComputer.LengthComputer.*;

public class Comparator{
	public static boolean JSONCompare(char [] a, char [] b, double epsilon, BooleanReference equal, StringReference message){
		boolean success;
		DataReference eaRef, ebRef;
		Data ea, eb;

		eaRef = new DataReference();
		ebRef = new DataReference();

		success = ReadJSON(a, eaRef, message);

		if(success){
			ea = eaRef.data;

			success = ReadJSON(b, ebRef, message);

			if(success){
				eb = ebRef.data;

				equal.booleanValue = JSONCompareElements(ea, eb, epsilon);

				FreeData(eb);
			}

			FreeData(ea);
		}

		return success;
	}

	public static boolean JSONCompareElements(Data ea, Data eb, double epsilon){
		boolean equal;

		equal = DataTypeEquals(ea, eb);
        
		if(equal){
			if(IsStructure(ea)){
				equal = JSONCompareObjects(ea.structure, eb.structure, epsilon);
			}else if(IsString(ea)){
				equal = StringsEqual(ea.string, eb.string);
			}else if(IsArray(ea)){
				equal = JSONCompareArrays(ea.array, eb.array, epsilon);
			}else if(IsNumber(ea)){
				equal = EpsilonCompare(ea.number, eb.number, epsilon);
			}else if(IsNoType(ea)){
				equal = true;
			}else if(IsBoolean(ea)){
				equal = ea.booleanx == eb.booleanx;
			}
		}
        
		return equal;
	}

	public static boolean JSONCompareArrays(Array ea, Array eb, double epsilon){
		boolean equals;
		double i, length;

		equals = ea.length == eb.length;

		if(equals){
			length = ea.length;
			for(i = 0d; i < length && equals; i = i + 1d){
				equals = JSONCompareElements(ArrayIndex(ea, i), ArrayIndex(eb, i), epsilon);
			}
		}

		return equals;
	}

	public static boolean JSONCompareObjects(Structure ea, Structure eb, double epsilon){
		boolean equals;
		double akeys, bkeys, i;
		StringReference [] keys;
		char [] key;
		BooleanReference aFoundReference, bFoundReference;
		Data eaValue, ebValue;

		aFoundReference = new BooleanReference();
		bFoundReference = new BooleanReference();

		akeys = StructKeys(ea);
		bkeys = StructKeys(eb);

		equals = akeys == bkeys;

		if(equals){
			keys = GetStructKeys(ea);

			for(i = 0d; i < keys.length && equals; i = i + 1d){
				key = keys[(int)(i)].string;

				eaValue = GetDataFromStructWithCheck(ea, key, aFoundReference);
				ebValue = GetDataFromStructWithCheck(eb, key, bFoundReference);

				if(aFoundReference.booleanValue && bFoundReference.booleanValue){
					equals = JSONCompareElements(eaValue, ebValue, epsilon);
				}else{
					equals = false;
				}
			}

			FreeStringReferenceArray(keys);
		}

		return equals;
	}

	public static void FreeStringReferenceArray(StringReference [] strings){
		double i;

		for(i = 0d; i < strings.length; i = i + 1d){
			delete(strings[(int)(i)]);
		}
		delete(strings);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
