import java.util.Scanner;

public class Esercizio1 {

    // Leggere da terminale una stringa di double arbitraria separata da uno spazio, e valutare il valore massimo.
    // Read from console an arbitrary space separated string of doubles, and return the maximum value.

    public static void main(String args[]) {
        Scanner scan = new Scanner(System.in);
        while (scan.hasNext()) {
            Double db = Double.MIN_VALUE;
            String arrayNumbers[] = scan.nextLine().trim().split("\\s");
            for (int i = 0; i<arrayNumbers.length; i++) {
                db = Math.max(db, Double.valueOf(arrayNumbers[i]));
            }
            System.out.println(db);
        }
    }

}
