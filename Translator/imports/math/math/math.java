package math.math;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;


public class math{
	public static double Negate(double x){
		return -x;
	}

	public static double Positive(double x){
		return +x;
	}

	public static double Factorial(double x){
		double i, f;

		f = 1d;

		for(i = 2d; i <= x; i = i + 1d){
			f = f*i;
		}

		return f;
	}

	public static double Round(double x){
		return floor(x + 0.5);
	}

	public static double BankersRound(double x){
		double r;

		if(Absolute(x - Truncate(x)) == 0.5){
			if(!DivisibleBy(Round(x), 2d)){
				r = Round(x) - 1d;
			}else{
				r = Round(x);
			}
		}else{
			r = Round(x);
		}

		return r;
	}

	public static double Ceil(double x){
		return ceil(x);
	}

	public static double Floor(double x){
		return floor(x);
	}

	public static double Truncate(double x){
		double t;

		if(x >= 0d){
			t = floor(x);
		}else{
			t = ceil(x);
		}

		return t;
	}

	public static double Absolute(double x){
		return abs(x);
	}

	public static double Logarithm(double x){
		return log10(x);
	}

	public static double NaturalLogarithm(double x){
		return log(x);
	}

	public static double Sin(double x){
		return sin(x);
	}

	public static double Cos(double x){
		return cos(x);
	}

	public static double Tan(double x){
		return tan(x);
	}

	public static double Asin(double x){
		return asin(x);
	}

	public static double Acos(double x){
		return acos(x);
	}

	public static double Atan(double x){
		return atan(x);
	}

	public static double Atan2(double y, double x){
		double a;

		/* Atan2 is an invalid operation when x = 0 and y = 0, but this method does not return errors.*/
		a = 0d;

		if(x > 0d){
			a = Atan(y/x);
		}else if(x < 0d && y >= 0d){
			a = Atan(y/x) + PI;
		}else if(x < 0d && y < 0d){
			a = Atan(y/x) - PI;
		}else if(x == 0d && y > 0d){
			a = PI/2d;
		}else if(x == 0d && y < 0d){
			a = -PI/2d;
		}

		return a;
	}

	public static double Squareroot(double x){
		return sqrt(x);
	}

	public static double Exp(double x){
		return exp(x);
	}

	public static boolean DivisibleBy(double a, double b){
		return ((a%b) == 0d);
	}

	public static double Combinations(double n, double k){
		double i, j, c;

		c = 1d;
		j = 1d;
		i = n - k + 1d;

		for(; i <= n; ){
			c = c*i;
			c = c/j;

			i = i + 1d;
			j = j + 1d;
		}

		return c;
	}

	public static double Permutations(double n, double k){
		double i, c;

		c = 1d;

		for(i = n - k + 1d; i <= n; i = i + 1d){
			c = c*i;
		}

		return c;
	}

	public static boolean EpsilonCompare(double a, double b, double epsilon){
		return abs(a - b) < epsilon;
	}

	public static double GreatestCommonDivisor(double a, double b){
		double t;

		for(; b != 0d; ){
			t = b;
			b = a%b;
			a = t;
		}

		return a;
	}

	public static double GCDWithSubtraction(double a, double b){
		double g;

		if(a == 0d){
			g = b;
		}else{
			for(; b != 0d; ){
				if(a > b){
					a = a - b;
				}else{
					b = b - a;
				}
			}

			g = a;
		}

		return g;
	}

	public static boolean IsInteger(double a){
		return (a - floor(a)) == 0d;
	}

	public static boolean GreatestCommonDivisorWithCheck(double a, double b, NumberReference gcdReference){
		boolean success;
		double gcd;

		if(IsInteger(a) && IsInteger(b)){
			gcd = GreatestCommonDivisor(a, b);
			gcdReference.numberValue = gcd;
			success = true;
		}else{
			success = false;
		}

		return success;
	}

	public static double LeastCommonMultiple(double a, double b){
		double lcm;

		if(a > 0d && b > 0d){
			lcm = abs(a*b)/GreatestCommonDivisor(a, b);
		}else{
			lcm = 0d;
		}

		return lcm;
	}

	public static double Sign(double a){
		double s;

		if(a > 0d){
			s = 1d;
		}else if(a < 0d){
			s = -1d;
		}else{
			s = 0d;
		}

		return s;
	}

	public static double Max(double a, double b){
		return max(a, b);
	}

	public static double Min(double a, double b){
		return min(a, b);
	}

	public static double Power(double a, double b){
		return pow(a, b);
	}

	public static double Gamma(double x){
		return LanczosApproximation(x);
	}

	public static double LogGamma(double x){
		return log(Gamma(x));
	}

