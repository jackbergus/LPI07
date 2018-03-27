package esercizio4;

public class Brano {
    private String titolo;
    private int numeroTraccia;
    private int secondi;

    public Brano(String titolo, int numeroTraccia, int secondi) {
        this.titolo = titolo;
        this.numeroTraccia = numeroTraccia;
        this.secondi = secondi;
    }

    public int getSecondi() {
        return secondi;
    }
}
