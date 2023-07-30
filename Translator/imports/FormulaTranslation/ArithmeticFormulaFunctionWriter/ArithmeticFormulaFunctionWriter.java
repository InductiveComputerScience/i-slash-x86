package FormulaTranslation.ArithmeticFormulaFunctionWriter;

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

import static FormulaTranslation.TS.TS.*;

import static FormulaTranslation.BooleanFormulaSymbolicWriter.BooleanFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaFunctionWriter.BitwiseFormulaFunctionWriter.*;

import FormulaTranslation.ASTNodes.*;
import static FormulaTranslation.ASTNodes.ASTNodes.*;

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaSymbolicWriter.BitwiseFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class ArithmeticFormulaFunctionWriter{
	public static void ASTToTFormFunctions(ASTNode ast, StringReference tf, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target){
		BooleanArrayReference t;
		NumberReference assignedT;

		t = CreateBooleanArrayReferenceLengthValue(0d, false);
		assignedT = CreateNumberReference(0d);

		ASTToTFormFunctionsInner(ast, tf, t, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, 0d);
	}

	public static void ASTToTFormFunctionsInner(ASTNode ast, StringReference tf, BooleanArrayReference ts, NumberReference assignedT, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target, double level){
		double tl, tr, t;
		char [] functionName, value, numberString;

		tl = 0d;
		tr = 0d;

		if(!ast.leaf){
			if(StringsEqual(ast.value, "+".toCharArray()) || StringsEqual(ast.value, "-".toCharArray()) || StringsEqual(ast.value, "mod".toCharArray()) || StringsEqual(ast.value, "*".toCharArray()) || StringsEqual(ast.value, "/".toCharArray()) || StringsEqual(ast.value, "^".toCharArray())){
				if(!ast.l.leaf){
					ASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tl = assignedT.numberValue;
				}

				if(!ast.r.leaf){
					ASTToTFormFunctionsInner(ast.r, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tr = assignedT.numberValue;
				}

				functionName = ArithmeticBinarySymbolToFunctionName(ast.value);

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
						if(wrappedNumber){
							value = AppendString(value, prefix);
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

					FreeTVariable(ts, tl);
				}
				tf.string = AppendString(tf.string, ", ".toCharArray());
				if(ast.r.leaf){
					if(charIsNumber(ast.r.value[0])){
						value = new char [0];
						if(wrappedNumber){
							value = AppendString(value, prefix);
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

					FreeTVariable(ts, tr);
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
				ASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
			}else if(StringsEqual(ast.value, "unary-".toCharArray()) || StringsEqual(ast.value, "unary+".toCharArray())){
				if(!ast.l.leaf){
					ASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tl = assignedT.numberValue;
				}

				functionName = ArithmeticBinarySymbolToFunctionName(ast.value);

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

					FreeTVariable(ts, tl);
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
			}else if(IsKnownArithmeticFunction(ast.value)){
				if(!ast.l.leaf){
					ASTToTFormFunctionsInner(ast.l, tf, ts, assignedT, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target, level + 1d);
					tl = assignedT.numberValue;
				}

				functionName = CopyString(ast.value);
				functionName[0] = charToUpperCase(functionName[0]);

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

					FreeTVariable(ts, tl);
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
			}else if(StringsEqual(ast.value, "%".toCharArray())){
				tf.string = AppendString(tf.string, prefix);
				tf.string = AppendString(tf.string, "Percentage".toCharArray());
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

				tf.string = AppendString(tf.string, ast.l.value);
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

	public static char [] ArithmeticBinarySymbolToFunctionName(char [] value){
		char [] f;

		f = "Unknown".toCharArray();

		if(StringsEqual(value, "+".toCharArray())){
			f = "Add".toCharArray();
		}
		if(StringsEqual(value, "-".toCharArray())){
			f = "Sub".toCharArray();
		}
		if(StringsEqual(value, "mod".toCharArray())){
			f = "Mod".toCharArray();
		}
		if(StringsEqual(value, "*".toCharArray())){
			f = "Mul".toCharArray();
		}
		if(StringsEqual(value, "/".toCharArray())){
			f = "Div".toCharArray();
		}
		if(StringsEqual(value, "^".toCharArray())){
			f = "Pow".toCharArray();
		}
		if(StringsEqual(value, "unary-".toCharArray())){
			f = "Negate".toCharArray();
		}
		if(StringsEqual(value, "unary+".toCharArray())){
			f = "Positive".toCharArray();
		}

		return f;
	}

	public static char [] ArithmeticFormulaToTFormFunctions(char [] f, char [] prefix, char [] postfix, char [] tprefix, boolean parenthesis, boolean semicolon, boolean wrappedNumber, char [] target){
		char [] af;
		StringArrayReference tokens;
		boolean success;
		StringReference errorMessage, tf;
		ASTNode ast;
		NumberReference pos, t;

		tokens = new StringArrayReference();
		errorMessage = new StringReference();
		success = TokenizeArithmeticFormula(f, tokens, errorMessage);

		if(success){
			/* Parse*/
			ast = new ASTNode();
			pos = CreateNumberReference(0d);
			success = ParseArithmeticTokens(tokens, pos, ast, errorMessage);

			if(success){
				tf = CreateStringReference("".toCharArray());
				ASTToTFormFunctions(ast, tf, prefix, postfix, tprefix, parenthesis, semicolon, wrappedNumber, target);
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
