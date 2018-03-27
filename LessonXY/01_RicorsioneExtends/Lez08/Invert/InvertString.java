package Lez08.Invert;

import java.util.Random;

public class InvertString {

    public static String invertString(String array) {
        if (array.length() > 1) {
            return array.charAt(array.length()-1)+invertString(array.substring(1,array.length()-1))+array.charAt(0); // optional return
        } else return array;
    }

    public static void main(String args[]) {
        int max = 10000000;
        double count = 0.0;
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i<max; i++) {
            int len = r.nextInt(5);
            String arr = "";
            String rev = "";
            for (int j=0; j<len; j++) {
                char curr = alphabet.charAt(r.nextInt(alphabet.length()));
                arr = arr + curr;
                rev = curr + rev;
            }
            arr = invertString(arr);
            if (arr.equals(rev)) count++;
        }
        System.out.println((count/(max*1.0))*30);
    }

}
