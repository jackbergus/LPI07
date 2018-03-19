package Lez09.imdb.better;

public class GenreScore {
    private String genre;
    private int scores;
    private int numberScores;

    public GenreScore(String genre, int score) {
        this.genre = genre;
        scores = score;
        numberScores = 1;
    }

    public boolean addScore(String genre, int score) {
        if (genre.equals(this.genre)) {
            scores+=score;
            numberScores++;
            return true;
        } return false;
    }

    public double average() {
        if (numberScores == 0)
            return 0.0;
        else return ((double)scores)/((double)numberScores);
    }

    public String getGenre() {
        return genre;
    }
}
