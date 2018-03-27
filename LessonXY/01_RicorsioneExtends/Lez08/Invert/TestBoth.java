package Lez08.Invert;

import java.util.Random;

public class TestBoth {

    public static void main(String args[]) {
        int max = 10000000;
        double count = 0.0;
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        InvertInvertible ii = new InvertInvertible();
        for (int i = 0; i<max; i++) {
            if (r.nextBoolean()) {
                int len = r.nextInt(100);
                char arr[] = new char[len];
                char[] rev = new char[len];
                for (int j=0; j<len; j++) {
                    arr[j] = rev[len-j-1] = alphabet.charAt(r.nextInt(alphabet.length()));
                }
                CharArrayInvertible c = new CharArrayInvertible(arr);
                CharArrayInvertible j = new CharArrayInvertible(rev);
                if (ii.invert(c).equals(j)) count++;
            } else {
                int len = r.nextInt(5);
                String arr = "";
                String rev = "";
                for (int j=0; j<len; j++) {
                    char curr = alphabet.charAt(r.nextInt(alphabet.length()));
                    arr = arr + curr;
                    rev = curr + rev;
                }
                StringInvertible c = new StringInvertible(arr);
                StringInvertible j = new StringInvertible(rev);
                if (ii.invert(c).equals(j)) count++;
            }
        }
        System.out.println((count/(max*1.0))*30);
    }

}
