package Lez08.Classroom;

public class Voto {
    private Studente studente;
    private int voto;

    public Voto(Studente studente, int voto) {
        this.studente = studente;
        this.voto = voto;
    }

    public Studente getStudente() {
        return studente;
    }

    public int getVoto() {
        return voto;
    }
}
