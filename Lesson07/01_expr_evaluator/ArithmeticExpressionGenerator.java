import java.util.Random;

public class ArithmeticExpressionGenerator {

    private Random r;
    private int maxDepth;
    public ArithmeticExpressionGenerator(int maxDepth) {
        r = new Random();
        this.maxDepth = maxDepth;
    }

    public ArithmeticExpression generate() {
        return generate(0);
    }

    private ArithmeticExpression generate(int depth) {
        int x = r.nextInt(3);
        if (x == 0 || depth == this.maxDepth) {
            // Leaf
            return  new ArithmeticExpression(r.nextInt(10));
        } else if (x == 1) {
            return  new ArithmeticExpression(generate(depth+1));
        } else {
            int op = r.nextInt(3);
            return new ArithmeticExpression(generate(depth+1), op == 0 ? "+" : (op == 1 ? "*" : "-"), generate(depth+1));
        }
    }

}
