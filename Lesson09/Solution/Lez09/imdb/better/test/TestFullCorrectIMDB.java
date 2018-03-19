package Lez09.imdb.better.test;

import Lez09.imdb.better.FullDriver;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class TestFullCorrectIMDB {

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
            List<String> ls = Files.readAllLines(Paths.get("tests/" + i + "/query.txt"));
            List<String> commands = ls.subList(0, 6150);
            Collections.shuffle(commands);
            ArrayList<String> al = new ArrayList<>(commands);
            al.addAll(ls.subList(6150, ls.size()));
            Files.write(Paths.get("tests/" + i + "/shuffledQuery.txt"),al);
            redirectInput(new File("tests/"+i+"/shuffledQuery.txt"));
            redirectOutput();
            FullDriver.main(null);
            String result = restoreOutput();
            String expected = new String(Files.readAllBytes(new File("tests/"+i+"/answer.txt").toPath()));
            if (result.equals(expected)) {
                System.out.println("Test "+i+": OK! (1)");
                score++;
            } else {
                System.err.println("Test "+i+": Fail (1)");
                redirectInput(new File("tests/"+i+"/shuffledQuery.txt"));
                redirectOutput();
                FullDriver.main(null);
                result = restoreOutput();
            }
            if (new HashSet<>(Files.readAllLines(new File("tests/"+i+"/db.txt").toPath())).equals(
                    new HashSet<>(Files.readAllLines(new File("d.txt").toPath()))
            )) {
                score++;
                System.out.println("Test "+i+": OK! (2)");
            } else {
                System.err.println("Test "+i+": Fail (2)");
            }
        }
        System.out.println("Score: " + ((score/8.0)*30.0));
        File dump = new File("d.txt");
        if (dump.exists()) {
            dump.delete();
        }
    }

    public static void main(String args[]) throws Exception {
        new TestFullCorrectIMDB().evaluate();
    }

}
