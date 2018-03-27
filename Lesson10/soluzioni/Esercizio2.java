import java.security.InvalidParameterException;
import java.util.Scanner;

public class Esercizio2 {

    public static long ackermann(long m, long n) throws InvalidParameterException {
        if (n <0 || m<0) throw new InvalidParameterException();
        if (m == 0)
            return n+1;
        else if ((m > 0) && (n == 0))
            return ackermann(m-1,1);
        else
            return ackermann(m-1, ackermann(m,n-1));
    }


    public static void main(String args[]) {
        Scanner s = new Scanner(System.in);
        System.out.print("Inserire il parametro m: ");
        long m = s.nextLong();
        System.out.print("Inserire il parametro n: ");
        long n = s.nextLong();
        try {
            // Eseguo la funzione, stando attento all'eccezione
            long r = ackermann(m, n);
            // Se non viene lanciato nessun errore, stampo il valore.
            System.out.println(r);
        } catch (InvalidParameterException e) {
            // Raccolgo l'eccezione e stampo un errore
            System.err.println("Errore: la funzione ha ricevuto almeno un parametro negativo");
        }

    }
}
