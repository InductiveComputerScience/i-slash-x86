package FormulaTranslation.BitwiseFormula;

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

import static FormulaTranslation.ArithmeticFormulaFunctionWriter.ArithmeticFormulaFunctionWriter.*;

import static FormulaTranslation.TS.TS.*;

import static FormulaTranslation.BooleanFormulaSymbolicWriter.BooleanFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaFunctionWriter.BitwiseFormulaFunctionWriter.*;

import FormulaTranslation.ASTNodes.*;
import static FormulaTranslation.ASTNodes.ASTNodes.*;

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaSymbolicWriter.BitwiseFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class BitwiseFormula{
	public static boolean TokenizeBitwiseFormula(char [] f, StringArrayReference tokens, StringReference errorMessage){
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
			}else if(f[(int)(i)] == '~'){
				LinkedListAddString(ll, "~".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '&'){
				LinkedListAddString(ll, "&".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '^'){
				LinkedListAddString(ll, "^".toCharArray());
				i = i + 1d;
			}else if(f[(int)(i)] == '|'){
				LinkedListAddString(ll, "|".toCharArray());
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
			}else if(i + 1d < f.length){
				if(f[(int)(i)] == '>' && f[(int)(i + 1d)] == '>'){
					LinkedListAddString(ll, ">>".toCharArray());
					i = i + 2d;
				}else if(f[(int)(i)] == '<' && f[(int)(i + 1d)] == '<'){
					LinkedListAddString(ll, "<<".toCharArray());
					i = i + 2d;
				}else{
					success = false;
					errorMessage.string = "Unknown token in formula.".toCharArray();
				}
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

	public static boolean ParseBitwiseTokens(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;

		success = ParseOr(tokens, cur, ast, errorMessage);

		if(success){
			success = TokenIs(tokens, cur, "<end>".toCharArray());

			if(!success){
				errorMessage.string = "Expected the end of the formula.".toCharArray();
			}
		}

		return success;
	}

	public static boolean ParseOr(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseXor(tokens, cur, t, errorMessage);

		for(; success && TokenIs(tokens, cur, "|".toCharArray()); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseXor(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseXor(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseAnd(tokens, cur, t, errorMessage);

		for(; success && TokenIs(tokens, cur, "^".toCharArray()); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseAnd(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseAnd(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseShift(tokens, cur, t, errorMessage);

		for(; success && TokenIs(tokens, cur, "&".toCharArray()); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseShift(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseShift(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t, t1;
		char [] op;

		t = new ASTNode();
		success = ParseLiteralVariableNotParenthesis(tokens, cur, t, errorMessage);

		for(; success && (TokenIs(tokens, cur, "<<".toCharArray()) || TokenIs(tokens, cur, ">>".toCharArray())); ){
			op = Index(tokens, cur);
			AddToNumberReference(cur, 1d);
			t1 = new ASTNode();
			success = ParseLiteralVariableNotParenthesis(tokens, cur, t1, errorMessage);
			if(success){
				t = CreateASTNode(t, t1, op);
			}
		}

		if(success){
			AssignASTNode(ast, t);
		}

		return success;
	}

	public static boolean ParseLiteralVariableNotParenthesis(StringArrayReference tokens, NumberReference cur, ASTNode ast, StringReference errorMessage){
		boolean success;
		ASTNode t;
		char [] token;

		success = true;

		token = Index(tokens, cur);
		if(charIsLetter(token[0]) || charIsNumber(token[0])){
			ast.leaf = true;
			ast.value = token;
			AddToNumberReference(cur, 1d);
		}else if(TokenIs(tokens, cur, "(".toCharArray())){
			AddToNumberReference(cur, 1d);

			t = new ASTNode();
			success = ParseOr(tokens, cur, t, errorMessage);

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
		}else if(TokenIs(tokens, cur, "~".toCharArray())){
			AddToNumberReference(cur, 1d);

			t = new ASTNode();
			success = ParseLiteralVariableNotParenthesis(tokens, cur, t, errorMessage);

			if(success){
				ast.value = "~".toCharArray();
				ast.l = t;
				ast.leaf = false;
			}
		}else{
			success = false;
			errorMessage.string = "Unexpected token.".toCharArray();
		}

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
