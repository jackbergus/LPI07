package Lez08.Invert;

import java.util.Random;

public class InvertArray {

    public static char[] invertArrayRecursive(char[] array, int left, int right) {
        if (right != -1 && left < right) {
            char tmp = array[left];
            array[left] = array[right];
            array[right] = tmp;
            return invertArrayRecursive(array, left+1, right-1); // optional return
        } else return array;
    }

    public static char[] invertArray(char[] array) {
        return invertArrayRecursive(array, 0, array.length-1); // optional return
    }

    public static boolean arrayEq(char[] left, char[] right) {
        if (left.length != right.length)
            return false;
        else {
            for (int i= 0; i<left.length; i++) {
                if (left[i]!=right[i])
                    return false;
            }
            return true;
        }
    }

    public static void main(String args[]) {
        int max = 10000000;
        double count = 0.0;
        Random r = new Random();
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i<max; i++) {
            int len = r.nextInt(100);
            char arr[] = new char[len];
            char[] rev = new char[len];
            for (int j=0; j<len; j++) {
                arr[j] = rev[len-j-1] = alphabet.charAt(r.nextInt(alphabet.length()));
            }
            arr = invertArray(arr);
            if (arrayEq(arr, rev)) count++;
        }
        System.out.println((count/(max*1.0))*30);
    }

}
