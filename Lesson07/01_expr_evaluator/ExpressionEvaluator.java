public class ExpressionEvaluator {

    public static class PairOfIntegers {
        int left; // left parenthesis position
        int right; // right parenthesis position

        public PairOfIntegers(int l, int r) {
            this.left = l;
            this.right = r;
        }
    }

    /**
     * Resolve the algebraic signs applied to a number. For example, if there are two consecutive negated signs, they are elided.
     * In all the remaining cases, negation provides a negative sign to an integer.
     *
     * eg: no sign is  1
     *     +       is  1
     *     -       is -1
     *     ++      is 1
     *     any combination of solely + is 1
     *     +-+          is -1
     *     +-+--+++++-+ is -1
     *
     * @param signExpr
     * @return
     */
    public static int signReduction(String signExpr) {
        return -1;
    }

    /**
     * Given a string str, return index if the substring of str starting from start which contains an integer with no signs
     * @param str       String to evaluate from start
     * @param start     Starting point from which evaluate the digits in str
     * @return          The index where the digit from start ends
     */
    public static int rightInteger(String str, int start) {
        return -1;
    }

    /**
     * Associates to a number its sign
     * @param expr   string, where an algebraic sign has to be associated to the number
     * @return       A pair, where the left element is the signed number, and the right is the position from which continue the post-processing
     */
    public static PairOfIntegers signedNumber(String expr) {
        return new PairOfIntegers(-1, -1);
    }

    /**
     * Solves an algebraic expression of plus and minus signs. No parentheses and times are evaluated
     * @param expr      Algebraic sum expression
     * @return
     */
    public static int algebraicSumEvalutate(String expr) {
        return -1;
    }

    /**
     * This function checks if s is a balanced string.
     * @param s             String to check
     * @param index         Index from which start the scan
     * @param newelement    Position of the last opened bracket
     * @return              Returns null if either there are no parentheses, or if they are not balanced
     */
    public static PairOfIntegers checkParentheses(String s, int index, int newelement) {
        return null;
    }

    private static boolean hasStringNoMathOperator(String expr) {
        return ((!expr.contains("+")) && (!expr.contains("-")) && (!expr.contains("/")) && (!expr.contains("*")) && (!expr.contains("(")) && (!expr.contains(")")));
    }

    /**
     * After evaluating all the occurrences of the algebraic expressions, checks the integer appearing on the left
     * @param str       String over which get the leftmost integer
     * @param start     Right index, from which start to scan from right to left
     * @return
     */
    public static int leftInteger(String str, int start) {
        return -1;
    }

    /**
     * Evaluates an integer expression without division
     * @param expr      String representation of an expression
     * @return          Evaluated value. Returns zero if the expression is ill-formed
     */
    public static int evaluate(String expr) {
        expr = expr.replaceAll("\\s","");
        return 0;
    }

// DO NOT CHANGE THIS METHOD
    public static void assertion(boolean test, String message) {
        if (!test) {
            throw new RuntimeException(message);
        }
    }

    // DO NOT CHANGE THIS METHOD
    public static void main(String args[]) {
        // Basic tests
        ExpressionEvaluator e = new ExpressionEvaluator();

        System.out.println("Evaluate sign expressions:");
        assertion(signReduction("")==1, "No sign is positive");
        assertion(signReduction("+")==1, "Positive is positive");
        assertion(signReduction("-")==-1, "Negative is positive");
        assertion(signReduction("--")==1, "Double negative is positive");
        assertion(signReduction("---")==-1, "Triple negative is negative");
        assertion(signReduction("++")==1, "Double positive is positive");
        assertion(signReduction("+++")==1, "Triple positive is positive");


        System.out.println("Evaluating algebraic sums:");
        assertion(algebraicSumEvalutate("+10")==10,"plus detected");
        assertion(algebraicSumEvalutate("-10")==-10,"minus detected");
        assertion(algebraicSumEvalutate("10")==10,"no sign detected");
        assertion(algebraicSumEvalutate("10+20+30")==60,"good summation");
        assertion(algebraicSumEvalutate("10-20-30")==-40,"good subtractions (1)");
        assertion(algebraicSumEvalutate("-10-20-30")==-60,"good subtractions (2)");
        assertion(algebraicSumEvalutate("-10-20--30")==0,"good subtractions (3)");
        assertion(algebraicSumEvalutate("--10-20--30")==20,"good subtractions (4)");
        assertion(algebraicSumEvalutate("--10---20--30")==20,"good subtractions (5)");
        assertion(algebraicSumEvalutate("--10----20--30")==60,"good subtractions (6)");


        System.out.println("Evaluating balanced parentheses:");
        assertion(checkParentheses("asdf",-1,-1)==null, "no parentheses should return null");
	PairOfIntegers poi = checkParentheses("( asdsdf ) ( )",-1,-1);
        assertion(poi.left == 1 && poi.right == 9, "no match should return null");


        System.out.println("Evaluating simple expressions:");
        assertion(evaluate("123")==123, "Cannot parse an integer");
        assertion(evaluate("   123        ")==123, "Cannot trim an integer");
        assertion(evaluate("   (  123  )        ")==123, "Cannot detect parentheses");
        assertion(evaluate("   (  123  )        ")==123, "Cannot detect parentheses");
        assertion(evaluate("   (  123          ")==0, "Cannot detect unbalanced parentheses (1)");
        assertion(evaluate("     123       )   ")==0, "Cannot detect unbalanced parentheses (2)");
        assertion(evaluate("1 * 2")==2, "Wrong times");
        assertion(evaluate("  ( 1 * 2     )")==2, "Wrong times");
        assertion(evaluate("  ( 1 * 2     ) * 3")==6, "Wrong times and paren");
        assertion(evaluate("1+2*3")==7, "Wrong associativity");
        assertion(evaluate("1+2*3+4")==11, "Wrong associativity");
        assertion(evaluate("  1    + 2* 3+4    ")==11, "Wrong associativity with spaces");
        assertion(evaluate("(1+2*3)+(4*5+6*(7+8+9+10-11)+20)*(17+70*3)")==40413, "Wrong long expr evaluation");
        assertion(evaluate("((2))*((((2))-3*7))-8")==-46, "Wrong long expr evaluation");
        assertion(evaluate("(((6+7-2-((9)+(2)+(0+(9)))-4+((((5)-2+(1-(((4*(4))+5+4)-4-((((((0+7+1)*(0)-(8)))))+((((6*6)*4-0*(7+3)-8))))))*(4-7)+(4-(2+((5))*1*((7))-((9+8))-(7))-(6))-0*(6)-5*(2)*3-9+((6*4*(4+0)))+3*3*(9)-3+(9)+2)*((5))+6+((5))))*(6)-0+5)-(1)-2))+9*3+(((0))-((2))-(2))")==-5622,"Error with negation");

        System.out.println("Generating some problematic (random) test (10000)");
        ArithmeticExpressionGenerator aeg = new ArithmeticExpressionGenerator(10);
        for (int i = 0; i<10000; i++) {
            ArithmeticExpression expr = aeg.generate();
            String toCheck = expr.toString();
            int expectedValue = Double.valueOf(expr.eval()).intValue();
            assertion(evaluate(toCheck) == expectedValue, "Error: the following expression is expected to return " + expectedValue+":\n\n\t"+toCheck);
        }
        System.out.println("Everything is allright ");
    }

}
