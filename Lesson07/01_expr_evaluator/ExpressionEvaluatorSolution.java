import java.util.Objects;

public class ExpressionEvaluatorSolution {

    public static class PairOfIntegers {
        public int left;
        public int right;

        public PairOfIntegers(int i, int i1) {
            this.left = i;
            this.right = i1;
        }
    }

    /**
     * Resolve the algebraic signs applied to a number. For example, if there are two consecutive negated signs, they are elided.
     * In all the remaining cases, negation provides a negative sign to an integer.
     *
     * @param signExpr
     * @return
     */
    public static int signReduction(String signExpr) {
        boolean positive = true;
        for (int i = 0; i<signExpr.length(); i++) {
            // One negative sign changes the sign of the whole expression.
            if (signExpr.charAt(i) == '-') {
                positive = !positive;
            }
        }
        // Boolean sign to signed integer conversion
        return positive ? 1 : -1;
    }

    /**
     * Given a string str, return index if the substring of str starting from start which contains an integer with no signs
     * @param str       String to evaluate from start
     * @param start     Starting point from which evaluate the digits in str
     * @return          The index where the digit from start ends
     */
    public static int rightInteger(String str, int start) {
        int pos = -1;
        char current;
        // Precondition: the string starts at start without a sign.
        // The string starts with a digit
        for (int i = start; i<str.length(); i++) {
            current = str.charAt(i);
            if (Character.isDigit(current)) {
                pos = i;
            } else break;
        }
        return pos;
    }

    /**
     * Associates to a number its sign
     * @param expr   string, where an algebraic sign has to be associated to the number
     * @return       A pair, where the left element is the signed number, and the right is the position from which continue the post-processing
     */
    public static PairOfIntegers signedNumber(String expr) {
        int partialResult = 0;
        int sign = 1;
        int posRight;
        // If the number starts with a plus, then...
        if (expr.startsWith("+") || expr.startsWith("-")) {
            int pos = 0;
            String buildSignExpr = ""; // Suggestion: this is the naive methodology. The more efficient one is using a StringBuilder...

            // Collect all the signs (until a digit is found)
            while ((expr.charAt(pos) == '+' || expr.charAt(pos) == '-') && (pos < expr.length())) {
                buildSignExpr += expr.charAt(pos);
                pos++;
            }

            // Associate a numerical sign to the string of pluses and minuses
            sign = signReduction(buildSignExpr);

            // Get the number at the right of such sign
            posRight = rightInteger(expr, pos);
            partialResult = Integer.valueOf(expr.substring(pos,posRight+1));
        } else {
            // If there is no sign, then just evaluate the number
            posRight = rightInteger(expr, 0);
            partialResult = Integer.valueOf(expr.substring(0,posRight+1));
        }
        return new PairOfIntegers(sign*partialResult, posRight+1);
    }

    /**
     * Solves an algebraic expression of plus and minus signs. No parentheses and times are evaluated
     * @param expr      Algebraic sum expression
     * @return
     */
    public static int algebraicSumEvalutate(String expr) {
        expr = expr.replaceAll("\\s","");
        if (expr.isEmpty()) {
            return 0;
        }
        int partialResult = 0;
        // The evaluation of an algebraic sum can be defined by getting
        // recursively the first integer (poi.left), and then evaluating the
        // string over the rest, starting from poi.right
        PairOfIntegers poi = signedNumber(expr);
        return poi.left+algebraicSumEvalutate(expr.substring(poi.right));
    }

