package m18math.math;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;


public class math{
	public static double m18Negate(double x){
		return -x;
	}

	public static double m18Positive(double x){
		return +x;
	}

	public static double m18Factorial(double x){
		double i, f;

		f = 1d;

		for(i = 2d; i <= x; i = i + 1d){
			f = f*i;
		}

		return f;
	}

	public static double m18Round(double x){
		return floor(x + 0.5);
	}

	public static double m18BankersRound(double x){
		double r;

		if(m18Absolute(x - m18Truncate(x)) == 0.5){
			if(!m18DivisibleBy(m18Round(x), 2d)){
				r = m18Round(x) - 1d;
			}else{
				r = m18Round(x);
			}
		}else{
			r = m18Round(x);
		}

		return r;
	}

	public static double m18Ceil(double x){
		return ceil(x);
	}

	public static double m18Floor(double x){
		return floor(x);
	}

	public static double m18Truncate(double x){
		double t;

		if(x >= 0d){
			t = floor(x);
		}else{
			t = ceil(x);
		}

		return t;
	}

	public static double m18Absolute(double x){
		return abs(x);
	}

	public static double m18Logarithm(double x){
		return log10(x);
	}

	public static double m18NaturalLogarithm(double x){
		return log(x);
	}

	public static double m18Sin(double x){
		return sin(x);
	}

	public static double m18Cos(double x){
		return cos(x);
	}

	public static double m18Tan(double x){
		return tan(x);
	}

	public static double m18Asin(double x){
		return asin(x);
	}

	public static double m18Acos(double x){
		return acos(x);
	}

	public static double m18Atan(double x){
		return atan(x);
	}

	public static double m18Atan2(double y, double x){
		double a;

		/* Atan2 is an invalid operation when x = 0 and y = 0, but this method does not return errors.*/
		a = 0d;

		if(x > 0d){
			a = m18Atan(y/x);
		}else if(x < 0d && y >= 0d){
			a = m18Atan(y/x) + PI;
		}else if(x < 0d && y < 0d){
			a = m18Atan(y/x) - PI;
		}else if(x == 0d && y > 0d){
			a = PI/2d;
		}else if(x == 0d && y < 0d){
			a = -PI/2d;
		}

		return a;
	}

	public static double m18Squareroot(double x){
		return sqrt(x);
	}

	public static double m18Exp(double x){
		return exp(x);
	}

	public static boolean m18DivisibleBy(double a, double b){
		return ((a%b) == 0d);
	}

	public static double m18Combinations(double n, double k){
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

	public static double m18Permutations(double n, double k){
		double i, c;

		c = 1d;

		for(i = n - k + 1d; i <= n; i = i + 1d){
			c = c*i;
		}

		return c;
	}

	public static boolean m18EpsilonCompare(double a, double b, double epsilon){
		return abs(a - b) < epsilon;
	}

	public static double m18GreatestCommonDivisor(double a, double b){
		double t;

		for(; b != 0d; ){
			t = b;
			b = a%b;
			a = t;
		}

		return a;
	}

	public static double m18GCDWithSubtraction(double a, double b){
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

	public static boolean m18IsInteger(double a){
		return (a - floor(a)) == 0d;
	}

	public static boolean m18GreatestCommonDivisorWithCheck(double a, double b, NumberReference gcdReference){
		boolean success;
		double gcd;

		if(m18IsInteger(a) && m18IsInteger(b)){
			gcd = m18GreatestCommonDivisor(a, b);
			gcdReference.numberValue = gcd;
			success = true;
		}else{
			success = false;
		}

		return success;
	}

	public static double m18LeastCommonMultiple(double a, double b){
		double lcm;

		if(a > 0d && b > 0d){
			lcm = abs(a*b)/m18GreatestCommonDivisor(a, b);
		}else{
			lcm = 0d;
		}

		return lcm;
	}

	public static double m18Sign(double a){
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

	public static double m18Max(double a, double b){
		return max(a, b);
	}

	public static double m18Min(double a, double b){
		return min(a, b);
	}

	public static double m18Power(double a, double b){
		return pow(a, b);
	}

	public static double m18Gamma(double x){
		return m18LanczosApproximation(x);
	}

	public static double m18LogGamma(double x){
		return log(m18Gamma(x));
	}

	public static double m18LanczosApproximation(double z){
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
			y = PI/(sin(PI*z)*m18LanczosApproximation(1d - z));
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

	public static double m18Beta(double x, double y){
		return m18Gamma(x)*m18Gamma(y)/m18Gamma(x + y);
	}

	public static double m18Sinh(double x){
		return (exp(x) - exp(-x))/2d;
	}

	public static double m18Cosh(double x){
		return (exp(x) + exp(-x))/2d;
	}

	public static double m18Tanh(double x){
		return m18Sinh(x)/m18Cosh(x);
	}

	public static double m18Cot(double x){
		return 1d/tan(x);
	}

	public static double m18Sec(double x){
		return 1d/cos(x);
	}

	public static double m18Csc(double x){
		return 1d/sin(x);
	}

	public static double m18Coth(double x){
		return m18Cosh(x)/m18Sinh(x);
	}

	public static double m18Sech(double x){
		return 1d/m18Cosh(x);
	}

	public static double m18Csch(double x){
		return 1d/m18Sinh(x);
	}

	public static double m18Error(double x){
		double y, t, tau, c1, c2, c3, c4, c5, c6, c7, c8, c9, c10;

		if(x == 0d){
			y = 0d;
		}else if(x < 0d){
			y = -m18Error(-x);
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

	public static double m18ErrorInverse(double x){
		double y, a, t;

		a = (8d*(PI - 3d))/(3d*PI*(4d - PI));

		t = 2d/(PI*a) + log(1d - pow(x, 2d))/2d;
		y = m18Sign(x)*sqrt(sqrt(pow(t, 2d) - log(1d - pow(x, 2d))/a) - t);

		return y;
	}

	public static double m18FallingFactorial(double x, double n){
		double k, y;

		y = 1d;

		for(k = 0d; k <= n - 1d; k = k + 1d){
			y = y*(x - k);
		}

		return y;
	}

	public static double m18RisingFactorial(double x, double n){
		double k, y;

		y = 1d;

		for(k = 0d; k <= n - 1d; k = k + 1d){
			y = y*(x + k);
		}

		return y;
	}

	public static double m18Hypergeometric(double a, double b, double c, double z, double maxIterations, double precision){
		double y;

		if(abs(z) >= 0.5){
			y = pow(1d - z, -a)*m18HypergeometricDirect(a, c - b, c, z/(z - 1d), maxIterations, precision);
		}else{
			y = m18HypergeometricDirect(a, b, c, z, maxIterations, precision);
		}

		return y;
	}

	public static double m18HypergeometricDirect(double a, double b, double c, double z, double maxIterations, double precision){
		double y, yp, n;
		boolean done;

		y = 0d;
		done = false;

		for(n = 0d; n < maxIterations && !done; n = n + 1d){
			yp = m18RisingFactorial(a, n)*m18RisingFactorial(b, n)/m18RisingFactorial(c, n)*pow(z, n)/m18Factorial(n);
			if(abs(yp) < precision){
				done = true;
			}
			y = y + yp;
		}

		return y;
	}

	public static double m18BernouilliNumber(double n){
		return m18AkiyamaTanigawaAlgorithm(n);
	}

	public static double m18AkiyamaTanigawaAlgorithm(double n){
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
