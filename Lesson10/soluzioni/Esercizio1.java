import java.util.Scanner;

public class Esercizio1 {

    public static void stampaTrasposta(int m[][], int righe, int colonne) {
        // Per trasporre una matrice e stamparla, basta prima scandire per colonne
        for (int j = 0; j<colonne; j++) {
            // ... e poi per righe
            for (int i = 0; i<righe; i++) {
                // E quindi stampare una cella alla volta
                System.out.print(m[i][j]);

                // Delimitatore di fine cella all'interno della riga
                if (i != righe-1)
                    System.out.print(",");
                else
                    // Delimitatore di fine riga
                    System.out.println();
            }
        }
    }

    public static void stampaSommaColonne(int m[][], int righe, int colonne) {
        // Per ogni colonna ...
        for (int j = 0; j<colonne; j++) {
            int count = 0;
            // ... Accedo ad ogni riga ...
            for (int i = 0; i<righe; i++) {
                // ... ed effettuo la somma dei valori
                count += m[i][j];
            }
            // Per ogni valore, ne stampo la somma.
            System.out.println("Somma colonna "+ j+": "+count);
        }
    }

    public static void stampaColonnaSommaMinore(int m[][], int righe, int colonne) {
        int min = Integer.MAX_VALUE;
        int col = -1;
        // Itero ancora una volta per colonne
        for (int j = 0; j<colonne; j++) {
            // Effettuo la somma per righe di ogni singola cella
            int count = 0;
            for (int i = 0; i<righe; i++) {
                count += m[i][j];
            }
            // Stabilisco qual è la colonna con somma minore
            if (count < min) {
                min = count;
                col = j;
            }
        }
        // Se esiste effettivamente una colonna, la stampo
        if (colonne > 0)
        System.out.println("Colonna a somma minore "+ col+": "+min);
    }

    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Inserire il numero di righe: ");
        int nrighe = s.nextInt();
        System.out.print("Inserire il numero di colonne: ");
        int ncolonne = s.nextInt();
        // Inizializzo la matrice
        int m[][] = new int[nrighe][ncolonne];

        // Leggo la matrice per righe...
        for (int i = 0;  i<nrighe; i++) {
            // ... e poi per colonne
            for (int j=0; j<ncolonne; j++) {
                // Quindi, associo il valore letto da terminale alla cella della matrice
                System.out.print("Cella M["+i+"]["+j+"]=");
                m[i][j] = s.nextInt();
            }
        }

        // Stampo la matrice trasposta
        stampaTrasposta(m,nrighe, ncolonne);

        // Stampo la somma delle colonne (della matrice non trasposta)
        stampaSommaColonne(m, nrighe, ncolonne);

        // FACOLTATIVO: stampo qual è la colonna con somma minore
        stampaColonnaSommaMinore(m, nrighe, ncolonne);

    }


}
