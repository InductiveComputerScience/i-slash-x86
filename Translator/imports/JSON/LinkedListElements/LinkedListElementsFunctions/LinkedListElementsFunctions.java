package JSON.LinkedListElements.LinkedListElementsFunctions;

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

import JSON.structures.*;

import static JSON.lengthComputer.lengthComputer.*;

import static JSON.json.json.*;

import static JSON.writer.writer.*;

import static JSON.parser.parser.*;

import JSON.StringElementMaps.*;
import static JSON.StringElementMaps.StringElementMaps.*;

import static JSON.comparator.comparator.*;

public class LinkedListElementsFunctions{
	public static LinkedListElements CreateLinkedListElements(){
		LinkedListElements ll;

		ll = new LinkedListElements();
		ll.first = new LinkedListNodeElements();
		ll.last = ll.first;
		ll.last.end = true;

		return ll;
	}

	public static void LinkedListAddElement(LinkedListElements ll, Element value){
		ll.last.end = false;
		ll.last.value = value;
		ll.last.next = new LinkedListNodeElements();
		ll.last.next.end = true;
		ll.last = ll.last.next;
	}

	public static Element [] LinkedListElementsToArray(LinkedListElements ll){
		Element [] array;
		double length, i;
		LinkedListNodeElements node;

		node = ll.first;

		length = LinkedListElementsLength(ll);

		array = new Element [(int)(length)];

		for(i = 0d; i < length; i = i + 1d){
			array[(int)(i)] = node.value;
			node = node.next;
		}

		return array;
	}

	public static double LinkedListElementsLength(LinkedListElements ll){
		double l;
		LinkedListNodeElements node;

		l = 0d;
		node = ll.first;
		for(; !node.end; ){
			node = node.next;
			l = l + 1d;
		}

		return l;
	}

	public static void FreeLinkedListElements(LinkedListElements ll){
		LinkedListNodeElements node, prev;

		node = ll.first;

		for(; !node.end; ){
			prev = node;
			node = node.next;
			delete(prev);
		}

		delete(node);
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
