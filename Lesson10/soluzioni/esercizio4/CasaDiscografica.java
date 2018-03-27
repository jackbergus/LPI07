package esercizio4;

import java.util.ArrayList;

public class CasaDiscografica {
    private String nome;
    private ArrayList<CD> rilasciati;

    public CasaDiscografica(String nome) {
        this.nome = nome;
        this.rilasciati = new ArrayList<CD>();
    }

    private String generePiuDischi() {
        // Ottengo il nome complessivo dei generi a cui sono associati CD di una casa discografica
        ArrayList<String> generi = new ArrayList<String>();
        // Per ogni cd rilasciato
        for (int i = 0; i < rilasciati.size(); i++) {
            CD c = rilasciati.get(i);
            String genere = c.getGenere();
            // Se contiene un genere non ancora incontrato, lo aggiungo alla lista di stringhe
            if (!generi.contains(genere))
                generi.add(genere);
        }

        // Inizializzo le variabili per il massimo
        int maxCount = -1;
        String maxGenere = null;
        // Per ogni genere precedentemente memorizzato
        for (int i = 0; i < generi.size(); i++) {
            String genere = generi.get(i);
            int conto = 0;
            // Controllo quali cd hanno quel determinato genere
            for (int j = 0; j < rilasciati.size(); j++) {
                CD cd = rilasciati.get(j);
                // Conto quanti cd hanno quel genere: incremento di uno la variabile di conteggio se il cd ha il genere corrente
                if (cd.getGenere().equals(genere))
                    conto++;
            }
            // Se ho contato un numero di cd maggiore rispetto a quello precedentemente incontrato, allora questo genere è il nuovo massimo (locale)
            if (conto > maxCount) {
                maxGenere = genere;
                maxCount = conto;
            }
        }

        // Restituisce un genere, se esiste. Altrimenti fornisce null
        return maxGenere;
    }
}
