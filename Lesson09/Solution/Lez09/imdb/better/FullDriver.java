package Lez09.imdb.better;

import Lez09.imdb.simple.Database;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FullDriver {

    private final static String ADD = ":add ";
    private final static String DUMP = ":dump ";
    private final static String LOAD = ":load ";
    private final static String MOVIESFORGENRE = "?noMoviesForGenre ";
    public static FullDatabase db;

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        db = new FullDatabase();
        while (s.hasNext()) {
            String line = s.nextLine();
            if (line.equals(":q")) {
                return;
            }
            if (line.startsWith(ADD)) {
                db.scanLine(line.substring(ADD.length()));
            } else if (line.startsWith(LOAD)) {
                try {
                    db.load(new File(line.substring(LOAD.length())));
                } catch (FileNotFoundException e) {
                    System.err.println("Error: file not found");
                }
            } else if (line.startsWith(DUMP)) {
                try {
                    db.dump(new File(line.substring(DUMP.length())));
                } catch (FileNotFoundException e) {
                    System.err.println("Error: file not found");
                }
            } else if (line.equals("?genreWithHighestAverage")) {
                System.out.println(db.genreWithHighestAverage());
            } else if (line.equals("?mostProlificActor")) {
                System.out.println(db.actorInMostMovies().asKey());
            }else if (line.startsWith(MOVIESFORGENRE)) {
                String genre = line.substring(MOVIESFORGENRE.length());
                System.out.println(db.countMoviesPerGenre(genre));
            }
        }
    }

}
