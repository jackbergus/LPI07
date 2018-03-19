package Lez09.imdb.better;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


/**
 * Questa classe rappresenta una delle soluzioni auspicabili, sia per quanto concerne l'efficienza, sia per quanto
 * concerne la rappresentazione del modello dei dati completamente in classi.
 *
 * Osserva: viene utilizzata una HashMap invece di una ArrayList per velocizzare l'accesso agli elementi con una chiave specifica
 */
public class FullDatabase {

    /*
     * Queste mappe rappresentano tutte il mio intero database. Queste mappe (o liste) contengono solamente oggetti
     * completamente istanziati
     */
    private HashMap<String,Actor> actorMap;
    private HashMap<String, User> userMap;
    private HashMap<String,FullMovie> movieMap;
    private ArrayList<FullScore> scoreList;

    /*
     * Questa mappa associa ad ogni chiave di attore una collezione di film che non hanno ancora l'informazione completa per quell'attore
     */
    private HashMap<String,ArrayList<DataObserver>> movieWithMissingActor;

    /*
     * Questa mappa associa ad ogni nome di film una collezione di valutazioni che non hanno ancora l'informazione completa per quel film
     */
    private HashMap<String,ArrayList<DataObserver>> scoreWithMissingMovie;
    /*
     * Questa mappa associa ad ogni nome utente una collezione di valutazioni che non hanno ancora l'informazione completa per quell'utente
     */
    private HashMap<String,ArrayList<DataObserver>> scoreWithMissingUser_;

    public FullDatabase() {
        actorMap = new HashMap<>();
        userMap = new HashMap<>();
        movieMap = new HashMap<>();
        scoreList = new ArrayList<>();
        movieWithMissingActor = new HashMap<>();
        scoreWithMissingMovie = new HashMap<>();
        scoreWithMissingUser_ = new HashMap<>();
    }

    public void scanLine(String line) {
        String classe = line.substring(0,line.indexOf('(')).toLowerCase();
        String fields[] = line.substring(line.indexOf('(')+1,line.indexOf(')')).split(",");
        if (classe.equals("actor")) {
            // Creo direttamente un attore (non richiede altre informazioni da altre classi)
            createActor(fields);
        } else if (classe.equals("user")) {
            // Creo direttamente un utente (non richiede altre informazioni da altre classi)
            createUser(fields);
        } else if (classe.equals("score")) {
            // Controllo se esiste già quell'utente
            User u = userMap.get(fields[0]);
            // Controllo se esiste già quel film
            FullMovie mov = movieMap.get(fields[1]);
            if (u != null && mov != null) {
                // Se esistono entrambi, posso creare direttamente l'oggetto ed aggiornare la tabella
                createScore(fields, u, mov);
            } else {
                DataObserver d = new DataObserver(this,fields);
                // Se l'utente non esiste ...
                if (u == null) {
                    // Inserisco l'osservatore d all'interno della lista di tutte quelle valutazioni in attesa che si crei l'utente fields[0]
                    ArrayList<DataObserver> check = scoreWithMissingUser_.get(fields[0]);
                    if (check == null) {
                        ArrayList<DataObserver> daob = new ArrayList<>();
                        scoreWithMissingUser_.put(fields[0], daob);
                        check = daob;
                    }
                    check.add(d);
                } else
                    d.notifyScore(u); // Se esiste già, informo l'osservatore che parte dell'informazione è già disponibile

                // Se il film non esiste ...
                if (mov == null) {
                    // Inserisco l'osservatore d all'interno della lista di tutte quelle valutazioni in attesa che si crei il film fields[1]
                    ArrayList<DataObserver> check = scoreWithMissingMovie.get(fields[1]);
                    if (check == null) {
                        ArrayList<DataObserver> daob = new ArrayList<>();
                        scoreWithMissingMovie.put(fields[1], daob);
                        check = daob;
                    }
                    check.add(d);
                } else
                    d.notifyScore(mov);// Se esiste già, informo l'osservatore che parte dell'informazione è già disponibile
            }
        } else if (classe.equals("movie")) {
            Actor a;
            if ((a = actorMap.get(fields[3])) != null) {
                // Se l'attore principale di quel film esiste, allora creo il film
                createMovie(a, fields);
            } else {
                // Altrimenti procedo come sopra, e rimango in attesa della sua creazione
                ArrayList<DataObserver> check = movieWithMissingActor.get(fields[3]);
                if (check == null) {
                    ArrayList<DataObserver> daob = new ArrayList<>();
                    movieWithMissingActor.put(fields[3], daob);
                    check = daob;
                }
                check.add(new DataObserver(this,fields));
            }
        }
    }

    public boolean scanLine(Scanner s) {
        if (!s.hasNext()) return false;
        scanLine(s.nextLine());
        return true;
    }

    public void load(File f) throws FileNotFoundException {
        Scanner s = new Scanner(new FileInputStream(f));
        while (scanLine(s));
    }

    public String printFilm(String title) {
        FullMovie fm = movieMap.get(title);
        return fm != null ? fm.getName() : "";
    }

