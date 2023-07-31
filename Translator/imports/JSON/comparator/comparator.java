package JSON.comparator;

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

import static JSON.parser.parser.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

public class comparator{
	public static boolean JSONCompare(char [] a, char [] b, double epsilon, BooleanReference equal, StringArrayReference errorMessage){
		boolean success;
		ElementReference eaRef, ebRef;
		Element ea, eb;

		eaRef = new ElementReference();
		ebRef = new ElementReference();

		success = ReadJSON(a, eaRef, errorMessage);

		if(success){
			ea = eaRef.element;

			success = ReadJSON(b, ebRef, errorMessage);

			if(success){
				eb = ebRef.element;

				equal.booleanValue = JSONCompareElements(ea, eb, epsilon);

				DeleteElement(eb);
			}

			DeleteElement(ea);
		}

		return success;
	}

	public static boolean JSONCompareElements(Element ea, Element eb, double epsilon){
		boolean equal;
		char [] typeName;

		equal = StringsEqual(ea.type, eb.type);
        
		if(equal){
			typeName = ea.type;
			if(StringsEqual(typeName, "object".toCharArray())){
				equal = JSONCompareObjects(ea.object, eb.object, epsilon);
			}else if(StringsEqual(typeName, "string".toCharArray())){
				equal = StringsEqual(ea.string, eb.string);
			}else if(StringsEqual(typeName, "array".toCharArray())){
				equal = JSONCompareArrays(ea.array, eb.array, epsilon);
			}else if(StringsEqual(typeName, "number".toCharArray())){
				equal = EpsilonCompare(ea.number, eb.number, epsilon);
			}else if(StringsEqual(typeName, "null".toCharArray())){
				equal = true;
			}else if(StringsEqual(typeName, "boolean".toCharArray())){
				equal = ea.booleanValue == eb.booleanValue;
			}
		}
        
		return equal;
	}

	public static boolean JSONCompareArrays(Element [] ea, Element [] eb, double epsilon){
		boolean equals;
		double i, length;

		equals = ea.length == eb.length;

		if(equals){
			length = ea.length;
			for(i = 0d; i < length && equals; i = i + 1d){
				equals = JSONCompareElements(ea[(int)(i)], eb[(int)(i)], epsilon);
			}
		}

		return equals;
	}

	public static boolean JSONCompareObjects(StringElementMap ea, StringElementMap eb, double epsilon){
		boolean equals;
		double akeys, bkeys, i;
		StringArrayReference keys;
		char [] key;
		BooleanReference aFoundReference, bFoundReference;
		Element eaValue, ebValue;

		aFoundReference = new BooleanReference();
		bFoundReference = new BooleanReference();

		akeys = GetStringElementMapNumberOfKeys(ea);
		bkeys = GetStringElementMapNumberOfKeys(eb);

		equals = akeys == bkeys;

		if(equals){
			keys = GetStringElementMapKeySet(ea);

			for(i = 0d; i < keys.stringArray.length && equals; i = i + 1d){
				key = keys.stringArray[(int)(i)].string;

				eaValue = GetObjectValueWithCheck(ea, key, aFoundReference);
				ebValue = GetObjectValueWithCheck(eb, key, bFoundReference);

				if(aFoundReference.booleanValue && bFoundReference.booleanValue){
					equals = JSONCompareElements(eaValue, ebValue, epsilon);
				}else{
					equals = false;
				}
			}
		}

		return equals;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
