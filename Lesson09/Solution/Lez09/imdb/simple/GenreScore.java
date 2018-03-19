package Lez09.imdb.simple;

/**
 * Ogni Genre Score tiene conto di quanti voti e quali sono stati associati ad un determinato genere
 */
public class GenreScore {
    private String genre;
    private int scores;
    private int numberScores;

    /**
     * GenreScore viene inizializzato associando una prima valutazione al genere
     * @param genre
     * @param score
     */
    public GenreScore(String genre, int score) {
        this.genre = genre;
        scores = score;
        numberScores = 1;
    }

    /**
     * Aggiungo uno score, se e solo se lo score da sommare è riferito allo stesso genere contenuto nell'oggetto corrente
     * @param genre
     * @param score
     * @return restituisce true se ho già trovato l'elemento. Serve per interrompere il prima possibile la scansione lineare
     */
    public boolean addScore(String genre, int score) {
        if (genre.equals(this.genre)) {
            scores+=score;
            numberScores++;
            return true;
        } return false;
    }

    /**
     * Restituisce la media delle valutazioni per i film dello stesso genere
     * @return
     */
    public double average() {
        if (numberScores == 0)
            return 0.0;
        else return ((double)scores)/((double)numberScores);
    }

    public String getGenre() {
        return genre;
    }
}
