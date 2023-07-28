package tests;

import DataStructures.Array.Structures.DataReference;
import lists.LinkedListStrings.Structures.LinkedListStrings;
import references.references.*;

import static JSON.Parser.Parser.*;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.CreateLinkedListString;
import static lists.LinkedListStrings.LinkedListStringsFunctions.LinkedListStringsFunctions.LinkedListStringsLength;
import static references.references.references.*;
import static testing.testing.testing.*;

public class tests {
    public static double test(){
        NumberReference failures;

        failures = CreateNumberReference(0d);

        return failures.numberValue;
    }
}
