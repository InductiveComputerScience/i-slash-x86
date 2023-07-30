package JSON.TokenReader;

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

import static JSON.Parser.Parser.*;

import static JSON.Validator.Validator.*;

import static JSON.LengthComputer.LengthComputer.*;

public class TokenReader{
	public static boolean JSONTokenize(char [] json, StringArrayReference tokensReference, StringReference message){
		double i;
		char c;
		char [] str;
		StringReference stringReference, tokenReference;
		NumberReference stringLength;
		boolean success;
		Array ll;

		ll = CreateArray();
		success = true;

		stringLength = new NumberReference();
		tokenReference = new StringReference();

		for(i = 0d; i < json.length && success; ){
			c = json[(int)(i)];

			if(c == '{'){
				AddStringToArray(ll, "{".toCharArray());
				i = i + 1d;
			}else if(c == '}'){
				AddStringToArray(ll, "}".toCharArray());
				i = i + 1d;
			}else if(c == '['){
				AddStringToArray(ll, "[".toCharArray());
				i = i + 1d;
			}else if(c == ']'){
				AddStringToArray(ll, "]".toCharArray());
				i = i + 1d;
			}else if(c == ':'){
				AddStringToArray(ll, ":".toCharArray());
				i = i + 1d;
			}else if(c == ','){
				AddStringToArray(ll, ",".toCharArray());
				i = i + 1d;
			}else if(c == 'f'){
				success = GetJSONPrimitiveName(json, i, message, "false".toCharArray(), tokenReference);
				if(success){
					AddStringToArray(ll, "false".toCharArray());
					i = i + "false".toCharArray().length;
				}
			}else if(c == 't'){
				success = GetJSONPrimitiveName(json, i, message, "true".toCharArray(), tokenReference);
				if(success){
					AddStringToArray(ll, "true".toCharArray());
					i = i + "true".toCharArray().length;
				}
			}else if(c == 'n'){
				success = GetJSONPrimitiveName(json, i, message, "null".toCharArray(), tokenReference);
				if(success){
					AddStringToArray(ll, "null".toCharArray());
					i = i + "null".toCharArray().length;
				}
			}else if(c == ' ' || c == '\n' || c == '\t' || c == '\r'){
				/* Skip.*/
				i = i + 1d;
			}else if(c == '\"'){
				success = GetJSONString(json, i, tokenReference, stringLength, message);
				if(success){
					AddStringToArray(ll, tokenReference.string);
					i = i + stringLength.numberValue;
				}
			}else if(IsJSONNumberCharacter(c)){
				success = GetJSONNumberToken(json, i, tokenReference, message);
				if(success){
					AddStringToArray(ll, tokenReference.string);
					i = i + tokenReference.string.length;
				}
			}else{
				str = ConcatenateCharacter("Invalid start of Token: ".toCharArray(), c);
				stringReference = CreateStringReference(str);
				message.string = stringReference.string;
				i = i + 1d;
				success = false;
			}
		}

		if(success){
			AddStringToArray(ll, "<end>".toCharArray());
			tokensReference.stringArray = ToStaticStringArray(ll);
			FreeArray(ll);
		}

		return success;
	}

	public static boolean GetJSONNumberToken(char [] json, double start, StringReference tokenReference, StringReference message){
		char c;
		double end, i;
		boolean done, success;
		char [] numberString;

		end = json.length;
		done = false;

		for(i = start; i < json.length && !done; i = i + 1d){
			c = json[(int)(i)];
			if(!IsJSONNumberCharacter(c)){
				done = true;
				end = i;
			}
		}

		numberString = Substring(json, start, end);

		success = IsValidJSONNumber(numberString, message);

		tokenReference.string = numberString;

		return success;
	}

	public static boolean IsValidJSONNumber(char [] n, StringReference message){
		boolean success;
		double i;

		i = 0d;

		/* JSON allows an optional negative sign.*/
		if(n[(int)(i)] == '-'){
			i = i + 1d;
		}

		if(i < n.length){
			success = IsValidJSONNumberAfterSign(n, i, message);
		}else{
			success = false;
			message.string = "Number must contain at least one digit.".toCharArray();
		}

		return success;
	}

	public static boolean IsValidJSONNumberAfterSign(char [] n, double i, StringReference message){
		boolean success;

		if(charIsNumber(n[(int)(i)])){
			/* 0 first means only 0.*/
			if(n[(int)(i)] == '0'){
				i = i + 1d;
			}else{
				/* 1-9 first, read following digits.*/
				i = IsValidJSONNumberAdvancePastDigits(n, i);
			}

			if(i < n.length){
				success = IsValidJSONNumberFromDotOrExponent(n, i, message);
			}else{
				/* If integer, we are done now.*/
				success = true;
			}
		}else{
			success = false;
			message.string = "A number must start with 0-9 (after the optional sign).".toCharArray();
		}

		return success;
	}

