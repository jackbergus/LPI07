package Lez08.Classroom;

import java.util.ArrayList;

public class Materia {
    private ArrayList<Voto> votoStudente;
    private String nome;

    public Materia(String materia) {
        this.nome = materia;
        this.votoStudente = new ArrayList<>();
    }

    public void aggiungiEsame(Studente student, int score) {
        int n = votoStudente.size();
	int toRemove = -1;
        for (int i = 0; i<n; i++) {
            if (votoStudente.get(i).getStudente().getMatricola().equals(student.getMatricola())) {
                if (votoStudente.get(i).getVoto() >= 18) {
			return;
		} else {
			toRemove = i;
			break;
		}
            }
        }
	if (toRemove >= 0) votoStudente.remove(toRemove);
        votoStudente.add(new Voto(student, score));
    }

    public boolean votoRegistrato(Studente student) {
        return votoStudente(student) >= 18;
    }

    public boolean promosso(Studente student) {
        return votoStudente(student) >= 18;
    }

    public int contaBocciati() {
	int n = votoStudente.size();
	int bocciati = 0;
	for (int i = 0; i<n; i++) {
		if (votoStudente.get(i).getVoto() < 18)
			bocciati++;
	}
	return bocciati;
    }

    public int votoStudente(Studente student) {
        int n = votoStudente.size();
        for (int i = 0; i<n; i++) {
            if (votoStudente.get(i).getStudente().getMatricola().equals(student.getMatricola())) {
                return votoStudente.get(i).getVoto();
            }
        }
        return -1;
    }

    public String getNome() {
        return nome;
    }

    public double media() {
        if (votoStudente.size() == 0) return 0;
        double sum = 0.0;
        double count = 0.0;
        int n = votoStudente.size();
        for (int i = 0; i<n; i++) {
            double voto = votoStudente.get(i).getVoto();
            if (voto >= 18) {
                sum += votoStudente.get(i).getVoto();
                count++;
            }
        }
        return sum / count;
    }
}
