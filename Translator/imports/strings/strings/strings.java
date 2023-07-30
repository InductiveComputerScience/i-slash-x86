package strings.strings;

import static java.lang.Math.*;

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

import references.references.*;
import static references.references.references.*;

import static charCharacters.Characters.Characters.*;


import static strings.stream.stream.*;

public class strings{
	public static boolean SubstringWithCheck(char [] string, double from, double to, StringReference stringReference){
		boolean success;

		if(from >= 0d && from <= string.length && to >= 0d && to <= string.length && from <= to){
			stringReference.string = Substring(string, from, to);
			success = true;
		}else{
			success = false;
		}

		return success;
	}

	public static char [] Substring(char [] string, double from, double to){
		char [] n;
		double i, length;

		length = to - from;

		n = new char [(int)(length)];

		for(i = from; i < to; i = i + 1d){
			n[(int)(i - from)] = string[(int)(i)];
		}

		return n;
	}

	public static char [] AppendString(char [] s1, char [] s2){
		char [] newString;

		newString = ConcatenateString(s1, s2);

		delete(s1);

		return newString;
	}

	public static char [] ConcatenateString(char [] s1, char [] s2){
		char [] newString;
		double i;

		newString = new char [(int)(s1.length + s2.length)];

		for(i = 0d; i < s1.length; i = i + 1d){
			newString[(int)(i)] = s1[(int)(i)];
		}

		for(i = 0d; i < s2.length; i = i + 1d){
			newString[(int)(s1.length + i)] = s2[(int)(i)];
		}

		return newString;
	}

	public static char [] AppendCharacter(char [] string, char c){
		char [] newString;

		newString = ConcatenateCharacter(string, c);

		delete(string);

		return newString;
	}

	public static char [] ConcatenateCharacter(char [] string, char c){
		char [] newString;
		double i;
		newString = new char [(int)(string.length + 1d)];

		for(i = 0d; i < string.length; i = i + 1d){
			newString[(int)(i)] = string[(int)(i)];
		}

		newString[(int)(string.length)] = c;

		return newString;
	}

	public static StringReference [] SplitByCharacter(char [] toSplit, char splitBy){
		StringReference [] split;
		char [] stringToSplitBy;

		stringToSplitBy = new char [1];
		stringToSplitBy[0] = splitBy;

		split = SplitByString(toSplit, stringToSplitBy);

		delete(stringToSplitBy);

		return split;
	}

	public static boolean IndexOfCharacter(char [] string, char character, NumberReference indexReference){
		double i;
		boolean found;

		found = false;
		for(i = 0d; i < string.length && !found; i = i + 1d){
			if(string[(int)(i)] == character){
				found = true;
				indexReference.numberValue = i;
			}
		}

		return found;
	}

	public static boolean SubstringEqualsWithCheck(char [] string, double from, char [] substring, BooleanReference equalsReference){
		boolean success;

		if(from < string.length){
			success = true;
			equalsReference.booleanValue = SubstringEquals(string, from, substring);
		}else{
			success = false;
		}

		return success;
	}

	public static boolean SubstringEquals(char [] string, double from, char [] substring){
		double i;
		boolean equal;

		equal = true;
		if(string.length - from >= substring.length){
			for(i = 0d; i < substring.length && equal; i = i + 1d){
				if(string[(int)(from + i)] != substring[(int)(i)]){
					equal = false;
				}
			}
		}else{
			equal = false;
		}

		return equal;
	}

	public static boolean IndexOfString(char [] string, char [] substring, NumberReference indexReference){
		double i;
		boolean found;

		found = false;
		for(i = 0d; i < string.length - substring.length + 1d && !found; i = i + 1d){
			if(SubstringEquals(string, i, substring)){
				found = true;
				indexReference.numberValue = i;
			}
		}

		return found;
	}

	public static boolean ContainsCharacter(char [] string, char character){
		double i;
		boolean found;

		found = false;
		for(i = 0d; i < string.length && !found; i = i + 1d){
			if(string[(int)(i)] == character){
				found = true;
			}
		}

		return found;
	}

	public static boolean ContainsString(char [] string, char [] substring){
		return IndexOfString(string, substring, new NumberReference());
	}

	public static void ToUpperCase(char [] string){
		double i;

		for(i = 0d; i < string.length; i = i + 1d){
			string[(int)(i)] = charToUpperCase(string[(int)(i)]);
		}
	}

	public static void ToLowerCase(char [] string){
		double i;

		for(i = 0d; i < string.length; i = i + 1d){
			string[(int)(i)] = charToLowerCase(string[(int)(i)]);
		}
	}

	public static boolean EqualsIgnoreCase(char [] a, char [] b){
		boolean equal;
		double i;

		if(a.length == b.length){
			equal = true;
			for(i = 0d; i < a.length && equal; i = i + 1d){
				if(charToLowerCase(a[(int)(i)]) != charToLowerCase(b[(int)(i)])){
					equal = false;
				}
			}
		}else{
			equal = false;
		}

		return equal;
	}

	public static char [] ReplaceString(char [] string, char [] toReplace, char [] replaceWith){
		char [] result;
		double i, j;
		BooleanReference equalsReference;
		boolean success;
		DynamicArrayCharacters da;

		da = CreateDynamicArrayCharacters();

		equalsReference = new BooleanReference();

		for(i = 0d; i < string.length; ){
			success = SubstringEqualsWithCheck(string, i, toReplace, equalsReference);
			if(success){
				success = equalsReference.booleanValue;
			}

			if(success && toReplace.length > 0d){
				for(j = 0d; j < replaceWith.length; j = j + 1d){
					DynamicArrayAddCharacter(da, replaceWith[(int)(j)]);
				}
				i = i + toReplace.length;
			}else{
				DynamicArrayAddCharacter(da, string[(int)(i)]);
				i = i + 1d;
			}
		}

		result = DynamicArrayCharactersToArray(da);

		FreeDynamicArrayCharacters(da);

		return result;
	}

