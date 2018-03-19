import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Client {

    public static void main(String args[]) {
        MiniQueryParser client = new MiniQueryParser(System.in);
        client.evaluate();
    }

}
