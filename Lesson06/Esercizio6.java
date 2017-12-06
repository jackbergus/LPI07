

import java.util.Scanner;

public class Esercizio6 {

    /*
     *   Creare un programma driver per la gestione di una rubrica telefonica, dove si hanno due metodi statici (add
     *   e search) per l'inserimento di nomi e numeri in rubrica, e la ricerca di nomi all'interno della rubrica.
     *   Consentire l'inserimento di massimo 5 nomi in rubrica.
     *
     *   Svolgere l'esercizio similarmente all'esercizio del Ristorante visto a lezione.
     */

    public static void add(String[] names, String[] numbers, int size, int max, String name, String phone) {
        if (size < max) {
            names[size] = name;
            numbers[size] = phone;
        } else {
            System.err.println("Error: the AddressBook is full");
        }
    }

    public static String search(String[] names, String[] numbers, int size, String name) {
        for (int i = 0; i<size; i++) {
            if (names[i].equals(name)) {
                return "Phone: " + numbers[i];
            }
        }
        return "Not found";
    }

    public static void main(String[] args) {
        String[] nomi = new String[5];
        String[] numeri = new String[5];
        int count = 0;
        String line;
        Scanner scanner = new Scanner(System.in);
        while ((line = scanner.nextLine()) != null && !line.equals(":exit")) {
            if (line.equals("add") && (count<5)) {
                System.out.print("Insert the person's name: ");
                String name = scanner.nextLine();
                System.out.print("Insert the person's phone number: ");
                String phone = scanner.nextLine();
                add(nomi, numeri, count++, 5, name, phone);
            } else if (line.equals("search")) {
                System.out.print("Insert the person's name: ");
                String name = scanner.nextLine();
                System.out.println(search(nomi, numeri, count, name));
            }
        }
    }

}
