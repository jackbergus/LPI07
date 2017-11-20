import java.util.Scanner;

public class Esercizio4 {
    // Modifica l'esercizio precedente per moltiplicare due matrici

    // Edit the previous exercise to perform the matrix multiplication

    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Number of rows of the first matrix= ");
        int rowLeft = Integer.valueOf(scanner.nextLine().trim());
        System.out.print("Number of columns of the first matrix= ");
        int colsLeft = Integer.valueOf(scanner.nextLine().trim());

        System.out.println("\n\n");

        System.out.print("Number of rows of the second matrix= ");
        int rowRight = Integer.valueOf(scanner.nextLine().trim());
        System.out.print("Number of columns of the second matrix= ");
        int colsRight = Integer.valueOf(scanner.nextLine().trim());

        if (colsLeft != rowRight) {
            System.err.println("Error: the left matrix must have the same number of rows of the right matrix");
        } else {
            int[][] matrixLeft = new int[rowLeft][colsLeft];
            int[][] matrixRight = new int[rowRight][colsRight];
            int[][] matrixResult = new int[rowLeft][colsRight];
            boolean error = false;
            double positions = 1;
            System.err.println("Reading the first matrix: ");
            for (int i = 0; i<rowLeft; i++) {
                String arrayNumbers[] = scanner.nextLine().trim().split("\\s");
                if (arrayNumbers.length != colsLeft) {
                    error = true;
                    System.err.println("Error: the provided row has the wrong number of columns. The program is going to be stopped.");
                    break;
                }
                for (int j = 0; j<colsLeft; j++) {
                    matrixLeft[i][j] = Integer.valueOf(arrayNumbers[j]);
                }
            }
            System.err.println("Reading the second matrix: ");
            for (int i = 0; i<rowRight; i++) {
                String arrayNumbers[] = scanner.nextLine().trim().split("\\s");
                if (arrayNumbers.length != colsRight) {
                    error = true;
                    System.err.println("Error: the provided row has the wrong number of columns. The program is going to be stopped.");
                    break;
                }
                for (int j = 0; j<colsRight; j++) {
                    matrixRight[i][j] = Integer.valueOf(arrayNumbers[j]);
                }
            }
            if (!error) {
                for (int i = 0; i<rowLeft; i++) {
                    for (int j = 0; j<colsRight; j++) {
                        matrixResult[i][j] = 0;
                        for (int k = 0; k<colsLeft; k++) {
                            matrixResult[i][j] += matrixLeft[i][k] * matrixRight[k][j];
                        }
                        positions = Math.max(Math.log10(matrixResult[i][j]), positions);
                    }
                }
                int truncated = (int)positions;
                if (((double)truncated) != positions) {
                    truncated++;
                }
                String format = " %"+truncated+"d ";
                for (int i = 0; i<rowLeft; i++) {
                    System.out.print("|");
                    for (int j = 0; j<colsRight; j++) {
                        System.out.printf(format, matrixResult[i][j]);
                    }
                    System.out.println("|");
                }
            }
        }


    }

}
