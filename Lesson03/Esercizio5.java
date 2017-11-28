import java.util.Scanner;

public class Esercizio5 {

    // Crea un quadrato magico usando il metodo Siamese per matrici di dimensioni dispari N, riempiendolo con numeri da 1 a N^2.
    // Riempi la matrice a partire dalla cella centrale della prima riga con il numero 1,
    // poi per riempire le scatole muoversi diagonalmente in alto e a destra (↗), un passo alla volta. Quando una mossa
    // uscirebbe dal quadrato, ritorna all'ultima riga o alla prima colonna. Riempi ogni cella con un
    // numero crescente. Se si incontra una cella piena, ci si sposta verticalmente in basso di una casella (↓), quindi continua
    // come prima.
    //
    // Alla fine, fai un po' di debug, e controlla se la matrice ottenuta è un quadrato magico, ogni riga e ogni diagonale
    // devono avere lo stesso valore di somma.

    // Create a magic square using the Siamese method for matrices of odd size N , filling it with numbers from 1 to N^2.
    // Full the matrix starting from the central cell of the first row with the number 1,
    // then always move for filling the boxes is diagonally up and right (↗), one step at a time. When a move
    // would leave the square, it is wrapped around to the last row or first column, respectively. Fill each cell with an
    // increasing number. If a filled box is encountered, one moves vertically down one box (↓) instead, then continuing
    // as before.
    //
    // At the end, do some debugging, and check if the obtained matrix is a magic square, that each each row and diagonal
    // have the same sum value.

