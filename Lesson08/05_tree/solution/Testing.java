import java.util.HashSet;
import java.util.Random;
import java.util.stream.Collectors;

public class Testing {

    public static void assertion(boolean test, String message) {
        if (!test) {
            throw new RuntimeException(message);
        }
    }

    public static boolean checkAlberoBalanced(Albero albero, int level, String part) {
        if (albero == null) {
            return true;
        } else if (albero.isLeaf()) {
            return true;
        } else {
            Albero left = albero.getLeft();
            if (left != null) {
                assertion(left.getValue() < albero.getValue(), "Unbalanced tree at level: " + level+" an in branch: " + part);
                checkAlberoBalanced(left, level+1, part+"L");
            }

            Albero right = albero.getRight();
            if (right != null) {
                assertion(right.getValue() > albero.getValue(), "Unbalanced tree at level: " + level+" an in branch: " + part);
                checkAlberoBalanced(left, level+1, part+"R");
            }

            return true;
        }
    }

    public static void main(String args[]) {
        for (int j = 0; j < 2000 ; j++) {
            Albero albero1 = new Albero();

            final int N = 200;
            final int max = 50;

            // Test 1) an empty tree has a sum of values as 1
            assertion(albero1.sum() == 0, "Error: the Lez08.Tree.Albero's sum is not equal to zero.");

            // Test 2) an empty tree is a node
            assertion(albero1.isLeaf(), "Error: an empty Lez08.Tree.Albero must be a node.");

            Random randomNumberGen = new Random();
            int first = randomNumberGen.nextInt(max);
            HashSet<Integer> test = new HashSet<>();
            test.add(first);
            albero1.setValue(first);
            checkAlberoBalanced(albero1, 0, "R");
            assertion(albero1.toString().equals(first+""), "Cannot print the root value");

            for (int i = 0; i<N-1; i++) {
                int next = randomNumberGen.nextInt(max);
                albero1.addUniqueValue(next);
                checkAlberoBalanced(albero1, 0, "R");

                test.add(next);
                String current = test.stream().sorted().map(Object::toString).collect(Collectors.joining(","));
                assertion(albero1.toString().equals(current), current+" is not returned by albero="+albero1.toString());
            }

            assertion(albero1.countNumbers() == N, "Did not add N elements, N="+N);

            Albero albero2 = new Albero();
            albero2.setValue(max);
            test.add(max);

            for (int i = 0; i<N-1; i++) {
                int current = randomNumberGen.nextInt(max*2);
                albero1.addUniqueValue(current);
                test.add(current);
            }

            albero1.addAll(albero2);
            assertion(albero1.countNumbers() == (N*2), "Did not add N elements, N="+(N*2));
            String expected = test.stream().sorted().map(Object::toString).collect(Collectors.joining(","));
            String obtained = albero1.toString();
            assertion(obtained.equals(expected), expected+" is not returned by albero="+obtained);

            System.out.println("Great, everything is OK! (Test #"+j+")");
        }
    }

}
