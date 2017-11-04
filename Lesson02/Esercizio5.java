import java.util.Scanner;

public class Esercizio5 {

    public static void main(String[] args) {
        /*
           Dato un intero letto da terminale, stampare la lista di tutti i suoi divisori (interi)
         */

        /*
           Given an integer read via terminal, print a list of all its divisors (integers)
         */
        int n = new Scanner(System.in).nextInt();
        String factors = "1";
        for (int i = 2; i < n; i++) {
            factors += (n % i == 0 ? (" "+i) : "");
        }
        factors += (" " + n);
        System.out.println(factors);
    }

}
