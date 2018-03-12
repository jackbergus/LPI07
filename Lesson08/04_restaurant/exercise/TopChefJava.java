import java.util.*;

public class TopChefJava {

    private final static Random r = new Random();
    private final static String alphabetic = "abcdefghijklmnopqrstuvwxyz ";
    private static String randomString(int len) {
        int max = r.nextInt(len)+5;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i<max; i++) {
            sb.append(alphabetic.charAt(r.nextInt(alphabetic.length())));
        }
        return sb.toString();
    }
    private static int times() {
        return r.nextInt(6);
    }
    private static double randomPrice(int maxPrice) {
        return r.nextInt(maxPrice)*1.0+(r.nextInt(100)*0.01);
    }
    private static boolean randomAddToBill() {
        return r.nextBoolean();
    }

    private static boolean test(boolean test, String value) {
        if (!test) {
            System.err.println(value);
        }
        return test;
    }

    public static void main(String args[]) {
        Restaurant totest = new Restaurant();
        double MAX = 10000.0;

        double evaluateFindPriceNull = 0.0;
        for (double i = 0.0; i<MAX; i++) {
            if (test(totest.findPrice(randomString(20)) == 0.0, "Error: when the resturant has no menu, it should return no elements"))
                evaluateFindPriceNull++;
        }
        evaluateFindPriceNull /= MAX;

        ArrayList<Dish> dishes = new ArrayList<>();
        ArrayList<String> dishName = new ArrayList<>();
        for (int numdish = 0; numdish < MAX; numdish++) {
            int time = times();
            String dish = randomString(20);
            for (int i = 0; i<time; i++) {
                double price = randomPrice(100);
                dishes.add(new Dish(price,dish));
            }
            if (time > 0)
            dishName.add(dish);
        }
        Collections.shuffle(dishes);

        HashMap<String, Double> checker = new HashMap<>();

        double evaluateDishUpdate = 0.0;
        for (Dish dish : dishes) {
            totest.addToMenu(dish);
            checker.put(dish.getName(), dish.getPrice());
            if (test(totest.findPrice(dish.getName()) == dish.getPrice(), "Error: the newly inserted dish '"+dish.getName()+" must have price"+dish.getPrice()))
                evaluateDishUpdate++;
        }
        evaluateDishUpdate /= dishes.size();


        double evalBillTotal = 0.0;
        for (int i = 0; i<MAX; i++) {
            ArrayList<BillItem> items = new ArrayList<>();
            int count = 0;
            for (String name : dishName) {
                if (randomAddToBill() && (count < 3)) {
                    items.add(new BillItem(name, times()));
                    count++;
                }
            }

            double price = 0;
            for (BillItem x : items) {
                price = price + (x.getNo()*checker.get(x.getDish()));
            }
            if (test(totest.bill(items) == price, "The bill evaluation is not correct"))
                evalBillTotal++;
        }
        evalBillTotal /= MAX;

        System.out.println("Score: "+(evaluateFindPriceNull+evaluateDishUpdate+evalBillTotal)*10.0);


    }

}
