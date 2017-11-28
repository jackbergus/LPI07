import java.util.Scanner;


/**
 * Creare un programma che, in seguito a dei comandi in input letti dall'utente, effettui alcune operazioni tra vettori,
 * cosi' come proposto nei metodi static che seguono.
 */
public class DaCompletare {

    // a. Creare un metodo statico "arrayFromLine" che, non prendendo alcun input, legga da input un array di double da
    //    tastiera.
    //
    //    L'array viene digitato dall'utente in una sola linea di testo. Utilizzare i metodi utilizzati nella
    //    scorsa esercitazione per leggere tutti i numeri. Si suppone che l'utente non scriva nessun carattere alfabetico
    //    e che inserisca solo numeri e stringhe.
    public static double[] arrayFromLine() {
        // ... COMPLETA, cambiando il return
        return new double[0];
    }

    // b. Crea un metodo "printArray" che, dato come input un array di double, lo stampi a video.
    //    In particolare, stampare un array nel formato seguente: [0.0, 1.23, -2.5, 3.6]
    public static void printArray(double[] toprint) {
        // ... COMPLETA
    }

    // c. Crea un metodo statico "maxArray" che, dato un array di double, ne restituisce il massimo. Se l'array è vuoto, restituisce il minimo valore
    //    (Double.MIN_VALUE)
    public static double maxArray(double[] tomax) {
        // ... COMPLETA, cambiando il return
        return 0;
    }

    // c. Fare un metodo analogo al precedente, ma che restituisca il minimo ("minArray")
    public static double minArray(double[] tomax) {
        // ... COMPLETA, cambiando il return
        return 0;
    }

    // d. Creare un metodo che sommi due array per componente, e restituisca il risultato della somma vettoriale.
    //    Utilizzare "arrayFromLine" per ottenere i due array.
    //    Se due array hanno dimensione differente, sommarli fino alla lunghezza minima delle due componenti
    public static double[] sum() {
        double[] left = arrayFromLine();
        double[] right = arrayFromLine();
        double[] result = new double[Integer.min(left.length, right.length)];
        // ... COMPLETA
        return result;
    }

    // e. Analogamente a d), creare un metodo che faccia il prodotto di due array per componente, e restituisca il
    //    risultato in un vettore.
    public static double[] times() {
        // ... COMPLETA, cambiando il return
        return new double[0];
    }

    // f. Creare un metodo che sommi tutte le componenti di un array, che viene ricevuto come input.
    //    Restituire il valore double
    public static double sum(double[] array) {
        // ... COMPLETA, cambiando il return
        return 0;
    }

    // g. Implementare il prodotto scalare, ovvero effettuare il prodotto per componente, e poi sommare ciascuna componente.
    //    Riutilizzare i comandi precedenti
    public static double scalarProduct() {
        // ... COMPLETA, cambiando il return
        return 0;
    }

    // h. Implementare un metodo che, dato un array di double "a" ed un double "d", restituisca un altro array contentente
    //    tutti quei numeri in "a" la cui parte intera è divisibile per la parte intera di "d".
    //    Hint: attenzione alle divisioni per zero
    public static double[] divisibles(double[] a, double d) {
        // ... COMPLETA, cambiando il return
        return a;
    }

    // i. Implementare un metodo che fornisca l'intersezione di due array. Permettete eventuali elementi ripetuti
    public static double[] intersection() {
        double[] left = arrayFromLine();
        double[] right = arrayFromLine();
        int evaluateInitialSize = 0;
        // ... COMPLETA
        double[] result = new double[evaluateInitialSize];
        // ... COMPLETA
        return result;
    }

    // j. creare nel main un driver che permetta all'utente di scegliere quale operazione vuole effettuare:
    //     - stampi la somma di due array letti da terminale
    //     - stampi la somma di tutti gli elementi di un array letti da terminale
    //     - stampi il prodotto scalare di array letto da terminale
    //     - stampi il massimo di un array letto da terminale
    //     - stampi il minimo di un array letto da terminale
    //     - stampi un sottoarray di elementi divisibili per un altro numero singolo letto dall'utente, dove sia l'array sia l'altro numero sono letti da terminale
    //     - stampi l'intersezione di due array
    //     - permetta all'utente di uscire dal programma (es. scrivendo un qualsiasi altro comando che non esegua i comandi precedenti.)
    public static void main(String args[]) {
        // Finché l'utente non interrompe la lettura scrivendo una linea non prevista
        while (true) {
            System.out.println();
            System.out.println();
            System.out.println("Scrivere uno dei seguenti comandi: ");
            System.out.println("SUM - calcola la somma di due array ");
            System.out.println("SUMMATE - calcola la somma di tutti gli elementi di un array array ");
            System.out.println("MAX - calcola il massimo di due array ");
            System.out.println("MIN - calcola il minimo di due array ");
            System.out.println("DIVIDE - dato un array ed un intero, restituisce tutti i numeri dell'array la cui parte intera è divisibile per l'intero ");
            System.out.println("INTERSECT - stampa l'intersezione di due array ");
            System.out.print("$ ");
            String line = scanner.nextLine();
            if (line.equals("SUM")) {
                printArray(sum());
            } else if (line.equals("SUMMATE")) {
                // ... COMPLETA
            } else if (line.equals("SCALAR")) {
                // ... COMPLETA
            } else if (line.equals("MAX")) {
                // ... COMPLETA
            } else if (line.equals("MIN")) {
                // ... COMPLETA
            } else if (line.equals("DIVIDE")) {
                double[] array = arrayFromLine();
                System.out.print("Provide the divisor: ");
                double div = Double.valueOf(scanner.nextLine());
                // ... COMPLETA
            } else if (line.equals("INTERSECT")) {
                // ... COMPLETA
            } else {
                System.err.println("Esco.");
                break;
            }
        }
    }

}
