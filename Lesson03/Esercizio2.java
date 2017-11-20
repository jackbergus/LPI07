import java.util.Scanner;

public class Esercizio2 {

    // Creare un programma che legge una matrice da terminale.
    // Chiedere prima all'utente il numero di righe e colonne, leggere la matrice per righe e controllare che il numero di celle inserite corrisponda con il numero di colonne.
    // Stampare la matrice letta da terminale
    //
    // Utilizzare System.out.printf("%nd", i); per stampare interi composti da massimo n cifre

    // Create a program that reads a matrix from console.
    // Ask the user the matrix's number of rows and columns first, read the matrix by row, and then check that the number of imputed cells per row matches with the number of columns.
    // Print the matrix
    //
    // Use System.out.printf("%nd", i); in order to print the integers formed by at most n digits

    public static void main(String args[]) {
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
                positions = Math.max(Math.log10(matrix[i][j]), positions);
            }
        }
        if (!error) {
            int truncated = (int)positions;
            if (((double)truncated) != positions) {
                truncated++;
            }
            String format = " %"+truncated+"d ";
            for (int i = 0; i<row; i++) {
                System.out.print("|");
                for (int j = 0; j<cols; j++) {
                    System.out.printf(format, matrix[i][j]);
                }
                System.out.println("|");
            }
        }
    }

}