	public static char [] ReplaceCharacterToNew(char [] string, char toReplace, char replaceWith){
		char [] result;
		double i;

		result = new char [(int)(string.length)];

		for(i = 0d; i < string.length; i = i + 1d){
			if(string[(int)(i)] == toReplace){
				result[(int)(i)] = replaceWith;
			}else{
				result[(int)(i)] = string[(int)(i)];
			}
		}

		return result;
	}

	public static void ReplaceCharacter(char [] string, char toReplace, char replaceWith){
		double i;

		for(i = 0d; i < string.length; i = i + 1d){
			if(string[(int)(i)] == toReplace){
				string[(int)(i)] = replaceWith;
			}
		}
	}

	public static char [] Trim(char [] string){
		char [] result;
		double i, lastWhitespaceLocationStart, lastWhitespaceLocationEnd;
		boolean firstNonWhitespaceFound;

		/* Find whitepaces at the start.*/
		lastWhitespaceLocationStart = -1d;
		firstNonWhitespaceFound = false;
		for(i = 0d; i < string.length && !firstNonWhitespaceFound; i = i + 1d){
			if(charIsWhiteSpace(string[(int)(i)])){
				lastWhitespaceLocationStart = i;
			}else{
				firstNonWhitespaceFound = true;
			}
		}

		/* Find whitepaces at the end.*/
		lastWhitespaceLocationEnd = string.length;
		firstNonWhitespaceFound = false;
		for(i = string.length - 1d; i >= 0d && !firstNonWhitespaceFound; i = i - 1d){
			if(charIsWhiteSpace(string[(int)(i)])){
				lastWhitespaceLocationEnd = i;
			}else{
				firstNonWhitespaceFound = true;
			}
		}

		if(lastWhitespaceLocationStart < lastWhitespaceLocationEnd){
			result = Substring(string, lastWhitespaceLocationStart + 1d, lastWhitespaceLocationEnd);
		}else{
			result = new char [0];
		}

		return result;
	}

	public static boolean StartsWith(char [] string, char [] start){
		boolean startsWithString;

		startsWithString = false;
		if(string.length >= start.length){
			startsWithString = SubstringEquals(string, 0d, start);
		}

		return startsWithString;
	}

	public static boolean EndsWith(char [] string, char [] end){
		boolean endsWithString;

		endsWithString = false;
		if(string.length >= end.length){
			endsWithString = SubstringEquals(string, string.length - end.length, end);
		}

		return endsWithString;
	}

	public static StringReference [] SplitByString(char [] toSplit, char [] splitBy){
		StringReference [] split;
		char [] next;
		double i;
		char c;
		StringReference n;

		split = new StringReference [0];

		next = new char [0];
		for(i = 0d; i < toSplit.length; ){
			c = toSplit[(int)(i)];

			if(SubstringEquals(toSplit, i, splitBy)){
				n = new StringReference();
				n.string = next;
				split = AddString(split, n);
				next = new char [0];
				i = i + splitBy.length;
			}else{
				next = AppendCharacter(next, c);
				i = i + 1d;
			}
		}

		n = new StringReference();
		n.string = next;
		split = AddString(split, n);

		return split;
	}

	public static boolean StringIsBefore(char [] a, char [] b){
		boolean before, equal, done;
		double i;

		before = false;
		equal = true;
		done = false;

		if(a.length == 0d && b.length > 0d){
			before = true;
		}else{
			for(i = 0d; i < a.length && i < b.length && !done; i = i + 1d){
				if(a[(int)(i)] != b[(int)(i)]){
					equal = false;
				}
				if(charCharacterIsBefore(a[(int)(i)], b[(int)(i)])){
					before = true;
				}
				if(charCharacterIsBefore(b[(int)(i)], a[(int)(i)])){
					done = true;
				}
			}

			if(equal){
				if(a.length < b.length){
					before = true;
				}
			}
		}

		return before;
	}

	public static char [] JoinStringsWithSeparator(StringReference [] strings, char [] separator){
		char [] result, string;
		double length, i;
		NumberReference index;

		index = CreateNumberReference(0d);

		length = 0d;
		for(i = 0d; i < strings.length; i = i + 1d){
			length = length + strings[(int)(i)].string.length;
		}
		length = length + (strings.length - 1d)*separator.length;

		result = new char [(int)(length)];

		for(i = 0d; i < strings.length; i = i + 1d){
			string = strings[(int)(i)].string;
			WriteStringToStingStream(result, index, string);
			if(i + 1d < strings.length){
				WriteStringToStingStream(result, index, separator);
			}
		}

		delete(index);

		return result;
	}

	public static char [] JoinStrings(StringReference [] strings){
		char [] result, string;
		double length, i;
		NumberReference index;

		index = CreateNumberReference(0d);

		length = 0d;
		for(i = 0d; i < strings.length; i = i + 1d){
			length = length + strings[(int)(i)].string.length;
		}

		result = new char [(int)(length)];

		for(i = 0d; i < strings.length; i = i + 1d){
			string = strings[(int)(i)].string;
			WriteStringToStingStream(result, index, string);
		}

		delete(index);

		return result;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
