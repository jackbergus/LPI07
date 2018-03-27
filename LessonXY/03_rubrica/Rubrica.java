package Lez10;

import java.util.ArrayList;
import java.util.Scanner;

public class Rubrica {

    public static void main(String args[]) {

        ArrayList<Voce> al = new ArrayList<>();
        Scanner s = new Scanner(System.in);
        while (s.hasNext()) {
            String ln = s.nextLine();
            if (ln.equals(":add")) {
                if (ln.length() < 100) {
                    System.out.print("name: ");
                    String name = s.nextLine();
                    System.out.print("tel: ");
                    String tel = s.nextLine();
                    Voce v = new Voce(name,tel);
                    if (!al.contains(v))
                        al.add(v);
                } else {
                    System.out.println(ln.length() +": maximum size reached.");
                }

            } else if (ln.equals(":search")) {
                System.out.print("name: ");
                String name = s.nextLine();
                boolean found = false;
                for (Voce v : al) {
                    if (v.getNome().equals(name)) {
                        System.out.println("tel: "+v.getTel());
                        found = true;
                        break;
                    }
                }
                if (!found)
                System.out.println(name+ " not found");
            } else if (ln.equals(":print")) {
                for (Voce v : al) {
                    System.out.println(v.toString());
                }
            } else if (ln.equals(":quit")) {
                return;
            } else {
                System.out.println("Wrong command");
            }
        }

    }

}
