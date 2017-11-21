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
        Scanner scanner = new Scanner(System.in);
        System.out.print("Odd size of the matrix = ");
        int size = Integer.valueOf(scanner.nextLine());
        if (size % 2 == 1) {
            int[][] matrix = new int[size][size];

            for (int i = 0; i<size; i++)
                for (int j = 0; j<size; j++)
                    matrix[i][j] = 0;


            int i = 0;
            int j = (size/2);

            for (int k = 1; k<=size*size; k++) {
                if (k == 1) {
                    matrix[i][j] = k;
                } else {

                    //int nexti = i == 0 ? size-1 : i-1;
                    int nexti = (i == 0 ? size : i);
                    nexti--;
                    //int nexti;
                    // if (i == 0) {
                    //   i = size-1;
                    // } else {
                    //   i--;
                    // }h

                    int nextj = j == size-1 ? 0 : j+1;
                    if (matrix[nexti][nextj] != 0) {
                        i = i == size ? size-1 : i+1;
                    } else {
                        i = nexti;
                        j = nextj;
                    }
                    matrix[i][j] = k;
                }
            }

            int truncated = (int)Math.log10(size*size)+1;
            String format = " %"+truncated+"d ";
            for (int ic = 0; ic<size; ic++) {
                System.out.print("|");
                for (int jc = 0; jc<size; jc++) {
                    System.out.printf(format, matrix[ic][jc]);
                }
                System.out.println("|");
            }

            int diag1 = 0;
            int diag2 = 0;

            // First diagonal
            for (int k = 0; k<size; k++) {
                diag1 += matrix[k][k];
            }

            // Second diagonal
            for (int k = 0; k<size; k++) {
                diag2 += matrix[size-k-1][k];
            }

            if (diag1 != diag2) {
                System.err.println("Error: the diagonals do not match");
                System.exit(1);
            }
            for (int ic = 0; ic<size; ic++) {
                int sum = 0;
                for (int jc = 0; jc<size; jc++) {
                    sum += matrix[ic][jc];
                }
                if (sum != diag1) {
                    System.err.println("Error: the diagonals do not match");
                    System.exit(1);
                }
            }

            for (int ic = 0; ic<size; ic++) {
                int sum = 0;
                for (int jc = 0; jc<size; jc++) {
                    sum += matrix[jc][ic];
                }
                if (sum != diag1) {
                    System.err.println("Error: the diagonals do not match");
                    System.exit(1);
                }
            }


        }
    }

}
