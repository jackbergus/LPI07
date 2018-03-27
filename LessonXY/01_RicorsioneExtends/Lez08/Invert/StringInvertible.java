package Lez08.Invert;

public class StringInvertible extends Invertible {

    public StringInvertible(String s) {
        this.s = s;
    }

    String s;

    @Override
    public int length() {
        return s.length();
    }

    @Override
    public char get(int pos) {
        return s.charAt(pos);
    }

    @Override
    public void set(int pos, char element) {
        String tmps = s.substring(0,pos)+element;
        if (pos < s.length()-1)
            tmps += s.substring(pos+1);
        s = tmps;
    }
}
