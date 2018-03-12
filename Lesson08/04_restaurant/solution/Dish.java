import java.util.Objects;

public class Dish {

    private double price;
    private String name;

    public Dish() {
    }

    public Dish(double price, String name) {
        this.price = price;
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
