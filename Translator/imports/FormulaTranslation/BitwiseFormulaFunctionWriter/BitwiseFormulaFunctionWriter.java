package FormulaTranslation.BitwiseFormulaFunctionWriter;

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

import FormulaTranslation.ASTNodes.*;
import static FormulaTranslation.ASTNodes.ASTNodes.*;

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaSymbolicWriter.BitwiseFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class BitwiseFormulaFunctionWriter{
	public static void BitwiseASTToTFormFunctions(ASTNode ast, StringReference tf, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target){
		BooleanArrayReference t;
		NumberReference assignedT;

		t = CreateBooleanArrayReferenceLengthValue(0d, false);
		assignedT = CreateNumberReference(0d);
		tf.string = new char [0];

		BitwiseASTToTFormFunctionsInner(ast, tf, t, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, 0d);
	}

	public static void BitwiseASTToTFormFunctionsInner(ASTNode ast, StringReference tf, BooleanArrayReference ts, NumberReference assignedT, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target, double level){
		double tl, tr, t;
		char [] functionName, value, numberString;

		tl = 0d;
		tr = 0d;
		t = 0d;

		if(!ast.leaf){
			if(StringsEqual(ast.value, "&".toCharArray()) || StringsEqual(ast.value, "|".toCharArray()) || StringsEqual(ast.value, "^".toCharArray()) || StringsEqual(ast.value, "<<".toCharArray()) || StringsEqual(ast.value, ">>".toCharArray())){
				if(!ast.l.leaf){
					BitwiseASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tl = assignedT.numberValue;
				}

				if(!ast.r.leaf){
					BitwiseASTToTFormFunctionsInner(ast.r, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tr = assignedT.numberValue;
				}

				if(!ast.l.leaf){
					FreeTVariable(ts, tl);
				}

				if(!ast.r.leaf){
					FreeTVariable(ts, tr);
				}

				functionName = BitwiseBinarySymbolToFunctionName(ast.value);

				tf.string = AppendString(tf.string, prefix);
				tf.string = AppendString(tf.string, functionName);
				tf.string = AppendString(tf.string, postfix);
				if(parenthesis){
					tf.string = AppendString(tf.string, "(".toCharArray());
				}else{
					tf.string = AppendString(tf.string, " ".toCharArray());
				}

				if(level > 0d || target.length == 0d){
					t = AllocateTVariable(ts);
					assignedT.numberValue = t;
					tf.string = AppendString(tf.string, "t".toCharArray());
					tf.string = AppendString(tf.string, tprefix);
					numberString = nCreateStringDecimalFromNumber(t);
					tf.string = AppendString(tf.string, numberString);
				}else{
					tf.string = AppendString(tf.string, target);
				}

				tf.string = AppendString(tf.string, ", ".toCharArray());

				if(ast.l.leaf){
					if(charIsNumber(ast.l.value[0])){
						value = new char [0];
						value = AppendString(value, prefix);
						if(wrappedNumber){
							value = AppendString(value, "Number".toCharArray());
							value = AppendString(value, postfix);
							value = AppendString(value, "(".toCharArray());
						}
						value = AppendString(value, ast.l.value);
						if(wrappedNumber){
							value = AppendString(value, ")".toCharArray());
						}
					}else{
						value = ast.l.value;
					}
					tf.string = AppendString(tf.string, value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					tf.string = AppendString(tf.string, tprefix);
					numberString = nCreateStringDecimalFromNumber(tl);
					tf.string = AppendString(tf.string, numberString);
				}
				tf.string = AppendString(tf.string, ", ".toCharArray());
				if(ast.r.leaf){
					if(charIsNumber(ast.r.value[0])){
						value = new char [0];
						value = AppendString(value, prefix);
						if(wrappedNumber){
							value = AppendString(value, "Number".toCharArray());
							value = AppendString(value, postfix);
							value = AppendString(value, "(".toCharArray());
						}
						value = AppendString(value, ast.r.value);
						if(wrappedNumber){
							value = AppendString(value, ")".toCharArray());
						}
					}else{
						value = ast.r.value;
					}
					tf.string = AppendString(tf.string, value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					tf.string = AppendString(tf.string, tprefix);
					numberString = nCreateStringDecimalFromNumber(tr);
					tf.string = AppendString(tf.string, numberString);
				}
				if(parenthesis){
					tf.string = AppendString(tf.string, ")".toCharArray());
				}else{
					tf.string = AppendString(tf.string, " ".toCharArray());
				}
				if(semicolon){
					tf.string = AppendString(tf.string, ";".toCharArray());
				}
				tf.string = AppendString(tf.string, "\n".toCharArray());
			}else if(StringsEqual(ast.value, "()".toCharArray())){
				BitwiseASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
			}else if(StringsEqual(ast.value, "~".toCharArray())){
				if(!ast.l.leaf){
					BitwiseASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tl = assignedT.numberValue;
					FreeTVariable(ts, tl);
				}

				functionName = BitwiseBinarySymbolToFunctionName(ast.value);

				tf.string = AppendString(tf.string, prefix);
				tf.string = AppendString(tf.string, functionName);
				tf.string = AppendString(tf.string, postfix);
				if(parenthesis){
					tf.string = AppendString(tf.string, "(".toCharArray());
				}else{
					tf.string = AppendString(tf.string, " ".toCharArray());
				}

				if(level > 0d || target.length == 0d){
					t = AllocateTVariable(ts);
					assignedT.numberValue = t;
					tf.string = AppendString(tf.string, "t".toCharArray());
					tf.string = AppendString(tf.string, tprefix);
					numberString = nCreateStringDecimalFromNumber(t);
					tf.string = AppendString(tf.string, numberString);
				}else{
					tf.string = AppendString(tf.string, target);
				}

				tf.string = AppendString(tf.string, ", ".toCharArray());

				if(ast.l.leaf){
					tf.string = AppendString(tf.string, ast.l.value);
				}else{
					tf.string = AppendString(tf.string, "t".toCharArray());
					tf.string = AppendString(tf.string, tprefix);
					numberString = nCreateStringDecimalFromNumber(tl);
					tf.string = AppendString(tf.string, numberString);
				}
				if(parenthesis){
					tf.string = AppendString(tf.string, ")".toCharArray());
				}else{
					tf.string = AppendString(tf.string, " ".toCharArray());
				}
				if(semicolon){
					tf.string = AppendString(tf.string, ";".toCharArray());
				}
				tf.string = AppendString(tf.string, "\n".toCharArray());
			}else{
				tf.string = AppendString(tf.string, "<failed>".toCharArray());
			}
		}else{
			tf.string = AppendString(tf.string, "<failed>".toCharArray());
		}
	}

	public static char [] BitwiseBinarySymbolToFunctionName(char [] value){
		char [] f;

		f = "Unknown".toCharArray();

		if(StringsEqual(value, "~".toCharArray())){
			f = "Not".toCharArray();
		}
		if(StringsEqual(value, "&".toCharArray())){
			f = "And".toCharArray();
		}
		if(StringsEqual(value, "|".toCharArray())){
			f = "Or".toCharArray();
		}
		if(StringsEqual(value, "^".toCharArray())){
			f = "Xor".toCharArray();
		}
		if(StringsEqual(value, "<<".toCharArray())){
			f = "ShiftLeft".toCharArray();
		}
		if(StringsEqual(value, ">>".toCharArray())){
			f = "ShiftRight".toCharArray();
		}

		return f;
	}

	public static boolean BitwiseFormulaToTFormFunctions(char [] f, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target, StringReference tf, StringReference message){
		StringArrayReference tokens;
		boolean success;
		ASTNode ast;
		NumberReference pos;

		tokens = new StringArrayReference();
		success = TokenizeBitwiseFormula(f, tokens, message);

		if(success){
			/* Parse*/
			ast = new ASTNode();
			pos = CreateNumberReference(0d);
			success = ParseBitwiseTokens(tokens, pos, ast, message);

			if(success){
				BitwiseASTToTFormFunctions(ast, tf, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target);
			}
		}

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
