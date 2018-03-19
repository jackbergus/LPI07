package Lez09.imdb.better;


public class FullScore {
    private FullMovie filmName;
    private User user;
    private int score;

    public FullScore(User user, FullMovie movie, int score) throws NumberFormatException {
        this.user = user;
        this.filmName = movie;
        this.score = score;
        if (score > 10 || score <0) throw  new IllegalArgumentException("The score should be in between 0 and 10");
    }

    public FullMovie getMovie() {
        return (filmName);
    }

    public User getUser() {
        return (user);
    }


    @Override
    public String toString() {
        return "score("+user.getUsername()+","+filmName.getName()+","+score+")";
    }

    public int getScore() {
        return score;
    }
}
