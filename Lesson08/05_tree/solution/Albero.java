public class Albero {
    private int value;
    private int count;
    private Albero left;
    private Albero right;

    public Albero() {
        value = 0;
        count = 0;
        left = null;
        right = null;
    }

    public boolean isLeaf() {
        return (left == null && right == null);
    }

    public int setValue(int newValue) {
        int old = value;
        if (newValue != value) {
            count = 1;
        } else if (count == 0) {
            count = 1;
        }
        value = newValue;
        return old;
    }

    public int getValue() {
        return value;
    }

    public Albero getLeft() {
        return left;
    }

    public Albero getRight() {
        return right;
    }

    public int getCurrentInstances() {
        return this.count;
    }

    public int countNumbers() {
        int count = this.count;
        if (left != null) {
            count += left.countNumbers();
        }
        if (right != null) {
            count += right.countNumbers();
        }
        return count;
    }

    public Albero addUniqueValue(int newValue) {
        if (newValue < value) {
            if (left != null) {
                return left.addUniqueValue(newValue);
            } else {
                left = new Albero();
                left.setValue(newValue);
                return left;
            }
        } else if (newValue > value) {
            if (right != null) {
                return right.addUniqueValue(newValue);
            } else {
                right = new Albero();
                right.setValue(newValue);
                return right;
            }
        } else {
            count++;
            return this;
        }
    }

    public void addAll(Albero values) {
        if (values != null) {
            addAll(values.getLeft());

            int value = values.getValue();
            int count = values.getCurrentInstances();
            for (int i = 0; i<count; i++) {
                addUniqueValue(value);
            }

            addAll(values.getRight());
        }
    }

    public int sum() {
        int somma = 0;
        if (left != null) {
            somma += left.sum();
        }
        if (right != null) {
            somma += right.sum();
        }
        return somma;
    }

    @Override
    public String toString() {
        String toreturn = String.valueOf(value);
        if (left != null) {
            toreturn = left.toString()+","+toreturn;
        }
        if (right != null) {
            toreturn += ","+right.toString();
        }
        return toreturn;
    }


}