    /**
     * This function checks if s is a balanced string.
     * @param s             String to check
     * @param index         Index from which start the scan
     * @param newelement    Position of the last opened bracket
     * @return              Returns null if either there are no parentheses, or if they are not balanced
     */
    public static PairOfIntegers checkParentheses(String s, int index, int newelement) {
        PairOfIntegers toreturn = new PairOfIntegers(index+1,-1);
        for(int i = toreturn.left; i < s.length(); i++) {
            char c = s.charAt(i);
            // If we meet an opened parenthesis
            if(c == '(') {
                toreturn.left = i;
                // put the parenthesis as a newelement, and continue
                return checkParentheses(s, i, c);
            } else if(c == ')') {
                // If we previosly met no open parenthesis, then the function fails
                // Please observe: 
                // 1) Instead of using recursion, we can create an iterative 
                //    function using a Stack function
                // 2) Recursive functions are evaluated using Stacks at the JVM
                //    level (check: what StackOverflow means?) ;D
                if(newelement == -1) {
                    return null;
                } else {
                    // Otherwise, return the match
                    toreturn.right = i;
                    return toreturn;
                }
            }
        }
        // If we had no match and the right parameter was always -1, then fail
        return toreturn.right == -1 ? null : toreturn;
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
        int pos = -1;
        char current;
        // precondition: the string at start contains a digit and, while 
        //               traversing str backwards, we may meet a signed element
        // precondition: this function will not be used within the evaluation
        //               of an algebraic sum, therefore, we can only meet a 
        //               plus or a minus. 
        for (int i = start; i>=0; i--) {
            current = str.charAt(i);
            if (current == '-'||current == '+') {
                if (i==0) {
                    pos = i;
                    break;
                } else {
                    char prev = str.charAt(i-1);
                    if (Character.isDigit(prev)) {
                        break;
                    } else if (!hasStringNoMathOperator(prev+"")) {
                        pos = i;
                    }
                }
            }
            if (Character.isDigit(current)) {
                pos = i;
            } else break;
        }
        return pos;
    }

    /**
     * Evaluates an integer expression without division
     * @param expr      String representation of an expression
     * @return          Evaluated value
     */
    public static int evaluate(String expr) {
	expr = expr.replaceAll("\\s","");


        // Checking if the expression contains a balanced parenthesis
        if (expr.contains("(") || expr.contains(")")) {
            // Getting the first balanced parenthesis
            PairOfIntegers leftRight = checkParentheses(expr,-1,-1);
            if (leftRight != null) {
                // Evaluate the expression within the parentheses first
                int result = evaluate(expr.substring(leftRight.left, leftRight.right));

                // Replace the parentheses with the evaluated expression
                String left = leftRight.left != 1 ? expr.substring(0, leftRight.left-1) : "";
                String right = leftRight.right != expr.length() -1 ? expr.substring(leftRight.right+1) : "";
                expr = left + " " + result + " " + right;
                expr = expr.trim();

                // Evaluate recursively the remaining part
                return evaluate(expr);
            } else {
                // If the expression contains unbalanced parentheses, return 0
                return 0;
            }
        } else {
            // If the string contains no operator
            if (hasStringNoMathOperator(expr))
                // ... then, return the simple number
                return Integer.valueOf(expr);
            else {
                // Otherwise, we must always evaluate the "times first".
                // Given that inductively we always evaluate an expression, in here we won't get any
                int timesIndex = expr.indexOf('*');
                if (timesIndex != -1) {
                    // Position where the left integer starts from
                    int posLeft = leftInteger(expr, timesIndex-1);

                    // Position where the right integer starts from. Such integer may start with a negated sign
                    int posRight ;
                    if (expr.charAt(timesIndex+1) == '-') {
                        posRight = rightInteger(expr, timesIndex+2);
                    } else {
                        posRight = rightInteger(expr, timesIndex+1);
                    }

                    // If there is a match for both left and right parentheses
                    if (posLeft != -1 && posRight != -1) {
                        // Left number
                        String leftExpr = expr.substring(posLeft,timesIndex);

                        // Right number
                        String rightExpr = posRight == expr.length()-1 ? expr.substring(timesIndex+1) : expr.substring(timesIndex+1,posRight+1);

                        // Compute the evaluation
                        int result =  evaluate(leftExpr)*evaluate(rightExpr);

                        // Expression at the left of the times operation
                        String left = expr.substring(0, posLeft);

                        // Expression at the right of the times operation
                        String right = posRight != expr.length() -1 ? expr.substring(posRight+1) : "";

                        // Evaluate the remaining part of the expression
                        expr = left + " " + result + " " + right;
                        return evaluate(expr);
                    }
                } else {
                    // If the expression does not contain a * operator, then evaluate the algebraic sum
                    return algebraicSumEvalutate(expr);
                }
            }
        }
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
