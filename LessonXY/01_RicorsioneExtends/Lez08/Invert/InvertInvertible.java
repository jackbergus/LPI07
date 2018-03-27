package Lez08.Invert;

public class InvertInvertible {

    public Invertible invert(Invertible i, int pLeft, int pRight) {
        if (pRight != -1 && pLeft < pRight) {
            char left = i.get(pLeft);
            char right = i.get(pRight);
            i.set(pLeft,right);
            i.set(pRight,left);
            return invert(i, pLeft+1, pRight-1);
        } else return i;
    }

    public Invertible invert(Invertible i) {
        return invert(i, 0, i.length()-1);
    }

}
