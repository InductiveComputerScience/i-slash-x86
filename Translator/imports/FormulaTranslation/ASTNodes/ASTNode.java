package FormulaTranslation.ASTNodes;

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

public class ASTNode{
	public char [] value;
	public boolean leaf;
	public ASTNode l;
	public ASTNode r;
}
