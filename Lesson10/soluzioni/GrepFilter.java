import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class GrepFilter {

    public static void main(String args[]) throws IllegalArgumentException {
        Scanner s = new Scanner(System.in);
        System.out.println("Inserire una parola: ");
        String p = s.nextLine();
        p = p.trim();
        // La parola non deve contenere spazi
        if (p.contains(" ")) {
            throw new IllegalArgumentException("La parola non deve contenere spazi");
        }


        System.out.println("Inserire il nome del file da leggere: ");
        String nomeFile = s.nextLine();

        // FACOLTATIVO: stringa dove verrà salvato il risultato del file
        String out = "";

        boolean doNext = false;
        try {
            // Apro il file in lettura
            Scanner file = new Scanner(new File(nomeFile));
            // Finché trovo linee da leggere
            while (file.hasNext()) {
                String line = file.nextLine();
                if (line.contains(p)) {
                    // Stampo la linea che si vede a
                    System.out.println(line);
                    // FACOLTATIVO: preparo la stringa da salvare nel file
                    if (doNext) out+= "\n";
                    // Stamp la stringa
                    out += line;
                    // So se al giro successivo devo stampare una nuova linea nel file da scrivere
                    doNext = true;
                }
            }
            // Ora posso scrivere il file
            System.out.println("Inserire il nome del file da scrivere: ");
            // Ottengo il mome del file da scrivere
            String write = s.nextLine();
            try {
                // Provo a scrivere il file
                FileWriter fw = new FileWriter(write);
                fw.write(out);
                fw.close();
            } catch (IOException e) {
                System.err.println("Il file da scrivere non esiste");
            }
        } catch (FileNotFoundException e) {
            System.err.println("Il file da leggere non esiste");
        }
    }
}
