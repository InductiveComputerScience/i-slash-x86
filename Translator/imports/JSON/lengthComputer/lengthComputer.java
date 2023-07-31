package JSON.lengthComputer;

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

import static JSON.json.json.*;

import static JSON.writer.writer.*;

import static JSON.parser.parser.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

import static JSON.comparator.comparator.*;

public class lengthComputer{
	public static double ComputeJSONStringLength(Element element){
		double result;

		result = 0d;

		if(StringsEqual(element.type, "object".toCharArray())){
			result = result + ComputeJSONObjectStringLength(element);
		}else if(StringsEqual(element.type, "string".toCharArray())){
			result = JSONEscapedStringLength(element.string) + 2d;
		}else if(StringsEqual(element.type, "array".toCharArray())){
			result = result + ComputeJSONArrayStringLength(element);
		}else if(StringsEqual(element.type, "number".toCharArray())){
			result = result + ComputeJSONNumberStringLength(element);
		}else if(StringsEqual(element.type, "null".toCharArray())){
			result = result + "null".toCharArray().length;
		}else if(StringsEqual(element.type, "boolean".toCharArray())){
			result = result + ComputeJSONBooleanStringLength(element);
		}

		return result;
	}

	public static double ComputeJSONBooleanStringLength(Element element){
		double result;

		if(element.booleanValue){
			result = "true".toCharArray().length;
		}else{
			result = "false".toCharArray().length;
		}

		return result;
	}

	public static double ComputeJSONNumberStringLength(Element element){
		double length;
		char [] a;

		if(element.number != 0d){
			if(abs(element.number) >= pow(10d, 15d) || abs(element.number) <= pow(10d, -15d)){
				a = nCreateStringScientificNotationDecimalFromNumber(element.number);
				length = a.length;
			}else{
				a = nCreateStringDecimalFromNumber(element.number);
				length = a.length;
			}
		}else{
			length = 1d;
		}

		return length;
	}

	public static double ComputeJSONArrayStringLength(Element element){
		Element arrayElement;
		double i;
		double length;

		length = 1d;

		for(i = 0d; i < element.array.length; i = i + 1d){
			arrayElement = element.array[(int)(i)];

			length = length + ComputeJSONStringLength(arrayElement);

			if(i + 1d != element.array.length){
				length = length + 1d;
			}
		}

		length = length + 1d;

		return length;
	}

	public static double ComputeJSONObjectStringLength(Element element){
		char [] key;
		double i;
		StringArrayReference keys;
		Element objectElement;
		double length;

		length = 1d;

		keys = GetStringElementMapKeySet(element.object);
		for(i = 0d; i < keys.stringArray.length; i = i + 1d){
			key = keys.stringArray[(int)(i)].string;
			objectElement = GetObjectValue(element.object, key);

			length = length + 1d;
			length = length + JSONEscapedStringLength(key);
			length = length + 1d;
			length = length + 1d;

			length = length + ComputeJSONStringLength(objectElement);

			if(i + 1d != keys.stringArray.length){
				length = length + 1d;
			}
		}

		length = length + 1d;

		return length;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
