import java.util.Objects;
import java.util.Scanner;

public class Ristorante {

    /**
     * Dato un vettore (array), restituisce un array di dimensione max(array.length, newSize) che contiene, come primi
     * elementi, gli stessi dello array
     * @param array     Array da estendere
     * @param newSize   Nuova dimensione da associare all'array
     * @return  Array esteso
     */
    public static double[] estendiVettore(double[] array, int newSize) {
        // Creo il nuovo array da estendere
        double[] copyResult = new double[(int)Math.max(array.length, newSize)];
        for (int i = 0; i<copyResult.length; i++ ) {
            if (i<array.length)
                copyResult[i] = array[i];
            else
                copyResult[i] = 0;
        }
        /* Alternativa avanzata
        // Copio tutti i valori vecchi. Osserva, non devo nemmeno iterare :)
        System.arraycopy(array, 0, copyResult, 0, copyResult.length);
        */
        // Restituisci l'array esteso
        return copyResult;
    }

    /**
     * Dato un vettore (array), restituisce un array di dimensione max(array.length, newSize) che contiene, come primi
     * elementi, gli stessi dello array
     * @param array     Array da estendere
     * @param newSize   Nuova dimensione da associare all'array
     * @return  Array esteso
     */
    public static String[] estendiVettore(String[] array, int newSize) {
        // Creo il nuovo array da estendere
        String[] copyResult = new String[(int)Math.max(array.length, newSize)];
        for (int i = 0; i<copyResult.length; i++ ) {
            if (i<array.length)
                copyResult[i] = array[i];
            else
                copyResult[i] = null; // Nessuna stringa
        }
        /* Alternativa avanzata
        // Copio tutti i valori vecchi. Osserva, non devo nemmeno iterare :)
        System.arraycopy(array, 0, copyResult, 0, copyResult.length);
        */
        // Restituisci l'array esteso
        return copyResult;
    }

    /**
     * Aggiunge un prezzo all'array
     * @param prezzi        Array dei prezzi
     * @param dimensione    Nuova dimensione dell'array
     * @param nuovoPrezzo   Nuovo prezzo da inserire
     * @return              Array aggiornato con il nuovo prezzo
     */
    public static double[] aggiungiMenu(double[] prezzi, int dimensione, double nuovoPrezzo) {
        if (prezzi.length > (dimensione+1)) {
            prezzi[dimensione] = nuovoPrezzo;
            return prezzi;
        } else { // Se il nuovo elemento rischia di andare al di fuori dell'array
            prezzi = estendiVettore(prezzi, prezzi.length+10);
            prezzi[dimensione] = nuovoPrezzo;
        }
        return prezzi;
    }

    /**
     * Aggiunge un nuovo piatto all'array
     * @param piatti        Array dei piatti
     * @param dimensione    Nuova dimensione dell'array
     * @param nuovoPiatto   Nuovo piatto da inserire
     * @return              Array aggiornato con il nuovo prezzo
     */
    public static String[] aggiungiMenu(String[] piatti, int dimensione, String nuovoPiatto) {
        if (piatti.length > (dimensione+1)) {
            piatti[dimensione] = nuovoPiatto;
            return piatti;
        } else {
            piatti = estendiVettore(piatti, piatti.length+10);
            piatti[dimensione] = nuovoPiatto;
        }
        return piatti;
    }

    /**
     * Dato un piatto, lo ricerca all'iterno dell'array dei piatti e, se presente, ne restituisce il prezzo. Restituisce
     * zero altrimenti
     * @param piatto   Nome del piatto di cui cercare il prezzo
     * @param piatti   Array di nomi di piatti
     * @param prezzi   Array di costi associati ai piatti
     * @param dimensione  dimensione massima da cercare all'interno dell'array
     * @return
     */
    public static double cercaPrezzo(String piatto, String[] piatti, double[] prezzi, int dimensione) {
        for (int i = 0; i<dimensione; i++) {
            if (piatto.equals(piatti[i])) {
                return prezzi[i];
            }
        }
        return 0.0;
    }

