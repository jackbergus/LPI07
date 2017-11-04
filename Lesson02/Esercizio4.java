import java.util.Scanner;

public class Esercizio4 {

    /*
        Scrivere un programma per poter analizzare una sequenza di numeri separati da spazi in una stessa linea.
        Si vogliono calcolare e stampare su schermo diversi risultati:
        * quanti sono i numeri positivi, nulli e negativi
        * quanti sono i numeri pari e dispari
        * se la sequenza dei numeri inseriti è crescente, decrescente oppure non monotona. Distinguere i casi di sequenza vuota e quando questa è costituita da un solo elemento.
    */

    /*
        Write a program to analyze a sequence of numbers separated by spaces in a same line.
        Calculate and print the following results:
        * the number of positive numbers numbers, negative numbers and zeros
        * The number of odd and even numbers
        * If the sequence of numbers is ascending, descending or non-monotonic. Distinguish also the former cases from empty sequence and when it consists of one single element.

     */

    public static void main(String args[]) {
        String oneLineList = new Scanner(System.in).nextLine().trim();

        int countPositive = 0;
        int countZeros = 0;
        int countNegative = 0;
        int countOdd = 0;
        int countEven = 0;
        int count = 0;
        int previous = 0;
        boolean crescent = true;
        boolean descrecent = true;

        while (!oneLineList.isEmpty()) {
            // Extracting the numbers from the single line
            int current = 0;
            if (oneLineList.contains(" ")) {
                current = Integer.valueOf(oneLineList.substring(0, oneLineList.indexOf(' ')));
                oneLineList = oneLineList.substring(oneLineList.indexOf(' ')).trim();
            } else {
                current = Integer.valueOf(oneLineList);
                oneLineList = "";
            }

            // Count how many numbers are positive, zero or negative
            if (current == 0) {
                countZeros++;
            } else if (current > 0) {
                countPositive++;
            } else {
                countNegative++;
            }

            // Counting the odd and the even numbers
            if (current % 2 == 0) {
                countEven++;
            } else {
                countOdd++;
            }

            if (count > 0) {
                // Checking if the sequence is crescent
                crescent = crescent && (previous < current);

                // Checking if the sequence is decrescent
                descrecent =  descrecent && (previous > current);
            }
            count++;
            previous = current;
        }

        crescent &= (count > 1);
        descrecent &= (count > 1);

        System.out.println("#Positives: " + countPositive);
        System.out.println("#Zeros: " + countZeros);
        System.out.println("#Negatives: " + countNegative);
        System.out.println("#Odds: " + countOdd);
        System.out.println("#Even: " + countEven);
        System.out.println("The sequence is " + (crescent ? "crescent" : (descrecent ? "decrescent" : (count > 1 ? "non-monotone" : (count == 0 ? "empty" : "a singlet")))));
    }

}
