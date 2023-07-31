package JSON.StringElementMaps;

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

import static JSON.comparator.comparator.*;

public class StringElementMaps{
	public static StringArrayReference GetStringElementMapKeySet(StringElementMap stringElementMap){
		return stringElementMap.stringListRef;
	}

	public static Element GetObjectValue(StringElementMap stringElementMap, char [] key){
		Element result;
		double i;

		result = new Element();

		for(i = 0d; i < GetStringElementMapNumberOfKeys(stringElementMap); i = i + 1d){
			if(StringsEqual(stringElementMap.stringListRef.stringArray[(int)(i)].string, key)){
				result = stringElementMap.elementListRef.array[(int)(i)];
			}
		}

		return result;
	}

	public static Element GetObjectValueWithCheck(StringElementMap stringElementMap, char [] key, BooleanReference foundReference){
		Element result;
		double i;

		result = new Element();

		foundReference.booleanValue = false;
		for(i = 0d; i < GetStringElementMapNumberOfKeys(stringElementMap); i = i + 1d){
			if(StringsEqual(stringElementMap.stringListRef.stringArray[(int)(i)].string, key)){
				result = stringElementMap.elementListRef.array[(int)(i)];
				foundReference.booleanValue = true;
			}
		}

		return result;
	}

	public static void PutStringElementMap(StringElementMap stringElementMap, char [] keystring, Element value){
		AddStringRef(stringElementMap.stringListRef, CreateStringReference(keystring));
		AddElementRef(stringElementMap.elementListRef, value);
	}

	public static void SetStringElementMap(StringElementMap stringElementMap, double index, char [] keystring, Element value){
		stringElementMap.stringListRef.stringArray[(int)(index)].string = keystring;
		stringElementMap.elementListRef.array[(int)(index)] = value;
	}

	public static double GetStringElementMapNumberOfKeys(StringElementMap stringElementMap){
		return stringElementMap.stringListRef.stringArray.length;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
