package Lez09.imdb.simple;

public class Score {
    private String movieName;
    private String user;
    private int score;

    public Score(String[] args) throws IllegalArgumentException, NumberFormatException {
        if (args.length != 3) throw  new IllegalArgumentException("Error: an actor has 2 fields.");
        this.user = args[0];
        this.movieName = args[1];
        score = Integer.valueOf(args[2]);
        if (score > 10 || score <0) throw  new IllegalArgumentException("The score should be in between 0 and 10");
    }

    public Movie getMovie(Database db) {
        return db.getMovie(movieName);
    }

    public User getUser(Database db) {
        return db.getUser(movieName);
    }


    @Override
    public String toString() {
        return "score("+user+","+ movieName +","+score+")";
    }

    public int getScore() {
        return score;
    }
}
