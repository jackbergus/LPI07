package Lez10;

public class Stirling {

    public static long factorial(long i) {
        return i <= 1 ? 1 : i * factorial(i - 1);
    }

    public static long cofbinom(long n, long k) {
        return factorial(n)/(factorial(k)*factorial(n-k));
    }

    public static long summate(long k, long n) {
        long result = 0;
        for (long j = 0; j<=k; j++) {
            result += ((long)Math.pow(-1, k-j)) * cofbinom(k, j) * ((long)Math.pow(j, n));
        }
        return result;
    }

    public static long stirling2(long n, long k) {
        long result = 0;
        for (int j = 1; j<=k; j++) {
            result += ((long)Math.pow(-1, k-j)) * ((long)Math.pow(j,n-1)) / (factorial(j-1)*factorial(k-j));
        }
        return result;
    }

    public static long stirling(long n, long k) {
        return summate(k, n)/factorial(k);
    }

    public static void main(String args[]) {
        for (int i = 0; i <= 10; i++) {
            for (int j = 0; j <= i; j++)
                System.out.print(stirling(i, j) + "\t");
            System.out.println();
        }
    }

}
