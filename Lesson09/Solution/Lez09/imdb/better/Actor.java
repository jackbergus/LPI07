package Lez09.imdb.better;

import java.util.ArrayList;

public class Actor {
    private String name;
    private String surname;
    private boolean sexIsFemale;
    private ArrayList<FullMovie> starredIn;

    public Actor(String... args) throws IllegalArgumentException {
        if (args.length != 3) throw  new IllegalArgumentException("Error: an actor has 3 fields.");
        this.name = args[0];
        this.surname = args[1];
        starredIn = new ArrayList<>();
        if (args[2].toLowerCase().equals("male")) {
            this.sexIsFemale = false;
        } else if (args[2].toLowerCase().equals("female")) {
            this.sexIsFemale = true;
        } else {
            throw new IllegalArgumentException(args[2]+" should be either 'male' or 'female'");
        }
    }

    public String asKey() {
        return name+" "+surname;
    }

    @Override
    public String toString() {
        return "actor("+name+","+surname+","+(sexIsFemale ? "female": "male")+")";
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Actor)) return false;
        Actor attore = (Actor)obj;
        return name.equals(attore.name) && surname.equals(attore.surname) && sexIsFemale == attore.sexIsFemale;
    }

    public void addMovie(FullMovie fullMovie) {
        starredIn.add(fullMovie);
    }

    public int starredMovies() {
        return starredIn.size();
    }

    /* Some java default containers (e.g. HashSet) may also require the following function:

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, sexIsFemale);
    }

    */
}
