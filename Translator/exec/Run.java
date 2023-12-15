import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {

        if(args.length == 4) {
            RunExpressionTranslator.main(new String[]{args[0], args[1]});
            RunTranslator.main(new String[]{args[1], args[2], args[3]});
        }else{
            System.err.println("I/x86 Translator");
            System.err.println("(c) 2023 Martin F. Johansen / inductive.no / progsbase.com");
            System.err.println("https://github.com/InductiveComputerScience/i-slash-x86");
            System.err.println("");
            System.err.println("Usage: <input file> <output intermediate results> <output assembly file> <output c header>");
            System.err.println("");
            System.err.println("For Example: ioc.idx ioc.idx.1 ioc.asm ioc.h");
        }
    }
}