	public static double LanczosApproximation(double z){
		double [] p;
		double i, y, t, x;

		p = new double [8];
		p[0] = 676.5203681218851;
		p[1] = -1259.1392167224028;
		p[2] = 771.32342877765313;
		p[3] = -176.61502916214059;
		p[4] = 12.507343278686905;
		p[5] = -0.13857109526572012;
		p[6] = 9.9843695780195716e-6;
		p[7] = 1.5056327351493116e-7;

		if(z < 0.5){
			y = PI/(sin(PI*z)*LanczosApproximation(1d - z));
		}else{
			z = z - 1d;
			x = 0.99999999999980993;
			for(i = 0d; i < p.length; i = i + 1d){
				x = x + p[(int)(i)]/(z + i + 1d);
			}
			t = z + p.length - 0.5;
			y = sqrt(2d*PI)*pow(t, z + 0.5)*exp(-t)*x;
		}

		return y;
	}

	public static double Beta(double x, double y){
		return Gamma(x)*Gamma(y)/Gamma(x + y);
	}

	public static double Sinh(double x){
		return (exp(x) - exp(-x))/2d;
	}

	public static double Cosh(double x){
		return (exp(x) + exp(-x))/2d;
	}

	public static double Tanh(double x){
		return Sinh(x)/Cosh(x);
	}

	public static double Cot(double x){
		return 1d/tan(x);
	}

	public static double Sec(double x){
		return 1d/cos(x);
	}

	public static double Csc(double x){
		return 1d/sin(x);
	}

	public static double Coth(double x){
		return Cosh(x)/Sinh(x);
	}

	public static double Sech(double x){
		return 1d/Cosh(x);
	}

	public static double Csch(double x){
		return 1d/Sinh(x);
	}

	public static double Error(double x){
		double y, t, tau, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

		if(x == 0d){
			y = 0d;
		}else if(x < 0d){
			y = -Error(-x);
		}else{
			c1 = -1.26551223;
			c2 = +1.00002368;
			c3 = +0.37409196;
			c4 = +0.09678418;
			c5 = -0.18628806;
			c6 = +0.27886807;
			c7 = -1.13520398;
			c8 = +1.48851587;
			c9 = -0.82215223;
			c10 = +0.17087277;

			t = 1d/(1d + 0.5*abs(x));

			tau = t*exp(-pow(x, 2d) + c1 + t*(c2 + t*(c3 + t*(c4 + t*(c5 + t*(c6 + t*(c7 + t*(c8 + t*(c9 + t*c10)))))))));

			y = 1d - tau;
		}

		return y;
	}

	public static double ErrorInverse(double x){
		double y, a, t;

		a = (8d*(PI - 3d))/(3d*PI*(4d - PI));

		t = 2d/(PI*a) + log(1d - pow(x, 2d))/2d;
		y = Sign(x)*sqrt(sqrt(pow(t, 2d) - log(1d - pow(x, 2d))/a) - t);

		return y;
	}

	public static double FallingFactorial(double x, double n){
		double k, y;

		y = 1d;

		for(k = 0d; k <= n - 1d; k = k + 1d){
			y = y*(x - k);
		}

		return y;
	}

	public static double RisingFactorial(double x, double n){
		double k, y;

		y = 1d;

		for(k = 0d; k <= n - 1d; k = k + 1d){
			y = y*(x + k);
		}

		return y;
	}

	public static double Hypergeometric(double a, double b, double c, double z, double maxIterations, double precision){
		double y;

		if(abs(z) >= 0.5){
			y = pow(1d - z, -a)*HypergeometricDirect(a, c - b, c, z/(z - 1d), maxIterations, precision);
		}else{
			y = HypergeometricDirect(a, b, c, z, maxIterations, precision);
		}

		return y;
	}

	public static double HypergeometricDirect(double a, double b, double c, double z, double maxIterations, double precision){
		double y, yp, n;
		boolean done;

		y = 0d;
		done = false;

		for(n = 0d; n < maxIterations && !done; n = n + 1d){
			yp = RisingFactorial(a, n)*RisingFactorial(b, n)/RisingFactorial(c, n)*pow(z, n)/Factorial(n);
			if(abs(yp) < precision){
				done = true;
			}
			y = y + yp;
		}

		return y;
	}

	public static double BernouilliNumber(double n){
		return AkiyamaTanigawaAlgorithm(n);
	}

	public static double AkiyamaTanigawaAlgorithm(double n){
		double m, j, B;
		double [] A;

		A = new double [(int)(n + 1d)];

		for(m = 0d; m <= n; m = m + 1d){
			A[(int)(m)] = 1d/(m + 1d);
			for(j = m; j >= 1d; j = j - 1d){
				A[(int)(j - 1d)] = j*(A[(int)(j - 1d)] - A[(int)(j)]);
			}
		}

		B = A[0];

		delete(A);

		return B;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
