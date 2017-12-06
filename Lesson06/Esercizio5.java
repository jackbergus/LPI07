

import java.util.Scanner;

public class Esercizio5 {

    /**
     * Creare un programma che legge una matrice per riga da terminale. Stampare la matrice trasposta, ovvero quello
     * che sostituisce ogni elemento a_ij in un a_ji
     * @param args
     */
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of rows = ");
        int row = Integer.valueOf(scanner.nextLine().trim());
        System.out.print("Number of columns = ");
        int cols = Integer.valueOf(scanner.nextLine().trim());
        int[][] matrix = new int[row][cols];
        boolean error = false;
        double positions = 1;
        for (int i = 0; i<row; i++) {
            String arrayNumbers[] = scanner.nextLine().trim().split("\\s");
            if (arrayNumbers.length != cols) {
                error = true;
                System.err.println("Error: the provided row has the wrong number of columns. The program is going to be stopped.");
                break;
            }
            for (int j = 0; j<cols; j++) {
                matrix[i][j] = Integer.valueOf(arrayNumbers[j]);
                positions = Math.max(Math.log10(matrix[i][j])+1, positions);
            }
        }
        if (!error) {
            int truncated = (int)positions;
            String format = " %"+truncated+"d ";
            for (int i = 0; i<row; i++) {
                System.out.print("|");
                for (int j = 0; j<cols; j++) {
                    System.out.printf(format, matrix[j][i]);
                }
                System.out.println("|");
            }
        }

    }

}
