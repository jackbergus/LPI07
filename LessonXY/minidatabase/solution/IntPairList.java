import java.util.ArrayList;

public class IntPairList {
    ArrayList<IntPair> list;

    public IntPairList() {
        list = new ArrayList<>();
    }

    public Integer getByValue(int value) {
        for (IntPair x : list) {
            if (x.getBy() == value)
                return x.getAcc();
        }
        return null;
    }

    public void updateByValue(int value, int acc) {
        for (IntPair x : list) {
            if (x.getBy() == value) {
                x.setAcc(acc);
                return;
            }
        }
        list.add(new IntPair(value, acc));
    }

    public ArrayList<IntPair> asList() {
        return list;
    }
}
