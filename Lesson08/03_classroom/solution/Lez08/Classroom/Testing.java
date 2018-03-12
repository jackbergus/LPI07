package Lez08.Classroom;

public class Testing {

    public static void main(String args[]) {
        // Nome, cognome, matricola
        Studente stu1 = new Studente("1","1","1");
        Studente stu2 = new Studente("2","2","2");
        Studente stu3 = new Studente("3","3","3");
        Studente stu4 = new Studente("4","4","4");
        // nome, cognome
        Docente doc = new Docente("2","2");
        // nome materia
        Materia qualsiasi = new Materia("qualsiasi");

        System.out.println(doc.mediaVoti(qualsiasi.getNome()) == 0);
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);
        doc.verbalizza(stu1, qualsiasi, 30);

        System.out.println(doc.mediaVoti("qualsiasi") == 0);
        System.out.println(qualsiasi.votoStudente(stu1) == -1);
        System.out.println(!qualsiasi.promosso(stu1));
        System.out.println(!qualsiasi.votoRegistrato(stu1));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);

        qualsiasi = doc.aggiungiMateria(qualsiasi.getNome());
        System.out.println(doc.mediaVoti(qualsiasi.getNome()) == 0);

        doc.verbalizza(stu1, qualsiasi, 30);
        System.out.println(doc.mediaVoti("qualsiasi") == 30);
        System.out.println(qualsiasi.votoStudente(stu1) == 30);
        System.out.println(qualsiasi.promosso(stu1));
        System.out.println(qualsiasi.votoRegistrato(stu1));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);

        doc.verbalizza(stu1, qualsiasi, 20);
        System.out.println(doc.mediaVoti("qualsiasi") == 30);
        System.out.println(qualsiasi.votoStudente(stu1) == 30);
        System.out.println(qualsiasi.promosso(stu1));
        System.out.println(qualsiasi.votoRegistrato(stu1));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);


        doc.verbalizza(stu2, qualsiasi, 20);
        System.out.println(doc.mediaVoti("qualsiasi") == 25);
        System.out.println(qualsiasi.votoStudente(stu2) == 20);
        System.out.println(qualsiasi.promosso(stu2));
        System.out.println(qualsiasi.votoRegistrato(stu2));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);


        doc.verbalizza(stu3, qualsiasi, 17);
        System.out.println(doc.mediaVoti("qualsiasi") == 25);
        System.out.println(qualsiasi.votoStudente(stu3) == 17);
        System.out.println(!qualsiasi.promosso(stu3));
        System.out.println(!qualsiasi.votoRegistrato(stu3));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 1);

	doc.verbalizza(stu3, qualsiasi, 19);
        System.out.println(doc.mediaVoti("qualsiasi") == 23);
        System.out.println(qualsiasi.votoStudente(stu3) == 19);
        System.out.println(qualsiasi.promosso(stu3));
        System.out.println(qualsiasi.votoRegistrato(stu3));
        System.out.println(doc.contaBocciati(qualsiasi.getNome()) == 0);

        System.out.println(!qualsiasi.promosso(stu4));
        System.out.println(!qualsiasi.votoRegistrato(stu4));

    }

}
