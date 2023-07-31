package JSON.json;

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

import static JSON.writer.writer.*;

import static JSON.parser.parser.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

import static JSON.comparator.comparator.*;

public class json{
	public static Element CreateStringElement(char [] string){
		Element element;
		element = new Element();
		element.type = "string".toCharArray();
		element.string = string;
		return element;
	}

	public static Element CreateBooleanElement(boolean booleanValue){
		Element element;
		element = new Element();
		element.type = "boolean".toCharArray();
		element.booleanValue = booleanValue;
		return element;
	}

	public static Element CreateNullElement(){
		Element element;
		element = new Element();
		element.type = "null".toCharArray();
		return element;
	}

	public static Element CreateNumberElement(double number){
		Element element;
		element = new Element();
		element.type = "number".toCharArray();
		element.number = number;
		return element;
	}

	public static Element CreateArrayElement(double length){
		Element element;
		element = new Element();
		element.type = "array".toCharArray();
		element.array = new Element [(int)(length)];
		return element;
	}

	public static Element CreateObjectElement(double length){
		Element element;
		element = new Element();
		element.type = "object".toCharArray();
		element.object = new StringElementMap();
		element.object.stringListRef = CreateStringArrayReferenceLengthValue(length, "".toCharArray());
		element.object.elementListRef = new ElementArrayReference();
		element.object.elementListRef.array = new Element [(int)(length)];
		return element;
	}

	public static void DeleteElement(Element element){
		if(StringsEqual(element.type, "object".toCharArray())){
			DeleteObject(element);
		}else if(StringsEqual(element.type, "string".toCharArray())){
			delete(element);
		}else if(StringsEqual(element.type, "array".toCharArray())){
			DeleteArray(element);
		}else if(StringsEqual(element.type, "number".toCharArray())){
			delete(element);
		}else if(StringsEqual(element.type, "null".toCharArray())){
			delete(element);
		}else if(StringsEqual(element.type, "boolean".toCharArray())){
			delete(element);
		}else{
		}
	}

	public static void DeleteObject(Element element){
		StringArrayReference keys;
		double i;
		char [] key;
		Element objectElement;

		keys = GetStringElementMapKeySet(element.object);
		for(i = 0d; i < keys.stringArray.length; i = i + 1d){
			key = keys.stringArray[(int)(i)].string;
			objectElement = GetObjectValue(element.object, key);
			DeleteElement(objectElement);
		}
	}

	public static void DeleteArray(Element element){
		double i;
		Element arrayElement;

		for(i = 0d; i < element.array.length; i = i + 1d){
			arrayElement = element.array[(int)(i)];
			DeleteElement(arrayElement);
		}
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
