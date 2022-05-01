import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ExecuteAssignment {


    public static void main(String[] args) throws IOException {
        String file = "InputText.txt";
        Path path = Paths.get("src/main/resources/", file);
        String text = FileUtility.readFile(path, StandardCharsets.US_ASCII);
        System.out.println(Assignment.printPalindromeWords(text));
    }

}
