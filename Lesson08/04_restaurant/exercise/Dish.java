import java.util.Objects;

public class Dish {

    private double price;
    private String name;

    public Dish() {
    }

    public Dish(double price, String name) {
    }

    @Override
    public boolean equals(Object dish) {
        if (!(dish instanceof Dish)) {
            return false;
        }
        Dish other =(Dish)dish;
        return price == other.price && Objects.equals(name, other.name);
    }

}
