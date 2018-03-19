package Lez09.imdb.simple.test;

import Lez09.imdb.simple.Driver;

import java.io.*;
import java.nio.file.Files;
import java.util.HashSet;

public class TestCorrectIMDB {
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

    public void redirectInput(File filename) throws FileNotFoundException {
        System.setIn(new FileInputStream(filename));
    }

    public void evaluate() throws IOException {
        String format = String.format("%%0%dd", 2);
        double score = 0;
        for (int i = 0; i<=3; i++) {
            String file = String.format(format, i);
            redirectInput(new File("tests/"+i+"/query.txt"));
            redirectOutput();
            Driver.main(null);
            String result = restoreOutput();
            String expected = new String(Files.readAllBytes(new File("tests/"+i+"/answer.txt").toPath()));
            if (result.equals(expected)) {
                System.out.println("Test "+i+": OK!");
                score++;
            } else {
                System.err.println("Test "+i+": Fail");
            }
            if (new HashSet<>(Files.readAllLines(new File("tests/"+i+"/db.txt").toPath())).equals(
                    new HashSet<>(Files.readAllLines(new File("d.txt").toPath()))
            )) {
                score++;
            }
        }
        System.out.println("Score: " + ((score/8.0)*30.0));
        File dump = new File("d.txt");
        if (dump.exists()) {
            dump.delete();
        }
    }

    public static void main(String args[]) throws Exception {
        new TestCorrectIMDB().evaluate();
    }

}
