import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Interpreter extends MiniQueryParser {

    public Interpreter(File f) throws FileNotFoundException {
        super(new FileInputStream(f));
    }

}