	public static double IsValidJSONNumberAdvancePastDigits(char [] n, double i){
		boolean done;

		i = i + 1d;
		done = false;
		for(; i < n.length && !done; ){
			if(charIsNumber(n[(int)(i)])){
				i = i + 1d;
			}else{
				done = true;
			}
		}

		return i;
	}

	public static boolean IsValidJSONNumberFromDotOrExponent(char [] n, double i, StringReference message){
		boolean wasDotAndOrE, success;

		wasDotAndOrE = false;
		success = true;

		if(n[(int)(i)] == '.'){
			i = i + 1d;
			wasDotAndOrE = true;

			if(i < n.length){
				if(charIsNumber(n[(int)(i)])){
					/* Read digits following decimal point.*/
					i = IsValidJSONNumberAdvancePastDigits(n, i);

					if(i == n.length){
						/* If non-scientific decimal number, we are done.*/
						success = true;
					}
				}else{
					success = false;
					message.string = "There must be numbers after the decimal point.".toCharArray();
				}
			}else{
				success = false;
				message.string = "There must be numbers after the decimal point.".toCharArray();
			}
		}

		if(i < n.length && success){
			if(n[(int)(i)] == 'e' || n[(int)(i)] == 'E'){
				wasDotAndOrE = true;
				success = IsValidJSONNumberFromExponent(n, i, message);
			}else{
				success = false;
				message.string = "Expected e or E.".toCharArray();
			}
		}else if(i == n.length && success){
			/* If number with decimal point.*/
			success = true;
		}else{
			success = false;
			message.string = "There must be numbers after the decimal point.".toCharArray();
		}

		if(wasDotAndOrE){
		}else{
			success = false;
			message.string = "Exprected decimal point or e or E.".toCharArray();
		}

		return success;
	}

	public static boolean IsValidJSONNumberFromExponent(char [] n, double i, StringReference message){
		boolean success;

		i = i + 1d;

		if(i < n.length){
			/* The exponent sign can either + or -,*/
			if(n[(int)(i)] == '+' || n[(int)(i)] == '-'){
				i = i + 1d;
			}

			if(i < n.length){
				if(charIsNumber(n[(int)(i)])){
					/* Read digits following decimal point.*/
					i = IsValidJSONNumberAdvancePastDigits(n, i);

					if(i == n.length){
						/* We found scientific number.*/
						success = true;
					}else{
						success = false;
						message.string = "There was characters following the exponent.".toCharArray();
					}
				}else{
					success = false;
					message.string = "There must be a digit following the optional exponent sign.".toCharArray();
				}
			}else{
				success = false;
				message.string = "There must be a digit following optional the exponent sign.".toCharArray();
			}
		}else{
			success = false;
			message.string = "There must be a sign or a digit following e or E.".toCharArray();
		}

		return success;
	}

	public static boolean IsJSONNumberCharacter(char c){
		char [] numericCharacters;
		boolean found;
		double i;

		numericCharacters = "0123456789.-+eE".toCharArray();

		found = false;

		for(i = 0d; i < numericCharacters.length; i = i + 1d){
			if(numericCharacters[(int)(i)] == c){
				found = true;
			}
		}

		return found;
	}

	public static boolean GetJSONPrimitiveName(char [] string, double start, StringReference message, char [] primitive, StringReference tokenReference){
		char c, p;
		boolean done, success;
		double i;
		char [] str, token;

		done = false;
		success = true;

		token = "".toCharArray();

		for(i = start; i < string.length && ((i - start) < primitive.length) && !done; i = i + 1d){
			c = string[(int)(i)];
			p = primitive[(int)(i - start)];
			if(c == p){
				/* OK*/
				if((i + 1d - start) == primitive.length){
					done = true;
				}
			}else{
				str = "".toCharArray();
				str = ConcatenateString(str, "Primitive invalid: ".toCharArray());
				str = AppendCharacter(str, c);
				str = AppendString(str, " vs ".toCharArray());
				str = AppendCharacter(str, p);

				message.string = str;
				done = true;
				success = false;
			}
		}

		if(done){
			if(StringsEqual(primitive, "false".toCharArray())){
				token = "false".toCharArray();
			}
			if(StringsEqual(primitive, "true".toCharArray())){
				token = "true".toCharArray();
			}
			if(StringsEqual(primitive, "null".toCharArray())){
				token = "null".toCharArray();
			}
		}else{
			message.string = "Primitive invalid".toCharArray();
			success = false;
		}

		tokenReference.string = token;

		return success;
	}

