import java.util.Scanner;

public class Esercizio1 {

    public static void main(String[] args) {
        /*
        Scrivere un programma che legga sempre un numero per ogni linea.
        Sommare ciascun numero ai precedenti, solo se è dispari.
        Visualizzare la somma di tutti i numeri ottenuti appena raggiunge, o supera, 100.
         */

        /*
        Write a program that reads always one number per line.
        Add each number to the previous ones, only if is odd.
        Display the sum of all numbers obtained as soon as the sum reaches, or exceeds, 100.
        */
        Scanner console = new Scanner(System.in);
        int operand;
        int sum = 0;
        while (sum < 100) {
            sum += ((operand = console.nextInt()) % 2 == 1) ? operand : 0;
        }
        System.out.println(sum);
    }

}
