package Lez09.imdb.better;

public class Scores {
    private int score;
    private int numScore;

    public Scores() {
        this.score = 0;
        this.numScore = 0;
    }

    public void add(int score) {
        this.score += score;
        numScore++;
    }

    public double average() {
        return ((double)score)/((double)numScore);
    }
}
