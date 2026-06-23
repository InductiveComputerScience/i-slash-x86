package math.Decimal15E2;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;


import static math.math.math.*;

public class Decimal15E2{
	public static double D15Add(double a, double b, BooleanReference overflow){
		double x;

		x = a + b;

		if(x > D15MaxValue() || x < D15MinValue()){
			overflow.booleanValue = true;
			x = 0d;
		}else{
			overflow.booleanValue = false;
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double RoundTo15Digits(double x){
		double p;

		p = floor(log10(x));
		x = x*pow(10d, 15d - p);
		x = Round(x);
		x = x/pow(10d, 15d - p);

		return x;
	}

	public static double D15MaxValue(){
		return +9.99999999999999e99;
	}

	public static double D15MinValue(){
		return -9.99999999999999e99;
	}

	public static double D15Multiply(double a, double b, BooleanReference overflow){
		double x;

		x = a*b;

		if(x > D15MaxValue() || x < D15MinValue()){
			overflow.booleanValue = true;
			x = 0d;
		}else{
			overflow.booleanValue = false;
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Divide(double a, double b, NumberReference reminder, BooleanReference overflow, BooleanReference invalidOperation){
		double x, r;

		if(b != 0d){
			invalidOperation.booleanValue = false;

			x = a/b;
			r = a%b;

			if(x > D15MaxValue() || x < D15MinValue()){
				overflow.booleanValue = true;
				x = 0d;
				r = 0d;
			}else{
				overflow.booleanValue = false;
				x = RoundTo15Digits(x);
				r = RoundTo15Digits(r);
			}
		}else{
			invalidOperation.booleanValue = true;
			overflow.booleanValue = false;
			x = 0d;
			r = 0d;
		}

		reminder.numberValue = r;

		return x;
	}

	public static double D15Exponentiation(double a, double b, BooleanReference overflow, BooleanReference invalidOperation){
		double x;

		if(a == 0d && b == 0d){
			invalidOperation.booleanValue = true;
			overflow.booleanValue = false;
			x = 0d;
		}else if(a < 0d && !IsInteger(b)){
			invalidOperation.booleanValue = true;
			overflow.booleanValue = false;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;

			x = pow(a, b);

			if(x > D15MaxValue() || x < D15MinValue()){
				overflow.booleanValue = true;
				x = 0d;
			}else{
				overflow.booleanValue = false;
				x = RoundTo15Digits(x);
			}
		}

		return x;
	}

	public static double D15Modulus(double a, double b, BooleanReference invalidOperation){
		double x;

		if(a < 0d || b == 0d || b < 0d){
			invalidOperation.booleanValue = true;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;
			x = a%b;
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Logarithm(double a, BooleanReference invalidOperation){
		double x;

		if(a <= 0d){
			invalidOperation.booleanValue = true;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;
			x = log10(a);
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15NaturalLogarithm(double a, BooleanReference invalidOperation){
		double x;

		if(a <= 0d){
			invalidOperation.booleanValue = true;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;
			x = log(a);
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Sin(double a){
		double x;

		x = sin(a);
		x = RoundTo15Digits(x);

		return x;
	}

	public static double D15Cos(double x){
		double a, y, piBy2Part1, piBy2Part2, limit, f;

		x = abs(x);

		limit = PI + 3.1/2d;

		if(x > limit){
			f = floor(x/PI);
			x = x - PI*f;
		}

		piBy2Part1 = +1.57079632679490;
		piBy2Part2 = -3.38076867830836e-15;

		if(x > 3.1/2d && x < 3.3/2d){
			a = x - piBy2Part1;
			a = (double)round(a*pow(10d, 15d))/pow(10d, 15d);
			a = a - piBy2Part2;
			y = -sin(a);
		}else{
			y = cos(x);
			y = RoundTo15Digits(y);
		}

		return y;
	}

	public static double D15Tan(double a, BooleanReference overflow){
		double x;

		x = tan(a);

		if(x > D15MaxValue() || x < D15MinValue()){
			overflow.booleanValue = true;
			x = 0d;
		}else{
			overflow.booleanValue = false;
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Asin(double a, BooleanReference invalidOperation){
		double x;

		if(a < -1d || a > 1d){
			invalidOperation.booleanValue = true;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;
			x = asin(a);
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Acos(double a, BooleanReference invalidOperation){
		double x;

		if(a < -1d || a > 1d){
			invalidOperation.booleanValue = true;
			x = 0d;
		}else{
			invalidOperation.booleanValue = false;
			x = acos(a);
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static double D15Atan(double a){
		double x;

		x = atan(a);
		x = RoundTo15Digits(x);

		return x;
	}

	public static double D15Sqrt(double a){
		double x;

		x = sqrt(a);
		x = RoundTo15Digits(x);

		return x;
	}

	public static double D15Exponential(double a, BooleanReference overflow){
		double x;

		x = exp(a);

		if(x > D15MaxValue() || x < D15MinValue()){
			overflow.booleanValue = true;
			x = 0d;
		}else{
			overflow.booleanValue = false;
			x = RoundTo15Digits(x);
		}

		return x;
	}

	public static char [] Decimal15E2ToString(double decimal){
		double multiplier, inc, i, d;
		double exponent;
		boolean done, isPositive, isPositiveExponent;
		char [] result;
		double len;

		len = 21d;
		/* 1+1+1+14+1+1+2 -- "+0.00000000000000e+00"*/
		result = new char [(int)(len)];

		done = false;
		exponent = 0d;

		if(decimal < 0d){
			isPositive = false;
			decimal = -decimal;
		}else{
			isPositive = true;
		}

		if(decimal == 0d){
			done = true;
		}

		if(!done){
			multiplier = 0d;
			inc = 0d;

			if(decimal < 1d){
				multiplier = 10d;
				inc = -1d;
			}else if(decimal >= 10d){
				multiplier = 0.1;
				inc = 1d;
			}else{
				done = true;
			}

			if(!done){
				exponent = (double)round(log10(decimal));
				exponent = min(99d, exponent);
				exponent = max(-99d, exponent);

				decimal = decimal/pow(10d, exponent);

				/* Adjust*/
				for(; (decimal >= 10d || decimal < 1d) && abs(exponent) < 99d; ){
					decimal = decimal*multiplier;
					exponent = exponent + inc;
				}
			}
		}

		isPositiveExponent = exponent >= 0d;
		if(!isPositiveExponent){
			exponent = -exponent;
		}

		if(isPositive){
			result[0] = '+';
		}else{
			result[0] = '-';
		}

		decimal = (double)round(decimal*pow(10d, 14d));

		d = floor(decimal/pow(10d, 14d));
		result[1] = SingleDigitNumberToCharacter(d);
		decimal = decimal - d*pow(10d, 14d);

		result[2] = '.';

		for(i = 0d; i < 14d; i = i + 1d){
			d = floor(decimal/pow(10d, 13d - i));
			result[(int)(3d + i)] = SingleDigitNumberToCharacter(d);
			decimal = decimal - d*pow(10d, 13d - i);
		}

		result[17] = 'e';

		if(isPositiveExponent){
			result[18] = '+';
		}else{
			result[18] = '-';
		}

		result[19] = SingleDigitNumberToCharacter(floor(exponent/10d));
		result[20] = SingleDigitNumberToCharacter(floor(exponent%10d));

		return result;
	}

	public static char SingleDigitNumberToCharacter(double n){
		char c;

		c = '0';
		if(n == 0d){
			c = '0';
		}else if(n == 1d){
			c = '1';
		}else if(n == 2d){
			c = '2';
		}else if(n == 3d){
			c = '3';
		}else if(n == 4d){
			c = '4';
		}else if(n == 5d){
			c = '5';
		}else if(n == 6d){
			c = '6';
		}else if(n == 7d){
			c = '7';
		}else if(n == 8d){
			c = '8';
		}else if(n == 9d){
			c = '9';
		}

		return c;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
