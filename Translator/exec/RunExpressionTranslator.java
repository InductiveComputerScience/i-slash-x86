import DataStructures.Array.Structures.Array;
import references.references.StringReference;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static DataStructures.Array.Arrays.Arrays.CreateArray;
import static Translator.Translator.Translator.*;

public class RunExpressionTranslator {
    public static void main(String[] args) throws IOException {
        char [] input = ReadStringFromFile(args[0].toCharArray());
        Array functions, structures;
        StringReference message, output;
        boolean success;

        functions = CreateArray();
        structures = CreateArray();
        message = new StringReference();
        output = new StringReference();

        success = FindFunctionsAndStructures(input, functions, structures, message);
        if(success) {
            success = TranslateExpressions(input, functions, structures, message, output);
        }

        if(success) {
            WriteStringToFile(args[1].toCharArray(), output.string);
        }else{
            System.out.println("Failed: " + new String(message.string));
        }

    }

    public static char [] ReadStringFromFile(char[] filename) throws IOException {
        Path filePath = Path.of(new String(filename));

        char [] content = Files.readString(filePath).toCharArray();

        return content;
    }

    public static void WriteStringToFile(char [] filename, char [] str) throws IOException {
        FileWriter out = new FileWriter(new String(filename));
        BufferedWriter writer = new BufferedWriter(out);
        writer.write(str);
        writer.close();
        out.close();
    }
}
