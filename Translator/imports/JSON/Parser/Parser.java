package JSON.Parser;

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


import static JSON.Comparator.Comparator.*;

import static JSON.Writer.Writer.*;

import static JSON.TokenReader.TokenReader.*;

import static JSON.Validator.Validator.*;

import static JSON.LengthComputer.LengthComputer.*;

public class Parser{
	public static boolean ReadJSON(char [] string, DataReference dataReference, StringReference message){
		StringArrayReference tokenArrayReference;
		boolean success;

		/* Tokenize.*/
		tokenArrayReference = new StringArrayReference();
		success = JSONTokenize(string, tokenArrayReference, message);

		if(success){
			/* Parse.*/
			success = GetJSONValue(tokenArrayReference.stringArray, dataReference, message);
		}

		return success;
	}

	public static boolean GetJSONValue(StringReference [] tokens, DataReference dataReference, StringReference message){
		NumberReference i;
		boolean success;

		i = CreateNumberReference(0d);
		success = GetJSONValueRecursive(tokens, i, 0d, dataReference, message);

		return success;
	}

	public static boolean GetJSONValueRecursive(StringReference [] tokens, NumberReference i, double depth, DataReference dataReference, StringReference message){
		char [] str, substr, token;
		double stringToDecimalResult;
		boolean success;

		success = true;
		token = tokens[(int)(i.numberValue)].string;

		if(StringsEqual(token, "{".toCharArray())){
			success = GetJSONObject(tokens, i, depth + 1d, dataReference, message);
		}else if(StringsEqual(token, "[".toCharArray())){
			success = GetJSONArray(tokens, i, depth + 1d, dataReference, message);
		}else if(StringsEqual(token, "true".toCharArray())){
			dataReference.data = CreateBooleanData(true);
			i.numberValue = i.numberValue + 1d;
		}else if(StringsEqual(token, "false".toCharArray())){
			dataReference.data = CreateBooleanData(false);
			i.numberValue = i.numberValue + 1d;
		}else if(StringsEqual(token, "null".toCharArray())){
			dataReference.data = CreateNoTypeData();
			i.numberValue = i.numberValue + 1d;
		}else if(charIsNumber(token[0]) || token[0] == '-'){
			stringToDecimalResult = nCreateNumberFromDecimalString(token);
			dataReference.data = CreateNumberData(stringToDecimalResult);
			i.numberValue = i.numberValue + 1d;
		}else if(token[0] == '\"'){
			substr = Substring(token, 1d, token.length - 1d);
			dataReference.data = CreateStringData(substr);
			i.numberValue = i.numberValue + 1d;
		}else{
			str = "".toCharArray();
			str = ConcatenateString(str, "Invalid token first in value: ".toCharArray());
			str = AppendString(str, token);
			message.string = str;
			success = false;
		}

		if(success && depth == 0d){
			if(StringsEqual(tokens[(int)(i.numberValue)].string, "<end>".toCharArray())){
			}else{
				message.string = "The outer value cannot have any tokens following it.".toCharArray();
				success = false;
			}
		}

		return success;
	}

	public static boolean GetJSONObject(StringReference [] tokens, NumberReference i, double depth, DataReference dataReference, StringReference message){
		Data data, value;
		boolean done, success;
		char [] key, colon, comma, closeCurly;
		char [] keystring, str;
		DataReference valueReference;

		data = CreateStructData();
		valueReference = new DataReference();
		success = true;
		i.numberValue = i.numberValue + 1d;

		if(!StringsEqual(tokens[(int)(i.numberValue)].string, "}".toCharArray())){
			done = false;

			for(; !done && success; ){
				key = tokens[(int)(i.numberValue)].string;

				if(key[0] == '\"'){
					i.numberValue = i.numberValue + 1d;
					colon = tokens[(int)(i.numberValue)].string;
					if(StringsEqual(colon, ":".toCharArray())){
						i.numberValue = i.numberValue + 1d;
						success = GetJSONValueRecursive(tokens, i, depth, valueReference, message);

						if(success){
							keystring = Substring(key, 1d, key.length - 1d);
							value = valueReference.data;

							AddDataToStruct(data.structure, keystring, value);

							comma = tokens[(int)(i.numberValue)].string;
							if(StringsEqual(comma, ",".toCharArray())){
								/* OK*/
								i.numberValue = i.numberValue + 1d;
							}else{
								done = true;
							}
						}
					}else{
						str = "".toCharArray();
						str = ConcatenateString(str, "Expected colon after key in object: ".toCharArray());
						str = AppendString(str, colon);
						message.string = str;

						success = false;
						done = true;
					}
				}else{
					message.string = "Expected string as key in object.".toCharArray();

					done = true;
					success = false;
				}
			}
		}

		if(success){
			closeCurly = tokens[(int)(i.numberValue)].string;

			if(StringsEqual(closeCurly, "}".toCharArray())){
				/* OK*/
				dataReference.data = data;
				i.numberValue = i.numberValue + 1d;
			}else{
				message.string = "Expected close curly brackets at end of object value.".toCharArray();
				success = false;
			}
		}

		delete(valueReference);

		return success;
	}

	public static boolean GetJSONArray(StringReference [] tokens, NumberReference i, double depth, DataReference dataReference, StringReference message){
		Data data, value;
		char [] nextToken, comma;
		boolean done, success;
		DataReference valueReference;

		i.numberValue = i.numberValue + 1d;

		valueReference = new DataReference();
		success = true;
		data = CreateArrayData();

		nextToken = tokens[(int)(i.numberValue)].string;

		if(!StringsEqual(nextToken, "]".toCharArray())){
			done = false;
			for(; !done && success; ){
				success = GetJSONValueRecursive(tokens, i, depth, valueReference, message);

				if(success){
					value = valueReference.data;
					AddDataToArray(data.array, value);

					comma = tokens[(int)(i.numberValue)].string;

					if(StringsEqual(comma, ",".toCharArray())){
						/* OK*/
						i.numberValue = i.numberValue + 1d;
					}else{
						done = true;
					}
				}
			}
		}

		nextToken = tokens[(int)(i.numberValue)].string;
		if(StringsEqual(nextToken, "]".toCharArray())){
			/* OK*/
			i.numberValue = i.numberValue + 1d;
		}else{
			message.string = "Expected close square bracket at end of array.".toCharArray();
			success = false;
		}

		dataReference.data = data;
		delete(valueReference);

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
