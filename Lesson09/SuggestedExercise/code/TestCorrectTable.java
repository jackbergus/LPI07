import java.io.*;
import java.nio.file.Files;

public class TestCorrectTable {

    ByteArrayOutputStream baos;
    PrintStream ps;
    PrintStream old;

    public void redirectOutput() {
        baos = new ByteArrayOutputStream();
        ps = new PrintStream(baos);
        old = System.out;
        System.setOut(ps);
    }

    public String restoreOutput() {
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }

    public void evaluate() throws IOException {
        File[] test = new File("query").listFiles();
        File[] expe = new File("expected").listFiles();
        String format = String.format("%%0%dd", 2);
        double score = 0;
        for (int i = 1; i<=8; i++) {
            String file = String.format(format, i);
            Interpreter interpreter = new Interpreter(new File("query/"+file+".txt"));
            redirectOutput();
            interpreter.evaluate();
            String result = restoreOutput();
            String expected = new String(Files.readAllBytes(new File("expected/"+file+".txt").toPath()));
            if (result.equals(expected)) {
                System.out.println("Test "+i+": OK!");
                score++;
            } else {
                System.err.println("Test "+i+": Fail");
            }
        }
	score = ((score/8.0)*30.0)+6.0;
	boolean merit = score > 30.0;
	if (merit) score = 30.0;
        System.out.println("Score: " + score + (merit ? " con lode ": ""));
    }

    public static void main(String args[]) throws Exception {
        new TestCorrectTable().evaluate();
    }

}
