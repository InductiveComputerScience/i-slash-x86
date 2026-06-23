package JSON.TypeMatcher;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import static nnumbers.NumberToString.NumberToString.*;

import static nnumbers.NumberComputations.NumberComputations.*;

import static nnumbers.StringToNumber.StringToNumber.*;

import static math.math.math.*;

import static math.Decimal15E2.Decimal15E2.*;

import static arrays.arrays.arrays.*;

import static charCharacters.Characters.Characters.*;

import DataStructures.Array.Structures.*;
import static DataStructures.Array.Structures.Structures.*;

import static DataStructures.Array.Arrays.Arrays.*;

import static lists.NumberList.NumberList.*;

import static lists.StringList.StringList.*;

import lists.DynamicArrayCharacters.Structures.*;

import static lists.DynamicArrayCharacters.DynamicArrayCharactersFunctions.DynamicArrayCharactersFunctions.*;

import static lists.BooleanList.BooleanList.*;

import lists.LinkedListStrings.Structures.*;

import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.*;

import lists.LinkedListNumbers.Structures.*;

import static lists.LinkedListNumbers.LinkedListNumbersFunctions.LinkedListNumbersFunctions.*;

import static lists.LinkedListCharacters.LinkedListCharactersFunctions.LinkedListCharactersFunctions.*;

import lists.LinkedListCharacters.Structures.*;

import lists.DynamicArrayNumbers.Structures.*;

import static lists.DynamicArrayNumbers.DynamicArrayNumbersFunctions.DynamicArrayNumbersFunctions.*;

import static lists.CharacterList.CharacterList.*;


import static JSON.Comparator.Comparator.*;

import JSON.Writer.*;
import static JSON.Writer.Writer.*;

import static JSON.Parser.Parser.*;

import static JSON.TokenReader.TokenReader.*;

import static JSON.Validator.Validator.*;

import static JSON.LengthComputer.LengthComputer.*;

public class TypeMatcher{
	public static boolean MatchTypesWithStrings(char [] inputJSON, char [] typesJSON, BooleanReference matchRef, StringReference message){
		boolean success;
		DataReference inputRef;

		inputRef = new DataReference();

		success = ReadJSON(inputJSON, inputRef, message);
		if(success){
			success = MatchTypesString(inputRef.data, typesJSON, matchRef, message);
		}

		return success;
	}

	public static boolean MatchTypesString(Data input, char [] typesJSON, BooleanReference matchRef, StringReference message){
		boolean success;
		DataReference typesRef;

		typesRef = new DataReference();

		success = ReadJSON(typesJSON, typesRef, message);
		if(success){
			success = MatchTypes(input, typesRef.data, matchRef, message);
		}

		return success;
	}

	public static boolean MatchTypes(Data input, Data types, BooleanReference matchRef, StringReference message){
		boolean match, success;
		double i;
		Data element, type;
		Structure inputStruct, typeStruct;
		StringReference [] typeKeys;
		char [] key;

		success = true;
		match = true;

		if(IsNumber(input) && IsNumber(types)){
		}else if(IsBoolean(input) && IsBoolean(types)){
		}else if(IsString(input) && IsString(types)){
		}else if(IsNoType(input) && IsNoType(types)){
		}else if(IsStructure(input) && IsStructure(types)){
			/* The same keys must be present in both, and the values must have the correct types.*/
			inputStruct = input.structure;
			typeStruct = types.structure;

			if(StructKeys(inputStruct) == StructKeys(typeStruct)){
				typeKeys = GetStructKeys(typeStruct);

				for(i = 0d; i < typeKeys.length && success && match; i = i + 1d){
					key = typeKeys[(int)(i)].string;
					if(StructHasKey(inputStruct, key)){
						element = GetDataFromStruct(inputStruct, key);
						type = GetDataFromStruct(typeStruct, key);

						success = MatchTypes(element, type, matchRef, message);
						if(success){
							match = matchRef.booleanValue;
						}
					}else{
						match = false;
					}
				}
			}else{
				match = false;
			}
		}else if(IsArray(input) && IsArray(types)){
			/* Each element of the array must match the first element of the types.*/
			if(ArrayLength(input.array) == 0d){
				/* OK*/
				match = true;
			}else{
				match = true;
				if(ArrayLength(types.array) == 1d){
					type = ArrayIndex(types.array, 0d);

					for(i = 0d; i < ArrayLength(input.array) && success && match; i = i + 1d){
						element = ArrayIndex(input.array, i);
						success = MatchTypes(element, type, matchRef, message);
						if(success){
							match = matchRef.booleanValue;
						}
					}
				}else{
					success = false;
					message.string = "Invalid type specification, array must be zero or one long.".toCharArray();
				}
			}
		}else{
			match = false;
		}

		if(success){
			matchRef.booleanValue = match;
		}

		return success;
	}

