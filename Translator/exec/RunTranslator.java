import DataStructures.Array.Structures.Array;
import DataStructures.Array.Structures.DataReference;
import Translator.Translator.Ast;
import Translator.Translator.Translator2;
import references.references.StringReference;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static DataStructures.Array.Arrays.Arrays.CreateArray;
import static Translator.Translator.Translator.*;

public class RunTranslator {
    public static void main(String[] args) throws IOException {
        char [] input;
        Array functions, structures;
        StringReference message, output, cheaderoutput;
        boolean success;

        input = ReadStringFromFile(args[0].toCharArray());

        functions = CreateArray();
        structures = CreateArray();
        message = new StringReference();
        output = new StringReference();
        cheaderoutput = new StringReference();

        // New system
        Array tokens;
        DataReference tokensRef = new DataReference();
        success = Translator2.Tokenize(input, tokensRef, message);
        if(success){
            tokens = tokensRef.data.array;

            Ast ast = new Ast();
            success = Translator2.Parse(tokens, ast, message);

            if(success){
                System.out.println("success");
            }else{
                System.out.println("Failed: " + new String(message.string));
            }
        }else{
            System.out.println("Failed: " + new String(message.string));
        }

        // --

        success = FindFunctionsAndStructures(input, functions, structures, message);
        if(success) {
            success = Translate(input, functions, structures, message, output);
            if(success){
                success = GenerateCHeader(input, functions, structures, message, cheaderoutput);
            }
        }

        if(success) {
            WriteStringToFile(args[1].toCharArray(), output.string);
            WriteStringToFile(args[2].toCharArray(), cheaderoutput.string);
        }else{
            System.out.println("Failed: " + new String(message.string));
        }

    }

    public static char [] ReadStringFromFile(char[] filename) throws IOException {
        Path filePath = Paths.get(new String(filename));

        char [] content = new String(Files.readAllBytes(filePath)).toCharArray();

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
