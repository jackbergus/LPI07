package esercizio4;

import java.util.ArrayList;

public class CD {
    private String genere;
    private String autore;
    private String titolo;
    private CasaDiscografica casa;
    private String codice;
    private ArrayList<Brano> brani;

    public CD(String genere, String autore, String titolo, CasaDiscografica casa, String codice) {
        this.genere = genere;
        this.autore = autore;
        this.titolo = titolo;
        this.casa = casa;
        this.codice = codice;
        this.brani = new ArrayList<Brano>();
    }

    public int durataTotale() {
        int tot = 0;
        // Per ogni brano associato al cd
        for (int i = 0; i < brani.size(); i++) {
            Brano b = brani.get(i);
            // ... ottengo il numero di secondi e li sommo al totale
            tot += b.getSecondi();
        }
        // Restituisco il totale
        return tot;
    }

    public String getGenere() {
        return genere;
    }
}
