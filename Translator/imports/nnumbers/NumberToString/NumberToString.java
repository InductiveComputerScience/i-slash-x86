package nnumbers.NumberToString;

import static java.lang.Math.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import references.references.*;
import static references.references.references.*;

import static math.math.math.*;

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


import static nnumbers.StringToNumber.StringToNumber.*;

public class NumberToString{
	public static char [] nCreateStringScientificNotationDecimalFromNumber(double n){
		StringReference mantissaReference, exponentReference;
		double e;
		boolean isPositive;
		char [] result;

		mantissaReference = new StringReference();
		exponentReference = new StringReference();
		result = new char [0];

		if(n < 0d){
			isPositive = false;
			n = -n;
		}else{
			isPositive = true;
		}

		if(n == 0d){
			e = 0d;
		}else{
			e = nGetFirstDecimalDigitPosition(n);

			if(e < 0d){
				n = n*pow(10d, abs(e));
			}else{
				n = n/pow(10d, e);
			}
		}

		mantissaReference.string = nCreateStringDecimalFromNumber(n);
		exponentReference.string = nCreateStringDecimalFromNumber(e);

		if(!isPositive){
			result = AppendString(result, "-".toCharArray());
		}

		result = AppendString(result, mantissaReference.string);
		result = AppendString(result, "e".toCharArray());
		result = AppendString(result, exponentReference.string);

		return result;
	}

	public static char [] nCreateStringDecimalFromNumber(double number){
		DynamicArrayCharacters string;
		double maximumDigits, i, d, digitPosition, trailingZeros;
		boolean hasPrintedPoint, isPositive, done;
		CharacterReference characterReference;
		char c;
		char [] str;

		string = CreateDynamicArrayCharacters();
		isPositive = true;

		if(number < 0d){
			isPositive = false;
			number = -number;
		}

		if(number == 0d){
			DynamicArrayAddCharacter(string, '0');
		}else{
			characterReference = new CharacterReference();

			maximumDigits = nGetMaximumDigitsForDecimal();

			digitPosition = nGetFirstDecimalDigitPosition(number);

			hasPrintedPoint = false;

			if(!isPositive){
				DynamicArrayAddCharacter(string, '-');
			}

			/* Print leading zeros.*/
			if(digitPosition < 0d){
				DynamicArrayAddCharacter(string, '0');
				DynamicArrayAddCharacter(string, '.');
				hasPrintedPoint = true;
				for(i = 0d; i < -digitPosition - 1d; i = i + 1d){
					DynamicArrayAddCharacter(string, '0');
				}
			}

			/* Count trailing zeros*/
			trailingZeros = 0d;
			done = false;
			for(i = 0d; i < maximumDigits && !done; i = i + 1d){
				d = nGetDecimalDigitWithFirstDigitPosition(number, digitPosition, maximumDigits - i - 1d);
				if(d == 0d){
					trailingZeros = trailingZeros + 1d;
				}else{
					done = true;
				}
			}

			/* Print number.*/
			for(i = 0d; i < maximumDigits; i = i + 1d){
				d = nGetDecimalDigitWithFirstDigitPosition(number, digitPosition, i);

				if(!hasPrintedPoint && digitPosition - i + 1d == 0d){
					if(maximumDigits - i > trailingZeros){
						DynamicArrayAddCharacter(string, '.');
					}
					hasPrintedPoint = true;
				}

				if(maximumDigits - i <= trailingZeros && hasPrintedPoint){
				}else{
					nGetDecimalDigitCharacterFromNumberWithCheck(d, characterReference);
					c = characterReference.characterValue;
					DynamicArrayAddCharacter(string, c);
				}
			}

			/* Print trailing zeros.*/
			for(i = 0d; i < digitPosition - maximumDigits + 1d; i = i + 1d){
				DynamicArrayAddCharacter(string, '0');
			}
		}

		/* Done*/
		str = DynamicArrayCharactersToArray(string);
		FreeDynamicArrayCharacters(string);
		return str;
	}

	public static boolean nCreateStringFromNumberWithCheck(double number, double base, StringReference stringRef){
		DynamicArrayCharacters string;
		double maximumDigits, i, d, digitPosition, trailingZeros;
		boolean success, hasPrintedPoint, isPositive, done;
		CharacterReference characterReference;
		char c;

		string = CreateDynamicArrayCharacters();
		isPositive = true;

		if(number < 0d){
			isPositive = false;
			number = -number;
		}

		if(number == 0d){
			DynamicArrayAddCharacter(string, '0');
			success = true;
		}else{
			characterReference = new CharacterReference();

			if(IsInteger(base)){
				success = true;

				maximumDigits = nGetMaximumDigitsForBase(base);

				digitPosition = nGetFirstDigitPosition(number, base);

				hasPrintedPoint = false;

				if(!isPositive){
					DynamicArrayAddCharacter(string, '-');
				}

				/* Print leading zeros.*/
				if(digitPosition < 0d){
					DynamicArrayAddCharacter(string, '0');
					DynamicArrayAddCharacter(string, '.');
					hasPrintedPoint = true;
					for(i = 0d; i < -digitPosition - 1d; i = i + 1d){
						DynamicArrayAddCharacter(string, '0');
					}
				}

				/* Count trailing zeros*/
				trailingZeros = 0d;
				done = false;
				for(i = 0d; i < maximumDigits && !done; i = i + 1d){
					d = nGetDigit(number, base, maximumDigits - i - 1d);
					if(d == 0d){
						trailingZeros = trailingZeros + 1d;
					}else{
						done = true;
					}
				}

				/* Print number.*/
				for(i = 0d; i < maximumDigits && success; i = i + 1d){
					d = nGetDigit(number, base, i);

					if(d >= base){
						d = base - 1d;
					}

					if(!hasPrintedPoint && digitPosition - i + 1d == 0d){
						if(maximumDigits - i > trailingZeros){
							DynamicArrayAddCharacter(string, '.');
						}
						hasPrintedPoint = true;
					}

					if(maximumDigits - i <= trailingZeros && hasPrintedPoint){
					}else{
						success = nGetSingleDigitCharacterFromNumberWithCheck(d, base, characterReference);
						if(success){
							c = characterReference.characterValue;
							DynamicArrayAddCharacter(string, c);
						}
					}
				}

				if(success){
					/* Print trailing zeros.*/
					for(i = 0d; i < digitPosition - maximumDigits + 1d; i = i + 1d){
						DynamicArrayAddCharacter(string, '0');
					}
				}
			}else{
				success = false;
			}
		}

		if(success){
			stringRef.string = DynamicArrayCharactersToArray(string);
			FreeDynamicArrayCharacters(string);
		}

		/* Done*/
		return success;
	}

