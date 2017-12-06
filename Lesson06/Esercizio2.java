

import java.util.Random;
import java.util.Scanner;

public class Esercizio2 {

    /*
       Modificando il programma precedente, scrivere un programma che chieda all'utente di indovinare un numero intero N
       genereato randomicamente da Java, tra -100 e 100. Il programma chiederà di indovinare lo stesso numero finché l'utente non
       avrà fornito N: a quel punto il programma terminerà. Se il numero forito dall'utente X è ad una distanza di almeno
       10 stampare fuoco , altrimenti se è compreso tra 10 e 20 stampare fochino, altrimenti stampare acqua.

       * Per la distanza tra due numeri, usare la funzione valore assoluto Math.abs
       * Osserva,
            Random dado = new Random();
            int choice = dado.nextInt(N);
         choice può essere solo un numero tra 0 ed N a valori positivi.
     */

    public static void main(String[] args) {
        Random dado = new Random();
        int choice = dado.nextInt(200)-100;
        int guess;
        Scanner scanner = new Scanner(System.in);
        System.out.print("Guess a number from -100 to 100: ");
        while ((guess = scanner.nextInt()) != choice) {
            int distance = Math.abs(choice - guess);
            if (distance <= 10) {
                System.out.print("Fuoco!: ");
            } else if (distance <= 20) {
                System.out.print("Fuochino!: ");
            } else {
                System.out.print("Acqua. ");
            }
        }
    }

}
