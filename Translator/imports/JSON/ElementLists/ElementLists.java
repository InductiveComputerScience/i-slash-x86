package JSON.ElementLists;

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

import JSON.LinkedListElements.Structures.*;

import static JSON.LinkedListElements.LinkedListElementsFunctions.LinkedListElementsFunctions.*;

import JSON.structures.*;

import static JSON.lengthComputer.lengthComputer.*;

import static JSON.json.json.*;

import static JSON.writer.writer.*;

import static JSON.parser.parser.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

import static JSON.comparator.comparator.*;

public class ElementLists{
	public static Element [] AddElement(Element [] list, Element a){
		Element [] newlist;
		double i;

		newlist = new Element [(int)(list.length + 1d)];

		for(i = 0d; i < list.length; i = i + 1d){
			newlist[(int)(i)] = list[(int)(i)];
		}
		newlist[(int)(list.length)] = a;

		delete(list);

		return newlist;
	}

	public static void AddElementRef(ElementArrayReference list, Element i){
		list.array = AddElement(list.array, i);
	}

	public static Element [] RemoveElement(Element [] list, double n){
		Element [] newlist;
		double i;

		newlist = new Element [(int)(list.length - 1d)];

		for(i = 0d; i < list.length; i = i + 1d){
			if(i < n){
				newlist[(int)(i)] = list[(int)(i)];
			}
			if(i > n){
				newlist[(int)(i - 1d)] = list[(int)(i)];
			}
		}

		delete(list);

		return newlist;
	}

	public static Element GetElementRef(ElementArrayReference list, double i){
		return list.array[(int)(i)];
	}

	public static void RemoveElementRef(ElementArrayReference list, double i){
		list.array = RemoveElement(list.array, i);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
