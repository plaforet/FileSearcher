import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SearchHelper {
    public static String readFile(File file) {
        try {
            Scanner s = new Scanner(file, "utf-8");
            String text = "";
            
            while (s.hasNextLine()) {
                text += s.nextLine() + " ";
            }

            s.close();
            
            return text;
        } catch (FileNotFoundException ex) {
            System.out.println("File not found: " + file.toString());
            return "";
        }
    }
}
