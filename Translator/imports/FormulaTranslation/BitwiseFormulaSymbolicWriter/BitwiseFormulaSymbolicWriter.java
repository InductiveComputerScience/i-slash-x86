package FormulaTranslation.BitwiseFormulaSymbolicWriter;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static nnumbers.NumberToString.NumberToString.*;

import static nnumbers.StringToNumber.StringToNumber.*;

import static charCharacters.Characters.Characters.*;

import static arrays.arrays.arrays.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

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


import static FormulaTranslation.BooleanFormula.BooleanFormula.*;

import static FormulaTranslation.ArithmeticFormula.ArithmeticFormula.*;

import static FormulaTranslation.BitwiseFormula.BitwiseFormula.*;

import static FormulaTranslation.ArithmeticFormulaFunctionWriter.ArithmeticFormulaFunctionWriter.*;

import static FormulaTranslation.TS.TS.*;

import static FormulaTranslation.BooleanFormulaSymbolicWriter.BooleanFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaFunctionWriter.BitwiseFormulaFunctionWriter.*;

import FormulaTranslation.ASTNodes.*;
import static FormulaTranslation.ASTNodes.ASTNodes.*;

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class BitwiseFormulaSymbolicWriter{
	public static char [] ASTToBitwiseFormula(ASTNode ast){
		char [] f, p1, p2;

		if(ast.leaf){
			f = ast.value;
		}else if(StringsEqual(ast.value, "~".toCharArray())){
			f = new char [0];
			p1 = ASTToBitwiseFormula(ast.l);
			f = AppendString(f, "~".toCharArray());
			f = AppendString(f, p1);
		}else if(StringsEqual(ast.value, "()".toCharArray())){
			f = new char [0];
			p1 = ASTToBitwiseFormula(ast.l);
			f = AppendString(f, "(".toCharArray());
			f = AppendString(f, p1);
			f = AppendString(f, ")".toCharArray());
		}else if(StringsEqual(ast.value, "&".toCharArray()) || StringsEqual(ast.value, "|".toCharArray()) || StringsEqual(ast.value, "^".toCharArray()) || StringsEqual(ast.value, "<<".toCharArray()) || StringsEqual(ast.value, ">>".toCharArray())){
			f = new char [0];
			p1 = ASTToBitwiseFormula(ast.l);
			p2 = ASTToBitwiseFormula(ast.r);
			f = AppendString(f, p1);
			f = AppendString(f, " ".toCharArray());
			f = AppendString(f, ast.value);
			f = AppendString(f, " ".toCharArray());
			f = AppendString(f, p2);
		}else{
			f = "<failed>".toCharArray();
		}

		return f;
	}

	public static void BitwiseASTToTForm(ASTNode ast, StringReference tf, NumberReference t){
		double tl, tr;
		StringReference numberString;
		numberString = new StringReference();

		tl = 0d;
		tr = 0d;

		if(!ast.leaf){
			if(StringsEqual(ast.value, "&".toCharArray()) || StringsEqual(ast.value, "|".toCharArray()) || StringsEqual(ast.value, "^".toCharArray()) || StringsEqual(ast.value, "<<".toCharArray()) || StringsEqual(ast.value, ">>".toCharArray())){
				if(!ast.l.leaf){
					BitwiseASTToTForm(ast.l, tf, t);
					tl = t.numberValue;
				}

				if(!ast.r.leaf){
					BitwiseASTToTForm(ast.r, tf, t);
					tr = t.numberValue;
				}

				t.numberValue = t.numberValue + 1d;
				tf.string = AppendString(tf.string, "t".toCharArray());
				nCreateStringFromNumberWithCheck(t.numberValue, 10d, numberString);
				tf.string = AppendString(tf.string, numberString.string);
				tf.string = AppendString(tf.string, " = ".toCharArray());
				if(ast.l.leaf){
					tf.string = AppendString(tf.string, ast.l.value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					nCreateStringFromNumberWithCheck(tl, 10d, numberString);
					tf.string = AppendString(tf.string, numberString.string);
				}
				tf.string = AppendString(tf.string, " ".toCharArray());
				tf.string = AppendString(tf.string, ast.value);
				tf.string = AppendString(tf.string, " ".toCharArray());
				if(ast.r.leaf){
					tf.string = AppendString(tf.string, ast.r.value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					nCreateStringFromNumberWithCheck(tr, 10d, numberString);
					tf.string = AppendString(tf.string, numberString.string);
				}
				tf.string = AppendString(tf.string, ";\n".toCharArray());
			}else if(StringsEqual(ast.value, "()".toCharArray())){
				BitwiseASTToTForm(ast.l, tf, t);
				tl = t.numberValue;

				t.numberValue = t.numberValue + 1d;
				tf.string = AppendString(tf.string, "t".toCharArray());
				nCreateStringFromNumberWithCheck(t.numberValue, 10d, numberString);
				tf.string = AppendString(tf.string, numberString.string);
				tf.string = AppendString(tf.string, " = ".toCharArray());
				tf.string = AppendString(tf.string, "t".toCharArray());
				nCreateStringFromNumberWithCheck(tl, 10d, numberString);
				tf.string = AppendString(tf.string, numberString.string);
				tf.string = AppendString(tf.string, ";\n".toCharArray());
			}else if(StringsEqual(ast.value, "!".toCharArray())){
				if(!ast.l.leaf){
					BitwiseASTToTForm(ast.l, tf, t);
					tl = t.numberValue;
				}

				t.numberValue = t.numberValue + 1d;
				tf.string = AppendString(tf.string, "t".toCharArray());
				nCreateStringFromNumberWithCheck(t.numberValue, 10d, numberString);
				tf.string = AppendString(tf.string, numberString.string);
				tf.string = AppendString(tf.string, " = ".toCharArray());
				tf.string = AppendString(tf.string, "!".toCharArray());
				if(ast.l.leaf){
					tf.string = AppendString(tf.string, ast.l.value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					nCreateStringFromNumberWithCheck(tl, 10d, numberString);
					tf.string = AppendString(tf.string, numberString.string);
				}
				tf.string = AppendString(tf.string, ";\n".toCharArray());
			}else{
				tf.string = AppendString(tf.string, "<failed>".toCharArray());
			}
		}
	}

	public static char [] BitwiseFormulaToTForm(char [] f){
		char [] af;
		StringArrayReference tokens;
		boolean success;
		StringReference errorMessage, tf;
		ASTNode ast;
		NumberReference pos, t;

		tokens = new StringArrayReference();
		errorMessage = new StringReference();
		success = TokenizeBitwiseFormula(f, tokens, errorMessage);

		if(success){
			/* Parse*/
			ast = new ASTNode();
			pos = CreateNumberReference(0d);
			success = ParseBitwiseTokens(tokens, pos, ast, errorMessage);

			if(success){
				tf = CreateStringReference("".toCharArray());
				t = CreateNumberReference(0d);
				BitwiseASTToTForm(ast, tf, t);
				af = tf.string;
			}else{
				af = errorMessage.string;
			}
		}else{
			af = errorMessage.string;
		}

		return af;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
