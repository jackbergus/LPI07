

import java.util.Random;
import java.util.Scanner;

public class Esercizio1 {

    /*
       Creare un programma che gioca a testa o croce con l'utente, fiché l'utente scrive "STOP!".
       Gestire liberamente la modalità nel quale il programma chiede di inserire la previsione del giocatore

       Suggerimenti:
       * Creare un nuovo oggetto di classe Random, che è un generatore di numeri casuale. (Random dado = new Random();)
       * Leggere con attenzione i metodi in https://docs.oracle.com/javase/7/docs/api/java/util/Random.html
       * Scegliere di generare valori booleani. Scegliere liberamente quale valore associare al valore testa, e quale
         associare alla croce
         (boolean risultato = dado.....;)

     */

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String line;
        System.out.print("Head or tail? ");
        Random dado = new Random();
        while ((line = scan.nextLine()) != null && !line.equals("STOP!")) {
            line = line.trim().replaceAll("\\s+","");
            boolean risultatoTesta = dado.nextBoolean();
            if (line.equals("head") && risultatoTesta) {
                System.out.println("Bravo!");
            } else if (line.equals("tail") && !risultatoTesta) {
                System.out.println("Bravo!");
            } else {
                System.out.println("Wrong!");
            }
        }
    }

}


