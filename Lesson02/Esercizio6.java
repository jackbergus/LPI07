import java.util.Scanner;

public class Esercizio6 {

    /*
       Scrivere un programma che controlli se una stringa fornita in input è palindroma
     */

    /*
       Write a program checking whether the input-provided string is palindrome or not.
     */

    public static void main(String args[]) {
        String s = new Scanner(System.in).nextLine();
        boolean test = true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length()-i-1)) {
                test = false;
                break;
            }
        }
        System.out.println(test);
    }

}