	public static double nGetMaximumDigitsForBase(double base){
		double t;

		t = pow(10d, 15d);
		return floor(log10(t)/log10(base));
	}

	public static double nGetMaximumDigitsForDecimal(){
		return 15d;
	}

	public static double nGetFirstDecimalDigitPosition(double n){
		double power, m, i;
		boolean multiply, done;

		n = abs(n);

		if(n != 0d){
			if(floor(n) < pow(10d, 15d)){
				multiply = true;
			}else{
				multiply = false;
			}

			done = false;
			m = 0d;
			for(i = 0d; !done; i = i + 1d){
				if(multiply){
					m = n*pow(10d, i);
					if(floor(m) >= pow(10d, 14d)){
						done = true;
					}
				}else{
					m = n/pow(10d, i);
					if(floor(m) < pow(10d, 15d)){
						done = true;
					}
				}
			}

			if(multiply){
				power = 15d - i;
			}else{
				power = 15d + i - 2d;
			}

			if(Round(m) >= pow(10d, 15d)){
				power = power + 1d;
			}
		}else{
			power = 1d;
		}

		return power;
	}

	public static double nGetFirstDigitPosition(double n, double base){
		double power, m, i, maximumDigits;
		boolean multiply, done;

		maximumDigits = nGetMaximumDigitsForBase(base);
		n = abs(n);

		if(n != 0d){
			if(floor(n) < pow(base, maximumDigits)){
				multiply = true;
			}else{
				multiply = false;
			}

			done = false;
			m = 0d;
			for(i = 0d; !done; i = i + 1d){
				if(multiply){
					m = n*pow(base, i);
					if(floor(m) >= pow(base, maximumDigits - 1d)){
						done = true;
					}
				}else{
					m = n/pow(base, i);
					if(floor(m) < pow(base, maximumDigits)){
						done = true;
					}
				}
			}

			if(multiply){
				power = maximumDigits - i;
			}else{
				power = maximumDigits + i - 2d;
			}

			if(Round(m) >= pow(base, maximumDigits)){
				power = power + 1d;
			}
		}else{
			power = 1d;
		}

		return power;
	}

	public static boolean nGetSingleDigitCharacterFromNumberWithCheck(double c, double base, CharacterReference characterReference){
		char [] numberTable;
		boolean success;

		numberTable = nGetDigitCharacterTable();

		if(c < base || c < numberTable.length){
			success = true;
			characterReference.characterValue = numberTable[(int)(c)];
		}else{
			success = false;
		}

		return success;
	}

	public static boolean nGetDecimalDigitCharacterFromNumberWithCheck(double c, CharacterReference characterRef){
		char [] numberTable;
		boolean success;

		numberTable = "0123456789".toCharArray();

		if(c >= 0d && c < 10d){
			success = true;
			characterRef.characterValue = numberTable[(int)(c)];
		}else{
			success = false;
		}

		return success;
	}

	public static char [] nGetDigitCharacterTable(){
		char [] numberTable;

		numberTable = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();

		return numberTable;
	}

	public static double nGetDecimalDigit(double n, double index){
		double digitPosition;

		digitPosition = nGetFirstDecimalDigitPosition(n);

		return nGetDecimalDigitWithFirstDigitPosition(n, digitPosition, index);
	}

	public static double nGetDecimalDigitWithFirstDigitPosition(double n, double digitPosition, double index){
		double d, e, m, i;

		n = abs(n);

		e = 15d - digitPosition - 1d;
		if(e < 0d){
			n = (double)round(n/pow(10d, abs(e)));
		}else{
			n = (double)round(n*pow(10d, e));
		}

		m = n;
		d = 0d;
		for(i = 0d; i < 15d - index; i = i + 1d){
			d = (double)round(m%10d);
			m = m - d;
			m = (double)round(m/10d);
		}

		return d;
	}

	public static double nGetDigit(double n, double base, double index){
		double d, digitPosition, e, m, maximumDigits, i;

		n = abs(n);
		maximumDigits = nGetMaximumDigitsForBase(base);
		digitPosition = nGetFirstDigitPosition(n, base);

		e = maximumDigits - digitPosition - 1d;
		if(e < 0d){
			n = (double)round(n/pow(base, abs(e)));
		}else{
			n = (double)round(n*pow(base, e));
		}

		m = n;
		d = 0d;
		for(i = 0d; i < maximumDigits - index; i = i + 1d){
			d = (double)round(m%base);
			m = m - d;
			m = (double)round(m/base);
		}

		return d;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