	public static boolean NumberExistsAndIsPositive(Structure st, char [] key){
		boolean valid;
		Data data;
		double n;

		if(StructHasKey(st, key)){
			data = GetDataFromStruct(st, key);
			if(IsNumber(data)){
				n = data.number;
				if(n >= 0d){
					valid = true;
				}else{
					valid = false;
				}
			}else{
				valid = false;
			}
		}else{
			valid = false;
		}

		return valid;
	}

	public static boolean StringExistsAndIsFromList(Structure st, char [] key, StringReference [] list){
		boolean valid;
		Data data;
		char [] str, cmp;
		double i;

		if(StructHasKey(st, key)){
			data = GetDataFromStruct(st, key);
			if(IsString(data)){
				str = data.string;
				valid = false;
				for(i = 0d; i < list.length && !valid; i = i + 1d){
					cmp = list[(int)(i)].string;
					if(StringsEqual(str, cmp)){
						valid = true;
					}
				}
			}else{
				valid = false;
			}
		}else{
			valid = false;
		}

		return valid;
	}

	public static boolean GetNumberFromStructPath(Structure st, char [] path, NumberReference numberRef, StringReference message){
		boolean success;
		DataReference dataRef;

		dataRef = new DataReference();

		success = GetDataFromPath(CreateStructData(st), path, dataRef, message);

		if(success){
			numberRef.numberValue = dataRef.data.number;
		}

		return success;
	}

	public static boolean GetBooleanFromStructPath(Structure st, char [] path, BooleanReference booleanRef, StringReference message){
		boolean success;
		DataReference dataRef;

		dataRef = new DataReference();

		success = GetDataFromPath(CreateStructData(st), path, dataRef, message);

		if(success){
			booleanRef.booleanValue = dataRef.data.booleanx;
		}

		return success;
	}

	public static boolean GetStringFromStructPath(Structure st, char [] path, StringReference stringRef, StringReference message){
		boolean success;
		DataReference dataRef;

		dataRef = new DataReference();

		success = GetDataFromPath(CreateStructData(st), path, dataRef, message);

		if(success){
			stringRef.string = dataRef.data.string;
		}

		return success;
	}

	public static boolean GetDataFromPath(Data data, char [] path, DataReference dataRef, StringReference message){
		Array keys;
		boolean success;
		DataReference keysRef;
		double i;
		Structure s;
		Data n;
		char [] key;

		keysRef = new DataReference();

		success = ParsePath(path, keysRef, message);

		if(success){
			keys = keysRef.data.array;

			n = data;

			for(i = 0d; i < ArrayLength(keys) && success; i = i + 1d){
				key = ArrayIndexString(keys, i);

				if(IsStructure(n)){
					s = n.structure;

					if(StructHasKey(s, key)){
						n = GetDataFromStruct(s, key);
					}else{
						success = false;
						message.string = "Structure does not have key.".toCharArray();
					}
				}else{
					success = false;
					message.string = "Accessed element is not a structure.".toCharArray();
				}
			}

			if(success){
				dataRef.data = n;
			}
		}

		return success;
	}

	public static boolean ParsePath(char [] path, DataReference keysRef, StringReference message){
		double i, state;
		LinkedListCharacters str;
		char c;
		Array keys;
		char [] next;
		boolean success;

		success = true;
		keys = CreateArray();
		str = CreateLinkedListCharacter();

		state = 0d;
		for(i = 0d; i <= path.length && success; i = i + 1d){
			if(i < path.length){
				c = IndexString(path, i);
			}else{
				c = '.';
			}

			if(state == 0d){
				if(c == '\\'){
					state = 1d;
				}else if(c == '.'){
					next = LinkedListCharactersToArray(str);
					if(next.length > 0d){
						AddStringToArray(keys, next);
						str = CreateLinkedListCharacter();
					}else{
						success = false;
						message.string = "Key cannot be empty.".toCharArray();
					}
				}else{
					LinkedListAddCharacter(str, c);
				}
			}else if(state == 1d){
				if(c == '\\'){
					state = 0d;
					LinkedListAddCharacter(str, c);
				}else if(c == '.'){
					state = 0d;
					LinkedListAddCharacter(str, c);
				}else{
					success = false;
					message.string = "Only backslash and dot can be escaped.".toCharArray();
				}
			}
		}

		if(success){
			keysRef.data = CreateArrayData(keys);
		}

		return success;
	}

