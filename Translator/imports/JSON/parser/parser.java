package JSON.parser;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import static nnumbers.NumberToString.NumberToString.*;

import static nnumbers.StringToNumber.StringToNumber.*;

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

import static math.math.math.*;

import static arrays.arrays.arrays.*;

import static charCharacters.Characters.Characters.*;


import static JSON.validator.validator.*;

import static JSON.tokenReader.tokenReader.*;

import JSON.ElementLists.*;
import static JSON.ElementLists.ElementLists.*;

import JSON.LinkedListElements.Structures.*;

import static JSON.LinkedListElements.LinkedListElementsFunctions.LinkedListElementsFunctions.*;

import JSON.structures.*;

import static JSON.lengthComputer.lengthComputer.*;

import static JSON.json.json.*;

import static JSON.writer.writer.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

import static JSON.comparator.comparator.*;

public class parser{
	public static boolean ReadJSON(char [] string, ElementReference elementReference, StringArrayReference errorMessages){
		StringArrayReference tokenArrayReference;
		boolean success;

		/* Tokenize.*/
		tokenArrayReference = new StringArrayReference();
		success = JSONTokenize(string, tokenArrayReference, errorMessages);

		if(success){
			/* Parse.*/
			success = GetJSONValue(tokenArrayReference.stringArray, elementReference, errorMessages);
		}

		return success;
	}

	public static boolean GetJSONValue(StringReference [] tokens, ElementReference elementReference, StringArrayReference errorMessages){
		NumberReference i;
		boolean success;

		i = CreateNumberReference(0d);
		success = GetJSONValueRecursive(tokens, i, 0d, elementReference, errorMessages);

		return success;
	}

	public static boolean GetJSONValueRecursive(StringReference [] tokens, NumberReference i, double depth, ElementReference elementReference, StringArrayReference errorMessages){
		char [] str, substr, token;
		double stringToDecimalResult;
		boolean success;

		success = true;
		token = tokens[(int)(i.numberValue)].string;

		if(StringsEqual(token, "{".toCharArray())){
			success = GetJSONObject(tokens, i, depth + 1d, elementReference, errorMessages);
		}else if(StringsEqual(token, "[".toCharArray())){
			success = GetJSONArray(tokens, i, depth + 1d, elementReference, errorMessages);
		}else if(StringsEqual(token, "true".toCharArray())){
			elementReference.element = CreateBooleanElement(true);
			i.numberValue = i.numberValue + 1d;
		}else if(StringsEqual(token, "false".toCharArray())){
			elementReference.element = CreateBooleanElement(false);
			i.numberValue = i.numberValue + 1d;
		}else if(StringsEqual(token, "null".toCharArray())){
			elementReference.element = CreateNullElement();
			i.numberValue = i.numberValue + 1d;
		}else if(charIsNumber(token[0]) || token[0] == '-'){
			stringToDecimalResult = nCreateNumberFromDecimalString(token);
			elementReference.element = CreateNumberElement(stringToDecimalResult);
			i.numberValue = i.numberValue + 1d;
		}else if(token[0] == '\"'){
			substr = Substring(token, 1d, token.length - 1d);
			elementReference.element = CreateStringElement(substr);
			i.numberValue = i.numberValue + 1d;
		}else{
			str = "".toCharArray();
			str = ConcatenateString(str, "Invalid token first in value: ".toCharArray());
			str = AppendString(str, token);
			AddStringRef(errorMessages, CreateStringReference(str));
			success = false;
		}

		if(success && depth == 0d){
			if(StringsEqual(tokens[(int)(i.numberValue)].string, "<end>".toCharArray())){
			}else{
				AddStringRef(errorMessages, CreateStringReference("The outer value cannot have any tokens following it.".toCharArray()));
				success = false;
			}
		}

		return success;
	}

	public static boolean GetJSONObject(StringReference [] tokens, NumberReference i, double depth, ElementReference elementReference, StringArrayReference errorMessages){
		Element element, value;
		boolean done, success;
		char [] key, colon, comma, closeCurly;
		char [] keystring, str;
		ElementReference valueReference;
		LinkedListElements values;
		LinkedListStrings keys;

		keys = CreateLinkedListString();
		values = CreateLinkedListElements();
		element = CreateObjectElement(0d);
		valueReference = new ElementReference();
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
						success = GetJSONValueRecursive(tokens, i, depth, valueReference, errorMessages);

						if(success){
							keystring = Substring(key, 1d, key.length - 1d);
							value = valueReference.element;
							LinkedListAddString(keys, keystring);
							LinkedListAddElement(values, value);

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
						AddStringRef(errorMessages, CreateStringReference(str));

						success = false;
						done = true;
					}
				}else{
					AddStringRef(errorMessages, CreateStringReference("Expected string as key in object.".toCharArray()));

					done = true;
					success = false;
				}
			}
		}

		if(success){
			closeCurly = tokens[(int)(i.numberValue)].string;

			if(StringsEqual(closeCurly, "}".toCharArray())){
				/* OK*/
				delete(element.object.stringListRef.stringArray);
				delete(element.object.elementListRef.array);
				element.object.stringListRef.stringArray = LinkedListStringsToArray(keys);
				element.object.elementListRef.array = LinkedListElementsToArray(values);
				elementReference.element = element;
				i.numberValue = i.numberValue + 1d;
			}else{
				AddStringRef(errorMessages, CreateStringReference("Expected close curly brackets at end of object value.".toCharArray()));
				success = false;
			}
		}

		FreeLinkedListString(keys);
		FreeLinkedListElements(values);
		delete(valueReference);

		return success;
	}

	public static boolean GetJSONArray(StringReference [] tokens, NumberReference i, double depth, ElementReference elementReference, StringArrayReference errorMessages){
		Element element, value;
		char [] nextToken, comma;
		boolean done, success;
		ElementReference valueReference;
		LinkedListElements elements;

		elements = CreateLinkedListElements();
		i.numberValue = i.numberValue + 1d;

		valueReference = new ElementReference();
		success = true;
		element = CreateArrayElement(0d);

		nextToken = tokens[(int)(i.numberValue)].string;

		if(!StringsEqual(nextToken, "]".toCharArray())){
			done = false;
			for(; !done && success; ){
				success = GetJSONValueRecursive(tokens, i, depth, valueReference, errorMessages);

				if(success){
					value = valueReference.element;
					LinkedListAddElement(elements, value);

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
			delete(element.array);
			element.array = LinkedListElementsToArray(elements);
		}else{
			AddStringRef(errorMessages, CreateStringReference("Expected close square bracket at end of array.".toCharArray()));
			success = false;
		}

		elementReference.element = element;
		FreeLinkedListElements(elements);
		delete(valueReference);

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
