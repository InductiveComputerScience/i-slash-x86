package testing.testing;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static arrays.arrays.arrays.*;


public class testing{
	public static void AssertFalse(boolean b, NumberReference failures){
		if(b){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertTrue(boolean b, NumberReference failures){
		if(!b){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertEquals(double a, double b, NumberReference failures){
		if(a != b){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertBooleansEqual(boolean a, boolean b, NumberReference failures){
		if(a != b){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertCharactersEqual(char a, char b, NumberReference failures){
		if(a != b){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertStringEquals(char [] a, char [] b, NumberReference failures){
		if(!StringsEqual(a, b)){
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertNumberArraysEqual(double [] a, double [] b, NumberReference failures){
		double i;

		if(a.length == b.length){
			for(i = 0d; i < a.length; i = i + 1d){
				AssertEquals(a[(int)(i)], b[(int)(i)], failures);
			}
		}else{
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertBooleanArraysEqual(boolean [] a, boolean [] b, NumberReference failures){
		double i;

		if(a.length == b.length){
			for(i = 0d; i < a.length; i = i + 1d){
				AssertBooleansEqual(a[(int)(i)], b[(int)(i)], failures);
			}
		}else{
			failures.numberValue = failures.numberValue + 1d;
		}
	}

	public static void AssertStringArraysEqual(StringReference [] a, StringReference [] b, NumberReference failures){
		double i;

		if(a.length == b.length){
			for(i = 0d; i < a.length; i = i + 1d){
				AssertStringEquals(a[(int)(i)].string, b[(int)(i)].string, failures);
			}
		}else{
			failures.numberValue = failures.numberValue + 1d;
		}
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
