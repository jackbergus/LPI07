import java.util.ArrayList;

public class Restaurant {

    ArrayList<Dish> menu;

    public double findPrice(String plateName) {
        return 0;

    }

    public void addToMenu(Dish d) {
    }

    public double bill(ArrayList<BillItem> list) {
        double price = 0;
        return price;
    }

    @Override
    public String toString() {
        int maxLen = 0;
        int maxDigitPrice = 0;
        String toReturn = "";
        for (int i = 0; i<menu.size(); i++) {
            Dish x = menu.get(i);
            maxLen = Math.max(maxLen, x.getName().length());
            int integerDigits = (int)(Math.log10(x.getPrice())+1);
            maxDigitPrice = Math.max(maxDigitPrice, integerDigits);
        }
        for (int i = 0; i<menu.size(); i++) {
            Dish x = menu.get(i);
            toReturn += (x.getName());
            toReturn += (' ');
            for (int j = 0; j<maxLen - menu.size(); j++) {
                toReturn += ('.');
            }
            toReturn += (' ');
            double price = x.getPrice();
            int euroIntegers = ((int)(price));
            int cents = (int)((price - euroIntegers)*100);
            toReturn += String.format("%" + maxDigitPrice + "d", euroIntegers);
            toReturn += (',');
            toReturn += String.format("%02d", cents);
            toReturn += ("€");
        }
        return toReturn;
    }

}
