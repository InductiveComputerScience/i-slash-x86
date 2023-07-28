import java.io.IOException;

public class Run {
    public static void main(String[] args) throws IOException {
        RunExpressionTranslator.main(new String[]{args[0], args[1]});
        RunTranslator.main(new String[]{args[1], args[2], args[3]});
    }
}