	public static boolean LastIndexOfString(char [] str, double i){
		return i + 1d == str.length;
	}

	public static char IndexString(char [] str, double i){
		return str[(int)(i)];
	}

	public static boolean CollectStringsFromPath(Data data, char [] outerPath, char [] innerPath, StringArrayReference stringsRef, StringReference message){
		boolean success;
		DataReference dataRef;
		Array array;
		double i;
		Data e;

		dataRef = new DataReference();

		success = CollectDataFromPath(data, outerPath, innerPath, dataRef, message);

		if(success){
			array = dataRef.data.array;
			stringsRef.stringArray = new StringReference [(int)(ArrayLength(array))];

			for(i = 0d; i < ArrayLength(array) && success; i = i + 1d){
				e = ArrayIndex(array, i);
				if(IsString(e)){
					stringsRef.stringArray[(int)(i)] = new StringReference();
					stringsRef.stringArray[(int)(i)].string = CopyString(e.string);
				}else{
					success = false;
					message.string = "Element is not a string.".toCharArray();
				}
			}
		}

		return success;
	}

	public static boolean CollectBooleansFromPath(Data data, char [] outerPath, char [] innerPath, BooleanArrayReference boolsRef, StringReference message){
		boolean success;
		DataReference dataRef;
		Array array;
		double i;
		Data e;

		dataRef = new DataReference();

		success = CollectDataFromPath(data, outerPath, innerPath, dataRef, message);

		if(success){
			array = dataRef.data.array;
			boolsRef.booleanArray = new boolean [(int)(ArrayLength(array))];

			for(i = 0d; i < ArrayLength(array) && success; i = i + 1d){
				e = ArrayIndex(array, i);
				if(IsBoolean(e)){
					boolsRef.booleanArray[(int)(i)] = e.isBoolean;
				}else{
					success = false;
					message.string = "Element is not a boolean.".toCharArray();
				}
			}
		}

		return success;
	}

	public static boolean CollectNumbersFromPath(Data data, char [] outerPath, char [] innerPath, NumberArrayReference numbersRef, StringReference message){
		boolean success;
		DataReference dataRef;
		Array array;
		double i;
		Data e;

		dataRef = new DataReference();

		success = CollectDataFromPath(data, outerPath, innerPath, dataRef, message);

		if(success){
			array = dataRef.data.array;
			numbersRef.numberArray = new double [(int)(ArrayLength(array))];

			for(i = 0d; i < ArrayLength(array) && success; i = i + 1d){
				e = ArrayIndex(array, i);
				if(IsNumber(e)){
					numbersRef.numberArray[(int)(i)] = e.number;
				}else{
					success = false;
					message.string = "Element is not a number.".toCharArray();
				}
			}
		}

		return success;
	}

	public static boolean CollectDataFromPath(Data data, char [] outerPath, char [] innerPath, DataReference dataRef, StringReference message){
		boolean success;
		DataReference outerData, innerDataRef;
		Array elements, result;
		double i;
		Data e;

		outerData = new DataReference();
		innerDataRef = new DataReference();
		result = CreateArray();

		success = GetDataFromPath(data, outerPath, outerData, message);

		if(success){
			if(IsArray(outerData.data)){
				elements = outerData.data.array;

				for(i = 0d; i < ArrayLength(elements) && success; i = i + 1d){
					e = ArrayIndex(elements, i);
					success = GetDataFromPath(e, innerPath, innerDataRef, message);

					if(success){
						AddDataToArray(result, innerDataRef.data);
					}
				}
			}else{
				success = false;
				message.string = "Element to collect from is not array.".toCharArray();
			}
		}

		if(success){
			dataRef.data = CreateArrayData(result);
		}

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
