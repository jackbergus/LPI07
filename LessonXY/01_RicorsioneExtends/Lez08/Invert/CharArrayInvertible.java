package Lez08.Invert;

public class CharArrayInvertible extends Invertible {

    char[] element;
    public CharArrayInvertible(char[] element) {
        this.element = element;
    }

    @Override
    public int length() {
        return element.length;
    }

    @Override
    public char get(int pos) {
        return element[pos];
    }

    @Override
    public void set(int pos, char x) {
        element[pos] = x;
    }
}
