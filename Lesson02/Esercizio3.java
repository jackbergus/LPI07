import java.util.Scanner;

public class Esercizio3 {
    /*
     * Creare un programma che genera l'n-esimo numero della sequenza di Fibonacci. Ricordati che:
     * Fib(0) = 1
     * Fib(1) = 1
     * e che \forall n>=2. Fib(n) = Fib(n-1)+Fib(n-2)
     */

    /*
     *  Create a program that generates the n-th number within the fibonacci sequence. Remember that
     *  Fib(0) = 1
     *  Fib(1) = 1
     *  and that \forall n>= 2. Fib(n) = Fib(n-1)+Fib(n-2)
     */
    public static void main(String[] args) {
        int N = new Scanner(System.in).nextInt();
        int prev = 1;
        int next = 1;
        for (int i = 1; i <= N; i++) {
            int tmp = next;
            next += prev;
            prev = tmp;
        }
        System.out.println(N == 0 ? 1 : prev);
    }

}
