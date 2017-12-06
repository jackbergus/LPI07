

import java.util.Scanner;

public class Esercizio3 {

    /**
     * Scrivere un programma che legge una serie di voti con i loro crediti. Si rispecchi il seguente formato:
     *
     * voto1:credito1   voto2:credito2       voto3:credito3 voto4:credito4
     *
     * Scrivere un programma che valuti la media pesata dei voti, ovvero:
     *
     * voto1*credito1 + voto2*credito2 + voto3*credito3 + voto4*credito4 + ...
     * -----------------------------------------------------------------------
     *    credito1+ credito2+ credito3+credito4
     *
     * Osserva: l'utente può anche scrivere un numero variabile di voti e crediti, e che i crediti non siano zero o negativi
     *
     */
    public static void main(String[] args) {
        String[] grades = new Scanner(System.in).nextLine()
                                              .trim()
                                              .replace("\\s+"," ")
                                              .split("\\s");
        double numerator = 0;
        double denominator = 0;
        boolean error = false;
        for (int i = 0; i<grades.length; i++) {
            String[] grade_and_credits = grades[i].split(":");
            double grade = Double.valueOf(grade_and_credits[0]);
            double credits = Double.valueOf(grade_and_credits[1]);
            if (credits == 0) {
                System.err.println("ERRORE: hai inserito un credito che è zero");
                error = true;
                break;
            }
            numerator += grade * credits;
            denominator += credits;
        }
        if (grades.length == 0 || error) {
            System.out.println(0.0);
        } else {
            System.out.println(numerator/denominator);
        }
    }

}
