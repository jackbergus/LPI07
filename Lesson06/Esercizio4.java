

import java.util.Scanner;

public class Esercizio4 {

    /**
     * Stampare tutti i numeri primi compresi tra 2 ed N.
     * Utilizzare il Crivello di Eratostene:
     *
     * 0) Creare un array di booleani tra 2 ed N
     * 2) Sia i il numero corrente nella scansione tra 2 ed N:
     *    se i è primo, allora settare come non primi tutti i multipli di i fino ad N
     * 3) Stampare tutti i numeri se sono stati marcati come primi
     */
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Provide the maximum number N (starting form 2, included): ");
        int max = s.nextInt();
        if (max >= 2) {
            boolean isPrime[] = new boolean[max+1];
            for (int i = 0; i<isPrime.length; i++)
                isPrime[i] = true;

            for (int i = 2; i<=max; i++) {
                if (isPrime[i]) {
                    for (int j = i*2; j<=max; j+=i) {
                        isPrime[j] = false;
                    }
                }
            }

            System.out.println("All the prime numbers: ");
            for (int i = 2; i<=max; i++) {
                if (isPrime[i])
                    System.out.print(i+" ");
            }
            System.out.println("");
        }
    }

}
