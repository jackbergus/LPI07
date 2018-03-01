import java.util.ArrayList;
import java.util.Random;

public class StringGenerator {

    private Random r;
    public StringGenerator() {
        r = new Random();
    }

    private String generateRandomPalindrome(int len) {
        if (len <= 0)
            return "";
        else {
            int char_ = r.nextInt(3);
            char ch = char_ == 0 ? 'a' : (char_ == 1 ? 'b' : 'c');
            return ch+generateRandomPalindrome(len-1)+ch;
        }
    }

    private String insertRandomCharacter(String toUpdate, int step) {
        String extension = step == 0 ? "d" : (step == 1 ? "e" : "f");
        if (toUpdate.isEmpty()) {
            return "gh";
        }
        int pos = r.nextInt(toUpdate.length());
        if (pos == toUpdate.length() / 2 && (toUpdate.length() % 2 == 0)) {
            pos = 0;
        }
        return toUpdate.substring(0,pos)+(extension)+toUpdate.substring(pos);
    }

    public RandomString generateRandomPalindrome() {
        if (r.nextBoolean()) {
            return new RandomString(generateRandomPalindrome(r.nextInt(20)),true);
        } else {
            String palindrome = generateRandomPalindrome(r.nextInt(20));
            ArrayList<Integer> J = new ArrayList<>();
            for (int i = 0; i<1+r.nextInt(3); i++) {
                J.add(i);
                palindrome = insertRandomCharacter(palindrome, i);
            }
            return new RandomString(palindrome, false);
        }
    }

    public static class RandomString {
        public String left;
        public boolean isPalindrome;

        public RandomString(String str, boolean isPalindrome) {
            left = str;
            this.isPalindrome = isPalindrome;
        }
    }

}
