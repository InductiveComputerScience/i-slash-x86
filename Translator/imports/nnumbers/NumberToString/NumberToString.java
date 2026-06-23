package nnumbers.NumberToString;

import static java.lang.Math.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import references.references.*;
import static references.references.references.*;

import static math.math.math.*;

import static math.Decimal15E2.Decimal15E2.*;

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


import static nnumbers.NumberComputations.NumberComputations.*;

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

	public static char [] nNumberToHumanReadableShortScale(double n){
		char [] res, suffix;
		boolean hasSuffix;
		double k, M, B, T, Q;

		k = 1000d;
		M = k*1000d;
		B = M*1000d;
		T = B*1000d;
		Q = T*1000d;
		suffix = " ".toCharArray();

		if(n < k){
			hasSuffix = false;
		}else{
			hasSuffix = true;
		}

		if(n >= k && n < M){
			if(n < 10d*k){
				n = Round(n/100d);
				n = n/10d;
			}else{
				n = Round(n/k);
			}
			suffix = "k".toCharArray();
		}else if(n >= M && n < B){
			if(n < 10d*M){
				n = Round(n/(k*100d));
				n = n/10d;
			}else{
				n = Round(n/M);
			}
			suffix = "M".toCharArray();
		}else if(n >= B && n < T){
			if(n < 10d*B){
				n = Round(n/(M*100d));
				n = n/10d;
			}else{
				n = Round(n/B);
			}
			suffix = "B".toCharArray();
		}else if(n >= T && n < Q){
			if(n < 10d*T){
				n = Round(n/(B*100d));
				n = n/10d;
			}else{
				n = Round(n/T);
			}
			suffix = "T".toCharArray();
		}else if(n >= Q){
			if(n < 10d*Q){
				n = Round(n/(T*100d));
				n = n/10d;
			}else{
				n = Round(n/Q);
			}
			suffix = "Q".toCharArray();
		}

		res = nCreateStringDecimalFromNumber(n);
		if(hasSuffix){
			res = AppendString(res, suffix);
		}
        
		return res;
	}

	public static char [] nNumberToHumanReadableBinary(double n){
		char [] res, suffix;
		boolean hasSuffix;
		double Ki, Mi, Gi, Ti, Pi, Ei, Zi, Yi;

		Ki = 1024d;
		Mi = Ki*1024d;
		Gi = Mi*1024d;
		Ti = Gi*1024d;
		Pi = Ti*1024d;
		Ei = Pi*1024d;
		Zi = Ei*1024d;
		Yi = Zi*1024d;
		suffix = " ".toCharArray();

		if(n < Ki){
			hasSuffix = false;
		}else{
			hasSuffix = true;
		}

		if(n >= Ki && n < Mi){
			if(n < 10d*Ki){
				n = Round(n/(Ki/10d));
				n = n/10d;
			}else{
				n = Round(n/Ki);
			}
			suffix = "Ki".toCharArray();
		}else if(n >= Mi && n < Gi){
			if(n < 10d*Mi){
				n = Round(n/(Mi/10d));
				n = n/10d;
			}else{
				n = Round(n/Mi);
			}
			suffix = "Mi".toCharArray();
		}else if(n >= Gi && n < Ti){
			if(n < 10d*Gi){
				n = Round(n/(Gi/10d));
				n = n/10d;
			}else{
				n = Round(n/Gi);
			}
			suffix = "Gi".toCharArray();
		}else if(n >= Ti && n < Pi){
			if(n < 10d*Ti){
				n = Round(n/(Ti/10d));
				n = n/10d;
			}else{
				n = Round(n/Ti);
			}
			suffix = "Ti".toCharArray();
		}else if(n >= Pi && n < Ei){
			if(n < 10d*Pi){
				n = Round(n/(Pi/10d));
				n = n/10d;
			}else{
				n = Round(n/Pi);
			}
			suffix = "Pi".toCharArray();
		}else if(n >= Ei && n < Zi){
			if(n < 10d*Ei){
				n = Round(n/(Ei/10d));
				n = n/10d;
			}else{
				n = Round(n/Ei);
			}
			suffix = "Ei".toCharArray();
		}else if(n >= Zi && n < Yi){
			if(n < 10d*Zi){
				n = Round(n/(Zi/10d));
				n = n/10d;
			}else{
				n = Round(n/Zi);
			}
			suffix = "Zi".toCharArray();
		}else if(n >= Yi){
			if(n < 10d*Yi){
				n = Round(n/(Yi/10d));
				n = n/10d;
			}else{
				n = Round(n/Yi);
			}
			suffix = "Yi".toCharArray();
		}

		res = nCreateStringDecimalFromNumber(n);
		if(hasSuffix){
			res = AppendString(res, suffix);
		}

		return res;
	}

	public static char [] nNumberToHumanReadableMetric(double n){
		char [] res, suffix;
		boolean hasSuffix;
		double k, M, G, T, P, Ex, Z, Y, R, Q;

		k = 1000d;
		M = k*1000d;
		G = M*1000d;
		T = G*1000d;
		P = T*1000d;
		Ex = P*1000d;
		Z = Ex*1000d;
		Y = Z*1000d;
		R = Y*1000d;
		Q = R*1000d;
		suffix = " ".toCharArray();

		if(n < k){
			hasSuffix = false;
		}else{
			hasSuffix = true;
		}

		if(n >= k && n < M){
			if(n < 10d*k){
				n = Round(n/100d);
				n = n/10d;
			}else{
				n = Round(n/k);
			}
			suffix = "k".toCharArray();
		}else if(n >= M && n < G){
			if(n < 10d*M){
				n = Round(n/(k*100d));
				n = n/10d;
			}else{
				n = Round(n/M);
			}
			suffix = "M".toCharArray();
		}else if(n >= G && n < T){
			if(n < 10d*G){
				n = Round(n/(M*100d));
				n = n/10d;
			}else{
				n = Round(n/G);
			}
			suffix = "G".toCharArray();
		}else if(n >= T && n < P){
			if(n < 10d*T){
				n = Round(n/(G*100d));
				n = n/10d;
			}else{
				n = Round(n/T);
			}
			suffix = "T".toCharArray();
		}else if(n >= P && n < Ex){
			if(n < 10d*P){
				n = Round(n/(T*100d));
				n = n/10d;
			}else{
				n = Round(n/P);
			}
			suffix = "P".toCharArray();
		}else if(n >= Ex && n < Z){
			if(n < 10d*Ex){
				n = Round(n/(P*100d));
				n = n/10d;
			}else{
				n = Round(n/Ex);
			}
			suffix = "E".toCharArray();
		}else if(n >= Z && n < Y){
			if(n < 10d*Z){
				n = Round(n/(Ex*100d));
				n = n/10d;
			}else{
				n = Round(n/Z);
			}
			suffix = "Z".toCharArray();
		}else if(n >= Y && n < R){
			if(n < 10d*Y){
				n = Round(n/(Z*100d));
				n = n/10d;
			}else{
				n = Round(n/Y);
			}
			suffix = "Y".toCharArray();
		}else if(n >= R && n < Q){
			if(n < 10d*R){
				n = Round(n/(Y*100d));
				n = n/10d;
			}else{
				n = Round(n/R);
			}
			suffix = "R".toCharArray();
		}else if(n >= Q){
			if(n < 10d*Q){
				n = Round(n/(R*100d));
				n = n/10d;
			}else{
				n = Round(n/Q);
			}
			suffix = "Q".toCharArray();
		}

		res = nCreateStringDecimalFromNumber(n);
		if(hasSuffix){
			res = AppendString(res, suffix);
		}

		return res;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
