package Lez09.imdb.simple;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Database {

    // Collezione degli attori
    private ArrayList<Actor> actorList;

    // Collezione degli utenti
    private ArrayList<User> userList;

    // Collezione dei film
    private ArrayList<Movie> movieList;

    // Collezione delle valutazioni
    private ArrayList<Score> scoreList;

    public Database() {
        actorList = new ArrayList<>();
        userList = new ArrayList<>();
        movieList = new ArrayList<>();
        scoreList = new ArrayList<>();
    }

    /**
     * Effettua il parsing di una linea, e crea l'oggetto corrispondente
     * @param line linea di cui fare il parsing
     */
    public void scanLine(String line) {
        // Ottengo la classe degli elementi da creare
        String classe = line.substring(0,line.indexOf('(')).toLowerCase();
        // Estraggo gli argomenti
        String fields[] = line.substring(line.indexOf('(')+1,line.indexOf(')')).split(",");
        if (classe.equals("actor")) {
            actorList.add(new Actor(fields));
        } else if (classe.equals("user")) {
            userList.add(new User(fields));
        } else if (classe.equals("score")) {
            scoreList.add(new Score(fields));
        } else if (classe.equals("movie")) {
            movieList.add(new Movie(fields));
        }
    }

    /**
     * legge la prima linea presente nello scanner
     * @param s Scanner da cui effettuare la lettura
     * @return  Restituisce true se era presente un dato, false altrimenti
     */
    public boolean scanLine(Scanner s) {
        if (!s.hasNext()) return false;
        scanLine(s.nextLine());
        return true;
    }

    /**
     * Legge un file (database) che contiene tutti gli oggetti da inizializzare e sui quali effettuare delle operazioni
     * @param f
     * @throws FileNotFoundException
     */
    public void load(File f) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(f));
        while (scanLine(s));
    }

    /**
     * Dato il titolo di un film, lo cerca nella lista e lo serializza in una stringa
     * @param title
     * @return
     */
    public String printFilm(String title) {
        for (Movie m : movieList) {
            if (m.getName().equals(title)) return m.toString();
        }
        return "";
    }

    /**
     * Inserisce un nuovo attore (se già non esiste)
     * @param name
     * @param surname
     * @param isFemale
     * @return
     */
    public boolean addActor(String name, String surname, String isFemale) {
        for (Actor m : actorList) {
            if (m.asKey().equals(name+" "+surname)) return false;
        }
        actorList.add(new Actor(name, surname, isFemale));
        return true;
    }

    /**
     * Inserisce un nuovo utente se già non esiste
     * @param uname
     * @param email
     * @return
     */
    public boolean addUser(String uname, String email) {
        for (User u : userList) {
            if (u.getUsername().equals(uname)) return false;
        }
        userList.add(new User(uname, email));
        return true;
    }

    /**
     * Inserisce un film di cui si conosce già l'attore
     * @param title
     * @param releaseDate
     * @param genre
     * @param actor
     * @return
     */
    public boolean addMovie(String title, String releaseDate, String genre, String actor) {
        for (Actor m : actorList) {
            if (m.asKey().equals(actor)) {
                movieList.add(new Movie(title, releaseDate, genre, actor));
                return true;
            }
        }
        return false;
    }

    /**
     * Dato il genere del film, conta quanti film sono stati prodotti di quel genere
     * @param genre     Genere dei film da cercare
     * @return
     */
    public int countMoviesPerGenre(String genre) {
        int genreCount = 0;
        for (Movie m : movieList) {
            if (m.getGenre().toLowerCase().equals(genre.toLowerCase()))
                genreCount++;
        }
        return genreCount;
    }

    /**
     * Restituire quale attore ha recitato in un numero maggiore di film
     * @return
     */
    public Actor actorInMostMovies() {
        Actor prev = null;
        int count = -1;
        // Per ogni attore
        for (Actor actor : actorList) {
            int currentActorCount = 0;
            // Per ogni film
            for (Movie m : movieList) {
                if (m.getActor(this).equals(actor)) {
                    // se actor ha recitato in m, allora conta un nuovo film
                    currentActorCount++;
                }
            }
            // Controlla se l'attore corrente è il nuovo massimo
            if (currentActorCount > count) {
                prev = actor;
                count = currentActorCount;
            }
        }
        return prev;
    }

    /**
     * Dopo aver calcolato la media di tutte le valutazioni per lo stesso genere, restituisce un genere che ha media
     * massima
     * @return
     */
    public String genreWithHighestAverage() {
        // Creo la lista delle medie per genere
        ArrayList<GenreScore> gsa = new ArrayList<>();
        // Per ogni valutazione...
        for (Score s : scoreList) {
            // Ottengo il film, convertendo la sua rappresentazione in stringa ed ottenendo l'informazione completa
            Movie m = s.getMovie(this);
            // Ottengo il genere del film
            String gen = m.getGenre();
            // Dalla valutazione ottengo anche il valore numerico
            int score = s.getScore();
            boolean found = false;
            for (GenreScore a : gsa) {
                // Aggiorno la media delle valutazioni se a corrisponde al genere gen. Controllo se devo fermare l'aggiornamento della lista
                found = found || a.addScore(gen, score);
                // Il valore aggregato può comparire una sola volta
                if (found) break;
            }
            // Se tale genere non esiste nella lista, allora aggiungo la prima valutazione in merito
            if (!found) {
                gsa.add(new GenreScore(gen,score));
            }
        }
        // Similarmente ad altri esercizi, ottengo una delle medie massime per genere
        String genre = null;
        double avg = -1;
        for (GenreScore g : gsa) {
            double s;
            if ((s = g.average()) > avg) {
                genre = g.getGenre();
                avg = s;
            }
        }
        return genre;
    }


    public Actor getActor(String mainActorKey) {
        for (Actor x : actorList) {
            if (x.asKey().equals(mainActorKey)) return x;
        }
        return null;
    }


    public Movie getMovie(String movieName) {
        for (Movie m : movieList) {
            if (m.getName().equals(movieName)) return m;
        }
        return null;
    }

    public User getUser(String userName) {
        for (User m : userList) {
            if (m.getUsername().equals(userName)) return m;
        }
        return null;
    }

    public void dump(File f) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        for (Actor a : actorList) {
            writer.println(a.toString());
        }
        for (Movie m : movieList) {
            writer.println(m.toString());
        }
        for (User u : userList) {
            writer.println(u.toString());
        }
        for (Score s : scoreList) {
            writer.println(s.toString());
        }
        writer.close();
    }
}
