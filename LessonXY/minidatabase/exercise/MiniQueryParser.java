import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

public class MiniQueryParser {

    Scanner console;
    public MiniQueryParser(InputStream in) {
        console = new Scanner(in);
    }

    public void evaluate() {
        System.out.println("Evaluate here");
    }

}
