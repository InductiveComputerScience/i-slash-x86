package JSON.Validator;

import static java.lang.Math.*;

import references.references.*;
import static references.references.references.*;

import static strings.stream.stream.*;

import static strings.strings.strings.*;

import static nnumbers.NumberToString.NumberToString.*;

import static nnumbers.StringToNumber.StringToNumber.*;

import static math.math.math.*;

import static arrays.arrays.arrays.*;

import static charCharacters.Characters.Characters.*;

import DataStructures.Array.Structures.*;
import static DataStructures.Array.Structures.Structures.*;

import static DataStructures.Array.Arrays.Arrays.*;


import static JSON.Comparator.Comparator.*;

import static JSON.Writer.Writer.*;

import static JSON.Parser.Parser.*;

import static JSON.TokenReader.TokenReader.*;

import static JSON.LengthComputer.LengthComputer.*;

public class Validator{
	public static boolean IsValidJSON(char [] json, StringReference message){
		boolean success;
		DataReference elementReference;

		elementReference = new DataReference();

		success = ReadJSON(json, elementReference, message);

		if(success){
			FreeData(elementReference.data);
		}

		return success;
	}

  public static void delete(Object object){
    // Java has garbage collection.
  }
}
