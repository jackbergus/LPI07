package Lez09.imdb.simple;

public class Movie {
    private String releasedIn;
    private String name;
    private String genre;
    private String mainActorKey;

    public Movie(String... x) throws IllegalArgumentException {
        if (x.length != 4) throw new IllegalArgumentException("Error: movies should have 4 fields");
        releasedIn = x[1];
        name = x[0];
        genre = x[2];
        mainActorKey = x[3];
    }

    public Actor getActor(Database db) {
        return db.getActor(mainActorKey);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "movie("+name+","+ releasedIn +","+genre+","+mainActorKey+")";
    }

    public String getGenre() {
        return genre;
    }
}
