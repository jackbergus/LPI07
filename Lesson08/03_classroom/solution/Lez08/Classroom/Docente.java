package Lez08.Classroom;

import java.util.ArrayList;

public class Docente {
    private ArrayList<Materia> insegnamenti;
    private String nome;
    private String cognome;

    public Docente(String nome, String cognome) {
        insegnamenti = new ArrayList<>();
        this.nome = nome;
        this.cognome = cognome;
    }

    public double mediaVoti(String materia) {
        int n = insegnamenti.size();
        for (int i = 0; i<n; i++) {
            if (insegnamenti.get(i).getNome().equals(materia)) {
                return insegnamenti.get(i).media();
            }
        }
        return 0;
    }

    public double contaBocciati(String materia) {
        int n = insegnamenti.size();
        for (int i = 0; i<n; i++) {
            if (insegnamenti.get(i).getNome().equals(materia)) {
                return insegnamenti.get(i).contaBocciati();
            }
        }
        return 0;
    }

    public Materia aggiungiMateria(String materia) {
        int n = insegnamenti.size();
        for (int i = 0; i<n; i++) {
            if (insegnamenti.get(i).getNome().equals(materia)) {
                return insegnamenti.get(i);
            }
        }
        Materia m = new Materia(materia);
        insegnamenti.add(m);
        return m;
    }

    public void verbalizza(Studente student, Materia toScore, int score) {
        if (student != null && toScore != null) {
            int n = insegnamenti.size();
            for (int i = 0; i<n; i++) {
                if (insegnamenti.get(i).getNome().equals(toScore.getNome())) {
                    toScore.aggiungiEsame(student, score);
                }
            }
        }
    }

}
