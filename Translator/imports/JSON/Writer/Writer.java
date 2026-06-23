package JSON.Writer;

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

import static JSON.Parser.Parser.*;

import static JSON.TokenReader.TokenReader.*;

import static JSON.Validator.Validator.*;

import static JSON.TypeMatcher.TypeMatcher.*;

import static JSON.LengthComputer.LengthComputer.*;

public class Writer{
	public static char [] WriteJSON(Data data){
		WriterSettings writerSettings;

		writerSettings = CreateDefaultWriterSettings();

		return WriteJSONWithOptions(data, writerSettings);
	}

	public static char [] WriteJSONPretty(Data data){
		WriterSettings writerSettings;

		writerSettings = CreateDefaultWriterSettings();
		writerSettings.prettyprint = true;

		return WriteJSONWithOptions(data, writerSettings);
	}

	public static char [] WriteJSONPrettyBinary(Data data){
		WriterSettings writerSettings;

		writerSettings = CreateDefaultWriterSettings();
		writerSettings.prettyprint = true;
		writerSettings.humanreadable = true;
		writerSettings.binary = true;

		return WriteJSONWithOptions(data, writerSettings);
	}

	public static WriterSettings CreateDefaultWriterSettings(){
		WriterSettings writerSettings;

		writerSettings = new WriterSettings();
		writerSettings.prettyprint = false;
		writerSettings.humanreadable = false;
		writerSettings.binary = false;
		writerSettings.level = 0d;
		writerSettings.indentString = "  ".toCharArray();

		return writerSettings;
	}

	public static char [] WriteJSONWithOptions(Data data, WriterSettings settings){
		DynamicArrayCharacters da;
		char [] result;

		da = CreateDynamicArrayCharacters();

		if(IsStructure(data)){
			WriteObject(data, da, settings);
		}else if(IsString(data)){
			WriteString(data, da);
		}else if(IsArray(data)){
			WriteArray(data, da, settings);
		}else if(IsNumber(data)){
			WriteNumber(data, da, settings);
		}else if(IsNoType(data)){
			DynamicArrayAddString(da, "null".toCharArray());
		}else if(IsBoolean(data)){
			WriteBooleanValue(data, da);
		}

		result = DynamicArrayCharactersToArray(da);

		return result;
	}

	public static void WriteBooleanValue(Data element, DynamicArrayCharacters da){
		if(element.booleanx){
			DynamicArrayAddString(da, "true".toCharArray());
		}else{
			DynamicArrayAddString(da, "false".toCharArray());
		}
	}

	public static void WriteNumber(Data element, DynamicArrayCharacters da, WriterSettings settings){
		char [] numberString;

		if(settings.humanreadable){
			if(settings.binary){
				numberString = nNumberToHumanReadableBinary(element.number);
			}else{
				numberString = nNumberToHumanReadableShortScale(element.number);
			}
		}else if(element.number != 0d){
			if(abs(element.number) >= pow(10d, 15d) || abs(element.number) <= pow(10d, -15d)){
				numberString = nCreateStringScientificNotationDecimalFromNumber(element.number);
			}else{
				numberString = nCreateStringDecimalFromNumber(element.number);
			}
		}else{
			numberString = nCreateStringDecimalFromNumber(element.number);
		}

		if(settings.humanreadable){
			DynamicArrayAddCharacter(da, '\"');
		}
		DynamicArrayAddString(da, numberString);
		if(settings.humanreadable){
			DynamicArrayAddCharacter(da, '\"');
		}
	}

	public static void WriteArray(Data data, DynamicArrayCharacters da, WriterSettings settings){
		char [] s;
		Data entry;
		double i, j;

		DynamicArrayAddString(da, "[".toCharArray());

		if(settings.prettyprint){
			settings.level = settings.level + 1d;
			if(data.array.length > 0d){
				DynamicArrayAddCharacter(da, '\n');
			}
		}

		for(i = 0d; i < data.array.length; i = i + 1d){
			entry = ArrayIndex(data.array, i);

			if(settings.prettyprint){
				for(j = 0d; j < settings.level; j = j + 1d){
					DynamicArrayAddString(da, settings.indentString);
				}
			}

			s = WriteJSONWithOptions(entry, settings);
			DynamicArrayAddString(da, s);

			if(i + 1d != data.array.length){
				DynamicArrayAddString(da, ",".toCharArray());
				if(settings.prettyprint){
					DynamicArrayAddString(da, "\n".toCharArray());
				}
			}
		}

		if(settings.prettyprint){
			settings.level = settings.level - 1d;
			if(data.array.length > 0d){
				DynamicArrayAddCharacter(da, '\n');
				for(i = 0d; i < settings.level; i = i + 1d){
					DynamicArrayAddString(da, settings.indentString);
				}
			}
		}

		DynamicArrayAddString(da, "]".toCharArray());
	}

	public static void WriteString(Data element, DynamicArrayCharacters da){
		char [] str;

		DynamicArrayAddString(da, "\"".toCharArray());
		str = JSONEscapeString(element.string);
		DynamicArrayAddString(da, str);
		DynamicArrayAddString(da, "\"".toCharArray());

		delete(str);
	}

