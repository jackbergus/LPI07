package Lez08.Invert;

public class Invertible {
    public int length() {
        return 0;
    }
    public char get(int pos) {
        throw new RuntimeException("Error: this is an empty element");
    }
    public void set(int pos, char element) {
        throw new RuntimeException("Error: this is an empty element");
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof  Invertible) {
            Invertible i = (Invertible)o;
            if (length() != i.length())
                return false;
            else {
                for (int j = 0; j<i.length(); j++) {
                    if (get(j) != i.get(j))
                        return false;
                }
                return true;
            }
        }
        return false;
    }

}
