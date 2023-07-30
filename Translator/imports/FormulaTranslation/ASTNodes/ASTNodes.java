package FormulaTranslation.ASTNodes;

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

import static FormulaTranslation.ArithmeticFormulaSymbolicWriter.ArithmeticFormulaSymbolicWriter.*;

import static FormulaTranslation.BitwiseFormulaSymbolicWriter.BitwiseFormulaSymbolicWriter.*;

import static FormulaTranslation.BooleanFormulaFunctionWriter.BooleanFormulaFunctionWriter.*;

public class ASTNodes{
	public static void AddToNumberReference(NumberReference cur, double val){
		cur.numberValue = cur.numberValue + val;
	}

	public static boolean TokenIs(StringArrayReference tokens, NumberReference cur, char [] s){
		return StringsEqual(Index(tokens, cur), s);
	}

	public static boolean NextTokenIs(StringArrayReference tokens, NumberReference cur, char [] s){
		return StringsEqual(NextIndex(tokens, cur), s);
	}

	public static char [] NextIndex(StringArrayReference stringArrayReference, NumberReference index){
		return stringArrayReference.stringArray[(int)(index.numberValue + 1d)].string;
	}

	public static char [] Index(StringArrayReference stringArrayReference, NumberReference index){
		return stringArrayReference.stringArray[(int)(index.numberValue)].string;
	}

	public static void AssignASTNode(ASTNode x, ASTNode a){
		x.leaf = a.leaf;
		x.l = a.l;
		x.r = a.r;
		x.value = a.value;
	}

	public static ASTNode CreateASTNode(ASTNode l, ASTNode r, char [] op){
		ASTNode t;

		t = new ASTNode();
		t.value = op;
		t.l = l;
		t.r = r;
		t.leaf = false;

		return t;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