    /**
     * Dato l'array di piatti e prezzi, stampa l'iterno menu di modo che i prezzi siano tutti allineati tra di loro
     *
     * es:
     * bucatini alla matriciana ... 27,35€
     * pizza alle quattro stagioni  18,23€
     *
     * @param piatti        Array di piatti
     * @param prezzi        Array di prezzi
     * @param dimensione    Numero dei piatti inseriti nel menù
     */
    public static void stampaMenu(String[] piatti, double[] prezzi, int dimensione) {
        int maxLen = 0;
        int maxCifrePrezzo = 0;
        for (int i = 0; i<dimensione; i++) {
            maxLen = Math.max(maxLen, piatti[i].length());
            int cifreIntere = (int)(Math.log10(prezzi[i])+1);
            maxCifrePrezzo = Math.max(maxCifrePrezzo, cifreIntere);
        }
        for (int i = 0; i<dimensione; i++) {
            System.out.print(piatti[i]);
            System.out.print(' ');
            for (int j = 0; j<maxLen - piatti[i].length(); j++) {
                System.out.print('.');
            }
            System.out.print(' ');
            int euroInteri = ((int)(prezzi[i]));
            int centesimi = (int)((prezzi[i] - euroInteri)*100);
            System.out.printf("%" + maxCifrePrezzo + "d", euroInteri);
            System.out.print(',');
            System.out.printf("%02d", centesimi);
            System.out.println("€");
        }
    }

    public static void main(String args[]) {
        double[] prezzi = new double[0];
        String[] piatti = new String[0];
        int dimensione = 0;
        Scanner s = new Scanner(System.in);


        System.out.println("Comandi: ");
        System.out.println("AGGIUNGI: aggiunge un determinato piatto ad un determinato prezzo.");
        System.out.println("CERCAPREZZO: cerca il prezzo di un determinato piatto.");
        System.out.println("STAMPAMENU: stampa il menù del ristorante, incolonnando i prezzi.");
        System.out.println("CONTO: Stampa la somma finale del conto di un cliente.");
        System.out.println();

        while (true) {
            System.out.print("$ ");
            String command = s.nextLine();
            if (command.equals("AGGIUNGI")) {
                System.out.print("Inserire il nome del piatto: ");
                String piatto = s.nextLine();
                piatti = aggiungiMenu(piatti, dimensione, piatto);
                System.out.print("Inserire il prezzo della pietanza (€): ");
                double prezzo = Double.valueOf(s.nextLine());
                prezzi = aggiungiMenu(prezzi, dimensione, prezzo);

                dimensione++;
            } else if (command.equals("CERCAPREZZO")) {
                System.out.print("Inserire piatto di cui si vuole cercare il prezzo: ");
                String piatto = s.nextLine();
                System.out.println("Il prezzo è: " + cercaPrezzo(piatto, piatti, prezzi, dimensione));
            } else if (command.equals("STAMPAMENU")) {
                stampaMenu(piatti, prezzi, dimensione);
            } else if (command.equals("CONTO")) {
                /*
                Fare in modo che lo scontrino preso in input sia nel seguente formato:

                2:pietanza1
                3:pietanza2
                :quit

                dove il numero che precede il primo : identifica quante portate di quel tipo sono state fornite al
                cliente, e il nome che segue i due punti identifica il piatto in questione

                 */
                String record;
                double count = 0;
                System.out.print("piatto>");
                while (!(record = s.nextLine()).equals(":quit")) {
                    int noPortate = 1;
                    int index = record.indexOf(':');
                    if (index != -1) {
                        noPortate = Integer.valueOf(record.substring(0, index));
                    }
                    double prezzo = cercaPrezzo(record.substring(index+1).trim(), piatti, prezzi, dimensione);
                    count += (prezzo * noPortate);
                    System.out.print("piatto>");
                }
                System.out.println("TOTALE: " + count+"€");
            }
        }


    }

}