	public static boolean GetJSONString(char [] json, double start, StringReference tokenReference, NumberReference stringLengthReference, StringReference message){
		boolean success, done;
		char [] string, hex;
		NumberReference characterCount, hexReference;
		double i, l, c;
		StringReference errorMessage;

		characterCount = CreateNumberReference(0d);
		hex = CreateString(4d, '0');
		hexReference = new NumberReference();
		errorMessage = new StringReference();

		success = IsValidJSONStringInJSON(json, start, characterCount, stringLengthReference, message);

		if(success){
			l = characterCount.numberValue;
			string = new char [(int)(l)];

			c = 0d;
			string[(int)(c)] = '\"';
			c = c + 1d;

			done = false;
			for(i = start + 1d; !done; i = i + 1d){
				if(json[(int)(i)] == '\\'){
					i = i + 1d;
					if(json[(int)(i)] == '\"' || json[(int)(i)] == '\\' || json[(int)(i)] == '/'){
						string[(int)(c)] = json[(int)(i)];
						c = c + 1d;
					}else if(json[(int)(i)] == 'b'){
						string[(int)(c)] = (char)(8d);
						c = c + 1d;
					}else if(json[(int)(i)] == 'f'){
						string[(int)(c)] = (char)(12d);
						c = c + 1d;
					}else if(json[(int)(i)] == 'n'){
						string[(int)(c)] = (char)(10d);
						c = c + 1d;
					}else if(json[(int)(i)] == 'r'){
						string[(int)(c)] = (char)(13d);
						c = c + 1d;
					}else if(json[(int)(i)] == 't'){
						string[(int)(c)] = (char)(9d);
						c = c + 1d;
					}else if(json[(int)(i)] == 'u'){
						i = i + 1d;
						hex[0] = charToUpperCase(json[(int)(i + 0d)]);
						hex[1] = charToUpperCase(json[(int)(i + 1d)]);
						hex[2] = charToUpperCase(json[(int)(i + 2d)]);
						hex[3] = charToUpperCase(json[(int)(i + 3d)]);
						nCreateNumberFromStringWithCheck(hex, 16d, hexReference, errorMessage);
						string[(int)(c)] = (char)(hexReference.numberValue);
						i = i + 3d;
						c = c + 1d;
					}
				}else if(json[(int)(i)] == '\"'){
					string[(int)(c)] = json[(int)(i)];
					c = c + 1d;
					done = true;
				}else{
					string[(int)(c)] = json[(int)(i)];
					c = c + 1d;
				}
			}

			tokenReference.string = string;
			success = true;
		}else{
			message.string = "End of string was not found.".toCharArray();
			success = false;
		}

		return success;
	}

	public static boolean IsValidJSONString(char [] jsonString, StringReference message){
		boolean valid;
		NumberReference numberReference, stringLength;

		numberReference = new NumberReference();
		stringLength = new NumberReference();

		valid = IsValidJSONStringInJSON(jsonString, 0d, numberReference, stringLength, message);

		return valid;
	}

	public static boolean IsValidJSONStringInJSON(char [] json, double start, NumberReference characterCount, NumberReference stringLengthReference, StringReference message){
		boolean success, done;
		double i, j;
		char c;

		success = true;
		done = false;

		characterCount.numberValue = 1d;

		for(i = start + 1d; i < json.length && !done && success; i = i + 1d){
			if(!IsJSONIllegalControllCharacter(json[(int)(i)])){
				if(json[(int)(i)] == '\\'){
					i = i + 1d;
					if(i < json.length){
						if(json[(int)(i)] == '\"' || json[(int)(i)] == '\\' || json[(int)(i)] == '/' || json[(int)(i)] == 'b' || json[(int)(i)] == 'f' || json[(int)(i)] == 'n' || json[(int)(i)] == 'r' || json[(int)(i)] == 't'){
							characterCount.numberValue = characterCount.numberValue + 1d;
						}else if(json[(int)(i)] == 'u'){
							if(i + 4d < json.length){
								for(j = 0d; j < 4d && success; j = j + 1d){
									c = json[(int)(i + j + 1d)];
									if(nCharacterIsNumberCharacterInBase(c, 16d) || c == 'a' || c == 'b' || c == 'c' || c == 'd' || c == 'e' || c == 'f'){
									}else{
										success = false;
										message.string = "\\u must be followed by four hexadecimal digits.".toCharArray();
									}
								}
								characterCount.numberValue = characterCount.numberValue + 1d;
								i = i + 4d;
							}else{
								success = false;
								message.string = "\\u must be followed by four characters.".toCharArray();
							}
						}else{
							success = false;
							message.string = "Escaped character invalid.".toCharArray();
						}
					}else{
						success = false;
						message.string = "There must be at least two character after string escape.".toCharArray();
					}
				}else if(json[(int)(i)] == '\"'){
					characterCount.numberValue = characterCount.numberValue + 1d;
					done = true;
				}else{
					characterCount.numberValue = characterCount.numberValue + 1d;
				}
			}else{
				success = false;
				message.string = "Unicode code points 0-31 not allowed in JSON string.".toCharArray();
			}
		}

		if(done){
			stringLengthReference.numberValue = i - start;
		}else{
			success = false;
			message.string = "String must end with \".".toCharArray();
		}

		return success;
	}

	public static boolean IsJSONIllegalControllCharacter(char c){
		double cnr;
		boolean isControll;

		cnr = c;

		if(cnr >= 0d && cnr < 32d){
			isControll = true;
		}else{
			isControll = false;
		}

		return isControll;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
