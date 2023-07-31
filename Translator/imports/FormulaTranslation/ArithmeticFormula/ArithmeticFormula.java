package FormulaTranslation.ArithmeticFormula;

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

import static FormulaTranslation.BitwiseFormula.BitwiseFormula.*;

import static FormulaTranslation.ArithmeticFormulaFunctionWriter.ArithmeticFormulaFunctionWriter.*;

import static FormulaTranslation.TS.TS.*;

import static FormulaTranslation.BooleanFormulaSymbolicWriter.BooleanFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaFunctionWriter.BitwiseFormulaFunctionWriter.*;

import FormulaTranslation.ASTNodes.*;
import static FormulaTranslation.ASTNodes.ASTNodes.*;

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaSymbolicWriter.BitwiseFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class ArithmeticFormula{
	public static boolean TokenizeArithmeticFormula(char [] f, StringArrayReference tokens, StringReference errorMessage){
		boolean success;
		double i;
		LinkedListStrings ll;
		LinkedListCharacters llc;
		char [] str;

		success = true;
		ll = CreateLinkedListString();
		llc = CreateLinkedListCharacter();

		for(i = 0d; i < f.length && success; ){
			if(f[(int)(i)] == '('){
				LinkedListAddString(ll, "(".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == ')'){
				LinkedListAddString(ll, ")".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '+'){
				LinkedListAddString(ll, "+".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '-'){
				LinkedListAddString(ll, "-".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '*'){
				LinkedListAddString(ll, "*".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '/'){
				LinkedListAddString(ll, "/".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '^'){
				LinkedListAddString(ll, "^".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '%'){
				LinkedListAddString(ll, "%".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == ' ' || f[(int)(i)] == '\t' || f[(int)(i)] == '\n'){
				i = i + 1d;
			}else if(charIsLetter(f[(int)(i)])){
				for(; i < f.length && (charIsLetter(f[(int)(i)]) || charIsNumber(f[(int)(i)]) || f[(int)(i)] == '_'); i = i + 1d){
					LinkedListAddCharacter(llc, f[(int)(i)]);
				}
				str = LinkedListCharactersToArray(llc);
				LinkedListAddString(ll, str);
				FreeLinkedListCharacter(llc);
				llc = CreateLinkedListCharacter();
			}else if(charIsNumber(f[(int)(i)])){
				for(; i < f.length && (charIsNumber(f[(int)(i)]) || f[(int)(i)] == '.'); i = i + 1d){
					LinkedListAddCharacter(llc, f[(int)(i)]);
				}
				str = LinkedListCharactersToArray(llc);
				LinkedListAddString(ll, str);
				FreeLinkedListCharacter(llc);
				llc = CreateLinkedListCharacter();
			}else{
				success = false;
				errorMessage.string = "Unknown token in formula.".toCharArray();
			}
		}

		FreeLinkedListCharacter(llc);
		if(success){
			LinkedListAddString(ll, "<end>".toCharArray());
			tokens.stringArray = LinkedListStringsToArray(ll);
		}
		FreeLinkedListString(ll);

		return success;
	}

	public static boolean ParseArithmeticTokens(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;

		success = ParseAdditionOrSubtraction(tokens, cur, ast, errorMessage);

		if(success){
			success = TokenIs(tokens, cur, "<end>".toCharArray());

			if(!success){
				errorMessage.string = "Expected the end of the formula.".toCharArray();
			}
		}

		return success;
	}

	public static boolean ParseAdditionOrSubtraction(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseMultiplicationOrDivision(tokens, cur, t, errorMessage);

		for(; success && (TokenIs(tokens, cur, "+".toCharArray()) || TokenIs(tokens, cur, "-".toCharArray())); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseMultiplicationOrDivision(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseMultiplicationOrDivision(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseExponentiation(tokens, cur, t, errorMessage);

		for(; success && (TokenIs(tokens, cur, "*".toCharArray()) || TokenIs(tokens, cur, "/".toCharArray()) || TokenIs(tokens, cur, "mod".toCharArray())); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseExponentiation(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseExponentiation(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseLiteralVariableUnaryParenthesis(tokens, cur, t, errorMessage);

		if(success && TokenIs(tokens, cur, "^".toCharArray())){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseExponentiation(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseLiteralVariableUnaryParenthesis(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t;
		char [] token;

		success = true;

		token = Index(tokens, cur);
		if(IsKnownArithmeticFunction(token)){
			AddToNumberReference(cur, 1d);

			success = TokenIs(tokens, cur, "(".toCharArray());
			if(success){
				AddToNumberReference(cur, 1d);

				t = new ASTNode();
				success = ParseAdditionOrSubtraction(tokens, cur, t, errorMessage);

				if(success){
					success = TokenIs(tokens, cur, ")".toCharArray());
					if(success){
						ast.leaf = false;
						ast.value = token;
						ast.l = t;

						AddToNumberReference(cur, 1d);
					}else{
						errorMessage.string = "Epected \')\'.".toCharArray();
					}
				}
			}else{
				errorMessage.string = "Epected \'(\'.".toCharArray();
			}
		}else if(charIsLetter(token[0])){
			ast.leaf = true;
			ast.value = token;
			AddToNumberReference(cur, 1d);
		}else if(charIsNumber(token[0])){
			if(NextTokenIs(tokens, cur, "%".toCharArray())){
				ast.leaf = false;
				ast.value = "%".toCharArray();
				ast.l = new ASTNode();
				ast.l.leaf = true;
				ast.l.value = token;
				AddToNumberReference(cur, 1d);
				AddToNumberReference(cur, 1d);
			}else{
				ast.leaf = true;
				ast.value = token;
				AddToNumberReference(cur, 1d);
			}
		}else if(TokenIs(tokens, cur, "(".toCharArray())){
			AddToNumberReference(cur, 1d);

			t = new ASTNode();
			success = ParseAdditionOrSubtraction(tokens, cur, t, errorMessage);

			if(success){
				ast.value = "()".toCharArray();
				ast.l = t;
				ast.leaf = false;

				success = TokenIs(tokens, cur, ")".toCharArray());
				if(success){
					AddToNumberReference(cur, 1d);
				}else{
					errorMessage.string = "Epected \')\'.".toCharArray();
				}
			}
		}else if(TokenIs(tokens, cur, "-".toCharArray()) || TokenIs(tokens, cur, "+".toCharArray())){
			AddToNumberReference(cur, 1d);

			t = new ASTNode();
			success = ParseExponentiation(tokens, cur, t, errorMessage);

			if(success){
				if(StringsEqual(token, "-".toCharArray())){
					ast.value = "unary-".toCharArray();
				}else{
					ast.value = "unary+".toCharArray();
				}
				ast.l = t;
				ast.leaf = false;
			}
		}else{
			success = false;
			errorMessage.string = "Unexpected token.".toCharArray();
		}

		return success;
	}

	public static boolean IsKnownArithmeticFunction(char [] token){
		return StringsEqual(token, "sqrt".toCharArray()) || StringsEqual(token, "ceil".toCharArray()) || StringsEqual(token, "floor".toCharArray()) || StringsEqual(token, "truncate".toCharArray()) || StringsEqual(token, "abs".toCharArray()) || StringsEqual(token, "log".toCharArray()) || StringsEqual(token, "ln".toCharArray()) || StringsEqual(token, "exp".toCharArray()) || StringsEqual(token, "sin".toCharArray()) || StringsEqual(token, "cos".toCharArray()) || StringsEqual(token, "tan".toCharArray()) || StringsEqual(token, "asin".toCharArray()) || StringsEqual(token, "acos".toCharArray()) || StringsEqual(token, "atan".toCharArray());
	}

	public static boolean IsKnownMatrixFunction(char [] token){
		return StringsEqual(token, "trace".toCharArray()) || StringsEqual(token, "inverse".toCharArray()) || StringsEqual(token, "norm".toCharArray()) || StringsEqual(token, "minor".toCharArray());
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
