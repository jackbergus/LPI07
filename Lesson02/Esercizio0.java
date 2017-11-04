import java.util.Scanner;

public class Esercizio0 {

    public static void main(String args[]) {
        /*
        Scrivere un programma che legga sempre un numero per ogni linea.
        Interrompere l'immissione dell'utente al quinto valore pari.
        Visualizzare la somma di tutti i numeri ottenuti.
         */

        /*
        Write a program that reads always one number per line.
        Stop the user's entry to the fifth even value.
        Display the sum of all numbers obtained.
         */
        Scanner console = new Scanner(System.in);
        int count = 0;
        int operand;
        int sum = 0;
        while (count < 5) {
            count += ((operand = console.nextInt()) % 2 == 0) ? 1 : 0;
            sum += operand;
        }
        System.out.println(sum);
    }

}