    public static void main(String[] args) {
	// Preparo la lettura da terminale
        Scanner scanner = new Scanner(System.in);
	// Chiedo all'utente di fornire un numero dispari
        System.out.print("Odd size of the matrix = ");
	// Ottengo il valore dispari
        int size = Integer.valueOf(scanner.nextLine());
	// Se effettivamente il valore è dispari, computerò qualcosa
        if (size % 2 == 1) {
	    // Creo la nuova matrice, di dimensione size x size
            int[][] matrix = new int[size][size];

	    // Per sicurezza, inizializzo tutti i campi a zero. Dato che, da 
	    // algoritmo indicato sopra, inizierò a riempire la matrice da 1,
            // avrò che la cella vuota di una matrice è rappresentata dalla
            // cella contenente valore zero
            for (int i = 0; i<size; i++)
                for (int j = 0; j<size; j++)
                    matrix[i][j] = 0;

            // Parto a scandire dalla prima riga della matrice ...
            int i = 0;
            // ... partendo dalla cella situata al centro
            int j = (size/2);

            // Devo riempire tutta la matrice. Il costrutto di iterazione for
            // in questo caso non mi aiuta nel capire quale sarà la posizione
            // dove scrivere gli elementi, in quanto di volta in volta dovrò
            // invece opportunamente cambiare i e j in base le condizioni al
            // contorno del mio problema. 
            // Per contro, mi posso chiedere quando dovrò terminare di riempire
            // la mia matrice: potrò interrompere il riempimento della matrice
            // quando avrò inserito tutti i valori da 1 a size * size. 
            // Conseguentemente, "continua a riempire la matrice finché ci sono 
            // elementi da riempire" significa in questo caso "itera da 1 a
            // size*size;
            // Ora, mi devo chiedere che cosa faro ad ogni passo k
            for (int k = 1; k<=size*size; k++) {
                if (k == 1) {
                    matrix[i][j] = k; // Se k == 1, allora ho già pre calcolato
 		    // prima la posizione dalla quale partire
                } else {

                    // Se non ho appena iniziato ad iterare, dovrò spostarmi
                    // 1) Decido come spotarmi lungo le righe
                    int nexti = i == 0 ? size-1 : i-1;
                    //  nexti = posizione che sto calcolando per i
                    //          se (i==0) ---> se mi trovo nella prima riga
                    //                    size -1 ---> allora passa all'ultima riga
                    //                            i-1 --> altrimenti vai alla riga precedente
                    
                    // Alternativa 1:
		    //int nexti = (i == 0 ? size : i);
                    //nexti--;
                    
                    // Alternativa 2:
                    //int nexti;
                    // if (i == 0) {
                    //   i = size-1;
                    // } else {
                    //   i--;
                    // }h

                    // 2) Decido come spostarmi lungo le colonne
                    // In particolare, se mi trovo nell'ultima colonna, inizia
                    // dalla prima, altrimenti passa alla linea successiva
                    int nextj = j == size-1 ? 0 : j+1;

		    // Osserva: i passi 1) e 2) implementano entrambe le specifiche 
                    // che descrivono come spostarsi tra gli elementi delle celle

                    // Se la cella nella posizione corrente non è vuota...
                    if (matrix[nexti][nextj] != 0) {
                        // aggiorno la posizione come in 1), spostandomi nella 
                        // cella sottostante. Quindi, dovrò solo modificare la 
                        // posizione delle righe, perché la posizione della colonna
			// rimarrà quella della posizione precedentemente calcolata.
                        i = i == size-1 ? 0 : i+1;
			System.err.println(nexti + "--" + nextj + " ==> " + i + "--" + j);
			// Osserva che non devo muovermi ↗ e poi ↓, ma di muovermi  
	                // ↓ dalla cella del passo precedente, il che significa 
		        // che debbo muovermi solo lungo le righe. 
                    } else {
			// altrimenti, la posizione calcolata è quella corretta
                        i = nexti;
                    	j = nextj;
                    }
		    // Assegno k nella posizione precalcolata
                    matrix[i][j] = k;
		    // Ora i e j, al passo successivo, memorizzeranno la posizione
                    // salvata al passo precedente
                }
            }

	    // Fase di debugging:
            // 1) Stampo la matrice ottenuta

            // Ottengo di quante cifre è composto il mio numero
            int truncated = (int)Math.log10(size*size)+1;
            // Dico a Java di stampare numeri interi (d) che sono al massimo di 
            // "truncated" cifre. Se il numero avrà m cifre, lascerò truncated-m
            // spazi prima di stampare il numero.
            // Tutto questo è fatto dalla funzione printf
            String format = " %"+truncated+"d ";
            for (int ic = 0; ic<size; ic++) {
                // Stampa il delimitatore di inizio riga
                System.out.print("|");
                for (int jc = 0; jc<size; jc++) {
                    // Stampa la cella
                    System.out.printf(format, matrix[ic][jc]);
                }
                // Stampa il delimitatore di fine riga
                System.out.println("|");
            }

            int diag1 = 0;
            int diag2 = 0;

            // 2) Calcolo la somma della prima diagonale
            for (int k = 0; k<size; k++) {
                diag1 += matrix[k][k];
            }

            // 3) Calcolo la somma della seconda diagonale
            for (int k = 0; k<size; k++) {
                diag2 += matrix[size-k-1][k];
            }

	    // ---> Controllo che le diagonali abbiano la stessa somma
            if (diag1 != diag2) {
                System.err.println("Error: the diagonals do not match");
                System.exit(1);
            }

            // 4) Per ogni riga
            for (int ic = 0; ic<size; ic++) {
                int sum = 0;
                // effettuo la somma per ogni componente (sommo la riga lungo le colonne)
                for (int jc = 0; jc<size; jc++) {
                    sum += matrix[ic][jc];
                }
                // --> controllo che la somma degli elementi per riga sia uguale ad una delle due diagonali
                if (sum != diag1) {
                    System.err.println("Error: the diagonals do not match");
                    System.exit(1);
                }
            }

	    // 5) Per ogni colonna
            for (int ic = 0; ic<size; ic++) {
                int sum = 0;
                // effettuo la somma per ogni componente (sommo la colonna lungo le righe)
                for (int jc = 0; jc<size; jc++) {
                    sum += matrix[jc][ic];
                }
                // --> controllo che la somma degli elementi per colonne sia uguale ad una delle due diagonali
                if (sum != diag1) {
                    System.err.println("Error: the diagonals do not match");
                    System.exit(1);
                }
            }


        }
    }

}
