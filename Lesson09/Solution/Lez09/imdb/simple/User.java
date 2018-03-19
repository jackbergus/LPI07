package Lez09.imdb.simple;

public class User {
    private String username;
    private String email;

    public User(String... args) throws IllegalArgumentException {
        if (args.length != 2) throw  new IllegalArgumentException("Error: an actor has 2 fields.");
        this.username = args[0];
        this.email = args[1];
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username) && email.equals(user.email);
    }

    @Override
    public String toString() {
        return "user("+username+","+email+")";
    }

    public String getUsername() {
        return username;
    }

    /* Some java default containers (e.g. HashSet) may also require the following function:
    @Override
    public int hashCode() {
        return Objects.hash(username, email);
    }*/
}