	public static char [] JSONEscapeString(char [] string){
		double i, length;
		NumberReference index, lettersReference;
		char [] ns, escaped;

		length = JSONEscapedStringLength(string);

		ns = new char [(int)(length)];
		index = CreateNumberReference(0d);
		lettersReference = CreateNumberReference(0d);

		for(i = 0d; i < string.length; i = i + 1d){
			if(JSONMustBeEscaped(string[(int)(i)], lettersReference)){
				escaped = JSONEscapeCharacter(string[(int)(i)]);
				WriteStringToStingStream(ns, index, escaped);
			}else{
				WriteCharacterToStingStream(ns, index, string[(int)(i)]);
			}
		}

		return ns;
	}

	public static double JSONEscapedStringLength(char [] string){
		NumberReference lettersReference;
		double i, length;

		lettersReference = CreateNumberReference(0d);
		length = 0d;

		for(i = 0d; i < string.length; i = i + 1d){
			if(JSONMustBeEscaped(string[(int)(i)], lettersReference)){
				length = length + lettersReference.numberValue;
			}else{
				length = length + 1d;
			}
		}
		return length;
	}

	public static char [] JSONEscapeCharacter(char c){
		double code;
		char [] escaped;
		StringReference hexNumber;
		double q, rs, s, b, f, n, r, t;

		code = c;

		q = 34d;
		rs = 92d;
		s = 47d;
		b = 8d;
		f = 12d;
		n = 10d;
		r = 13d;
		t = 9d;

		hexNumber = new StringReference();

		if(code == q){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = '\"';
		}else if(code == rs){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = '\\';
		}else if(code == s){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = '/';
		}else if(code == b){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = 'b';
		}else if(code == f){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = 'f';
		}else if(code == n){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = 'n';
		}else if(code == r){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = 'r';
		}else if(code == t){
			escaped = new char [2];
			escaped[0] = '\\';
			escaped[1] = 't';
		}else if(code >= 0d && code <= 31d){
			escaped = new char [6];
			escaped[0] = '\\';
			escaped[1] = 'u';
			escaped[2] = '0';
			escaped[3] = '0';

			nCreateStringFromNumberWithCheck(code, 16d, hexNumber);

			if(hexNumber.string.length == 1d){
				escaped[4] = '0';
				escaped[5] = hexNumber.string[0];
			}else if(hexNumber.string.length == 2d){
				escaped[4] = hexNumber.string[0];
				escaped[5] = hexNumber.string[1];
			}
		}else{
			escaped = new char [1];
			escaped[0] = c;
		}

		return escaped;
	}

	public static boolean JSONMustBeEscaped(char c, NumberReference letters){
		double code;
		boolean mustBeEscaped;
		double q, rs, s, b, f, n, r, t;

		code = c;
		mustBeEscaped = false;

		q = 34d;
		rs = 92d;
		s = 47d;
		b = 8d;
		f = 12d;
		n = 10d;
		r = 13d;
		t = 9d;

		if(code == q || code == rs || code == s || code == b || code == f || code == n || code == r || code == t){
			mustBeEscaped = true;
			letters.numberValue = 2d;
		}else if(code >= 0d && code <= 31d){
			mustBeEscaped = true;
			letters.numberValue = 6d;
		}else if(code >= pow(2d, 16d)){
			mustBeEscaped = true;
			letters.numberValue = 6d;
		}

		return mustBeEscaped;
	}

	public static void WriteObject(Data data, DynamicArrayCharacters da, WriterSettings settings){
		char [] s, key, escapedKey;
		double i, j;
		StringReference [] keys;
		Data objectElement;

		DynamicArrayAddString(da, "{".toCharArray());

		keys = GetStructKeys(data.structure);

		if(settings.prettyprint){
			settings.level = settings.level + 1d;
			if(keys.length > 0d){
				DynamicArrayAddCharacter(da, '\n');
			}
		}

		for(i = 0d; i < keys.length; i = i + 1d){
			key = keys[(int)(i)].string;
			objectElement = GetDataFromStruct(data.structure, key);

			if(settings.prettyprint){
				for(j = 0d; j < settings.level; j = j + 1d){
					DynamicArrayAddString(da, settings.indentString);
				}
			}

			escapedKey = JSONEscapeString(key);
			DynamicArrayAddString(da, "\"".toCharArray());
			DynamicArrayAddString(da, escapedKey);
			DynamicArrayAddString(da, "\"".toCharArray());
			DynamicArrayAddString(da, ":".toCharArray());

			if(settings.prettyprint){
				DynamicArrayAddString(da, " ".toCharArray());
			}

			s = WriteJSONWithOptions(objectElement, settings);
			DynamicArrayAddString(da, s);

			if(i + 1d != keys.length){
				DynamicArrayAddString(da, ",".toCharArray());
				if(settings.prettyprint){
					DynamicArrayAddCharacter(da, '\n');
				}
			}
		}

		if(settings.prettyprint){
			settings.level = settings.level - 1d;
			if(keys.length > 0d){
				DynamicArrayAddCharacter(da, '\n');
				for(i = 0d; i < settings.level; i = i + 1d){
					DynamicArrayAddString(da, settings.indentString);
				}
			}
		}

		DynamicArrayAddCharacter(da, '}');
	}

	public static char [] WriteJSONFromStruct(Structure struct){
		Data data;
		char [] json;

		data = CreateNewStructData();
		data.structure = struct;
		json = WriteJSON(data);

		return json;
	}

	public static char [] WriteJSONFromArray(Array array){
		Data data;
		char [] json;

		data = CreateNewArrayData();
		data.array = array;
		json = WriteJSON(data);

		return json;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
