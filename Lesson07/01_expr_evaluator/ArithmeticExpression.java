import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ArithmeticExpression {

    private String op;
    private int number;
    private ArithmeticExpression left, right;
    private boolean isCenter;
    private boolean isLeaf;

    public ArithmeticExpression(int number) {
        this.number = number;
        isLeaf = true;
        isCenter = false;
    }

    public ArithmeticExpression(ArithmeticExpression left, String op, ArithmeticExpression right) {
        this.op = op;
        this.left = left;
        this.right = right;
        isLeaf = false;
        isCenter = false;
    }

    public ArithmeticExpression(ArithmeticExpression center) {
        isLeaf = false;
        isCenter = true;
        this.left = center;
        this.right = null;
    }

    public String eval() {
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        try {
            return engine.eval(toString()).toString();
        } catch (ScriptException e) {
            return "0";
        }
    }

    @Override
    public String toString() {
        if (isLeaf) {
            return number+"";
        } else if (isCenter) {
            return "("+left.toString()+")";
        } else {
            return left+op+right;
        }
    }

}
