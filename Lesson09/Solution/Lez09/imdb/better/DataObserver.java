package Lez09.imdb.better;

import java.util.Arrays;
import java.util.Objects;

/**
 * Classe che mi permette di posticipare la creazione del nuovo oggetto al momento in cui io abbia effettivamente
 * tutta la informazione necessaria. Se così non dovesse essere, memorizzo solamente i parametri in mio possesso.
 */
public class DataObserver {
    private final String[] args;
    private final FullDatabase db;
    private FullMovie fm;
    private User u;

    /**
     *
     * @param db        Database al quale demandare la creazione dell'oggetto, qualora io possegga l'informazione completa
     * @param fields    Campi che saranno utili nel creare il nuovo oggetto
     */
    public DataObserver(FullDatabase db, String... fields) {
        this.args = fields;
        this.db = db;
    }

    /**
     * Notifico al database che l'attore è pronto, e che quindi posso creare il film aggiungendo l'oggetto mancante, che è l'attore
     * @param a
     */
    public void notify(Actor a) {
        db.createMovie(a, args);
    }

    public boolean notifyScore(FullMovie fm) {
        this.fm = fm;
        if (u != null) {
            // Posso effettuare la creazione di un oggetto score solamente quando conosco sia il film, sia l'utente
            db.createScore(args,u,fm);
            return true;
        }
        // Se non conosco ancora entrambe le informazioni, debbo aspettare
        return false;
    }

    public boolean notifyScore(User u) {
        this.u = u;
        if (fm != null) {
            // Posso effettuare la creazione di un oggetto score solamente quando conosco sia il film, sia l'utente
            db.createScore(args,u,fm);
            return true;
        }
        // Se non conosco ancora entrambe le informazioni, debbo aspettare
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DataObserver that = (DataObserver) o;
        return Arrays.equals(args, that.args) &&
                Objects.equals(db, that.db) &&
                Objects.equals(fm, that.fm) &&
                Objects.equals(u, that.u);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(db, fm, u);
        result = 31 * result + Arrays.hashCode(args);
        return result;
    }
}
