import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class PalindromeExercise {

    public static boolean isPalindrome(String toCheck) {
        return false;
    }

    public static void main (String args[]) throws IOException {
        StringGenerator sg = new StringGenerator();
        double max = 10000000.0;
        double count = 0;
        String str = "Hello";
        BufferedWriter writer = new BufferedWriter(new FileWriter("errors.txt"));
        
        for (int i = 0; i<(int)max; i++) {
            StringGenerator.RandomString test = sg.generateRandomPalindrome();
            boolean result = isPalindrome(test.left);
            if (result != test.isPalindrome) {
                writer.write("ERROR: " + test.left +" must "+(test.isPalindrome ? "" : "not") + " be palindrome\n");
            } else {
                count++;
            }
        }
        writer.close();
        System.out.println("Score = " + (count/max*30));
        if ((count/max*30) < 30.0) {
            System.out.println("Please check the errors.txt file to look at your errors");
        }
    }

}
