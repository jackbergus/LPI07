import java.util.Scanner;

public class Esercizio3 {

    // Modifica l'esercizio precedente per sommare due matrici

    // Edit the previous exercise to sum two matrices

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Number of rows = ");
        int row = Integer.valueOf(scanner.nextLine().trim());
        System.out.print("Number of columns = ");
        int cols = Integer.valueOf(scanner.nextLine().trim());
        int[][] matrixLeft = new int[row][cols];
        int[][] matrixRight = new int[row][cols];
        boolean error = false;
        double positions = 1;
        System.err.println("Reading the first matrix: ");
        for (int i = 0; i<row; i++) {
            String arrayNumbers[] = scanner.nextLine().trim().split("\\s");
            if (arrayNumbers.length != cols) {
                error = true;
                System.err.println("Error: the provided row has the wrong number of columns. The program is going to be stopped.");
                break;
            }
            for (int j = 0; j<cols; j++) {
                matrixLeft[i][j] = Integer.valueOf(arrayNumbers[j]);
            }
        }
        System.err.println("Reading the second matrix: ");
        for (int i = 0; i<row; i++) {
            String arrayNumbers[] = scanner.nextLine().trim().split("\\s");
            if (arrayNumbers.length != cols) {
                error = true;
                System.err.println("Error: the provided row has the wrong number of columns. The program is going to be stopped.");
                break;
            }
            for (int j = 0; j<cols; j++) {
                matrixRight[i][j] = Integer.valueOf(arrayNumbers[j]);
                positions = Math.max(Math.log10(matrixLeft[i][j]+matrixRight[i][j]), positions);
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
                    System.out.printf(format, matrixLeft[i][j]+matrixRight[i][j]);
                }
                System.out.println("|");
            }
        }
    }

}
