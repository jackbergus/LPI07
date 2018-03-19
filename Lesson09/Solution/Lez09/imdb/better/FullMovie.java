package Lez09.imdb.better;


public class FullMovie {
    private CalendarDate releasedIn;
    private String name;
    private String genre;
    private Actor mainActorKey;

    public FullMovie(String name, CalendarDate date, String genre, Actor actor) {
        this.name = name;
        this.releasedIn = date;
        this.genre = genre;
        this.mainActorKey = actor;
        actor.addMovie(this);
    }

    public Actor getActor() {
        return (mainActorKey);
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "movie("+name+","+releasedIn.toString()+","+genre+","+mainActorKey.asKey()+")";
    }

    public String getGenre() {
        return genre;
    }
}