    public boolean addActor(String name, String surname, String isFemale) {
        createActor(name, surname, isFemale);
        return true;
    }

    public boolean addUser(String uname, String email) {
        addUser(uname, email);
        return true;
    }

    public boolean addMovie(String title, String releaseDate, String genre, String actor) {
        Actor a;
        if ((a = actorMap.get(actor)) != null) {
            createMovie(a, title,releaseDate, genre,actor);
            return true;
        }
        return false;
    }

    public int countMoviesPerGenre(String genre) {
        int genreCount = 0;
        for (FullMovie m : movieMap.values()) {
            if (m.getGenre().toLowerCase().equals(genre.toLowerCase()))
                genreCount++;
        }
        return genreCount;
    }

    public Actor actorInMostMovies() {
        Actor prev = null;
        int count = -1;
        for (Actor actor : actorMap.values()) {
            int currentActorCount = actor.starredMovies();
            if (currentActorCount > count) {
                prev = actor;
                count = currentActorCount;
            }
        }
        return prev;
    }

    public String genreWithHighestAverage() {
        HashMap<String, Scores> genreWithScore = new HashMap<>();
        for (FullScore s : scoreList) {
            FullMovie m = s.getMovie();
            String gen = m.getGenre();
            int score = s.getScore();
            Scores old = genreWithScore.get(gen);
            if (old == null) {
                Scores sc = new Scores();
                genreWithScore.put(gen, sc);
                old = sc;
            }
            old.add(score);
        }
        String genre = null;
        double avg = -1;
        for (Map.Entry<String,Scores> g : genreWithScore.entrySet()) {
            double s;
            if ((s = g.getValue().average()) > avg) {
                genre = g.getKey();
                avg = s;
            }
        }
        return genre;
    }


    public Actor getActor(String mainActorKey) {
        return actorMap.get(mainActorKey);
    }


    public FullMovie getMovie(String movieName) {
        return movieMap.get(movieName);
    }

    public User getUser(String userName) {
        return userMap.get(userName);
    }

    public void dump(File f) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(f);
        for (Actor a : actorMap.values()) {
            writer.println(a.toString());
        }
        for (FullMovie m : movieMap.values()) {
            writer.println(m.toString());
        }
        for (User u : userMap.values()) {
            writer.println(u.toString());
        }
        for (FullScore s : scoreList) {
            writer.println(s.toString());
        }
        writer.close();
    }

    public void createActor(String... fields) {
        ArrayList<DataObserver> elements;
        String key = fields[0]+" "+fields[1];
        Actor a = new Actor(fields);
        actorMap.putIfAbsent(key,a);

        // Se esistono elementi che sono in attesa della creazione di questo attore prima inesistente
        if ((elements = movieWithMissingActor.get(key)) != null) {
            // Notifico ciascun film della creazione dell'attore, di modo che anche il film x possa essere creato
            for (DataObserver x : elements) {
                x.notify(a);
            }
            // Pulisco la mappa liberando memoria( ho creato tutti i film e l'attore esiste,
            // quindi non ci potranno essere altri film in attesa dell'attore a)
            movieWithMissingActor.remove(key);
        }
    }

    public void createUser(String[] args) {
        User u = new User(args);
        userMap.putIfAbsent(args[0], u);
        ArrayList<DataObserver> observers = this.scoreWithMissingUser_.get(args[0]);

        // Se esistono valutazioni che richiedono l'esistenza dell'utente corrente
        if (observers != null) {
            // Ottengo quali sono gli osservatori da rimuovere
            ArrayList<DataObserver> toRemove = new ArrayList<>();
            for (DataObserver x : observers) {
                // Se l'informazione dell'utente u era l'ultima rimasta per la creazione della valutazione
                // (ovvero, esisteva già il film), allora questo osservatore non serve più a nulla e lo rimuoverò
                if (x.notifyScore(u))
                    toRemove.add(x);
            }
            // Rimuovi gli osservatori inutili
            observers.removeAll(toRemove);
            // Se è rimasta una lista vuota, allora elimina anche la chiave
            if (observers.isEmpty())
                scoreWithMissingUser_.remove(args[0]);
        }
    }

    // Simile al metodo di sopra, ma per i film.
    public void createMovie(Actor a, String... args) {
        FullMovie fm = new FullMovie(args[0], new CalendarDate(args[1]), args[2], a);
        movieMap.putIfAbsent(args[0], fm);
        ArrayList<DataObserver> observers = scoreWithMissingMovie.get(fm.getName());
        if (observers != null) {
            ArrayList<DataObserver> toRemove = new ArrayList<>();
            for (DataObserver x : observers) {
                if (x.notifyScore(fm))
                    toRemove.add(x);
            }
            observers.removeAll(toRemove);
            if (observers.isEmpty())
                scoreWithMissingMovie.remove(fm.getName());
        }
    }

    public void createScore(String[] args, User u, FullMovie fm) {
        scoreList.add(new FullScore(u, fm, Integer.valueOf(args[2])));
    }
}
