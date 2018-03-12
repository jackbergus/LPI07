public class BillItem {
    private String dish;
    private int no;

    public BillItem(String dish, int no) {
        this.dish = dish;
        this.no = no;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }
}
