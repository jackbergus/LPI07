import java.util.Scanner;

public class Esercizio2 {

    public static void main(String[] args) {
        /*
        Scrivere un programma per calcolare il valore massimo e minimo di un insieme
        di N numeri inseriti da tastiera. Ciascun numero verrà fornito in una riga distinta.
        Il programma deve leggere il valore di N, ed in
        seguito deve leggere una sequenza di N numeri. A questo punto il programma
        deve stampare il massimo ed il minimo tra i numeri inseriti.
         */

        /*
        Write a program to calculate the maximum and minimum value of a set of N numbers entered via the keyboard.
        Each number will be provided on a separate line. The program must read the value of N, and then it must read
        a sequence of N numbers by N distinct lines. At this point the program must print the maximum and the
        minimum between the entered numbers.
         */
        Scanner console = new Scanner(System.in);
        System.out.println("Provide how many numbers you want to provide");
        int N = console.nextInt();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            System.out.print("Value n° " + (i+1) + ": ");
            int value = console.nextInt();
            min = Math.min(min, value);
            max = Math.max(max, value);
        }
        if (N > 0)
            System.out.println("Min = " + min + " Max = " + max);
        else
            System.out.println("No valid set of numbers was analysed.");
    }

}
