import java.util.ArrayList;
import java.util.Scanner;

public class RestaurantDriver {

    public static void main(String args[]) {
        ArrayList<Dish> menu = new ArrayList<>();
        Restaurant restaurant = new Restaurant();
        Scanner s = new Scanner(System.in);


        System.out.println("Commands: ");
        System.out.println("ADD: aggiunge un determinato piatto ad un determinato prezzo.");
        System.out.println("FINDPRICE: cerca il prezzo di un determinato piatto.");
        System.out.println("PRINTMENU: stampa il menù del ristorante, incolonnando i prezzi.");
        System.out.println("BILL: Stampa la somma finale del conto di un cliente.");
        System.out.println();

        while (true) {
            System.out.print("$ ");
            String command = s.nextLine();
            if (command.equals("ADD")) {
                Dish d = new Dish();
                System.out.print("Insert the dish's name: ");
                d.setName(s.nextLine());
                System.out.print("Insert the dish's price (€): ");
                double prezzo = Double.valueOf(s.nextLine());
                d.setPrice(prezzo);
                restaurant.addToMenu(d);
            } else if (command.equals("FINDPRICE")) {
                System.out.print("Dish name: ");
                String piatto = s.nextLine();
                System.out.println("Its price is: " + restaurant.findPrice(piatto));
            } else if (command.equals("PRINTMENU")) {
                System.out.println(restaurant.toString());
            } else if (command.equals("BILL")) {
                String record;
                double count = 0;
                System.out.print("dish>");
                ArrayList<BillItem> items = new ArrayList<>();
                while (!(record = s.nextLine()).equals(":quit")) {
                    int noPortate = 1;
                    int index = record.indexOf(':');
                    if (index != -1) {
                        noPortate = Integer.valueOf(record.substring(0, index));
                    }
                    String dish = record.substring(index+1).trim();
                    items.add(new BillItem(dish, noPortate));
                    System.out.print("dish>");
                }
                System.out.println("SUM: " + restaurant.bill(items)+"€");
            }
        }


    }


}
