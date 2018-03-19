package Lez09.imdb.data_generator;

import java.io.*;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;

public class RealDatabase {

    private static String query_file = "query.txt";
    private static String db_file = "db.txt";
    private static String answer_file = "answer.txt";

    private static final GregorianCalendar gc = new GregorianCalendar();
    private static final Random r = new Random();
    private static String generateRandomDate() {
        int year = r.nextInt(2018-1920)+1920;
        gc.set(gc.YEAR, year);
        int dayOfYear = randBetween(1, gc.getActualMaximum(gc.DAY_OF_YEAR));
        gc.set(gc.DAY_OF_YEAR, dayOfYear);
        return String.format("%02d", gc.get(gc.DAY_OF_MONTH))+"/"+String.format("%02d", (gc.get(gc.MONTH) + 1))+"/"+String.format("%04d",gc.get(gc.YEAR));
    }

    public static int randBetween(int start, int end) {
        return start + (int)Math.round(Math.random() * (end - start));
    }

    private final static String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static String randomIdentifier(int len) {
        StringBuilder builder = new StringBuilder();
        int length = r.nextInt(len)+len;
        for(int i = 0; i < length; i++) {
            builder.append(lexicon.charAt(r.nextInt(lexicon.length())));
        }
        return builder.toString();
    }
    private static String randomIdentifier() {
        return randomIdentifier(5);
    }

    public static void writeBoth(Writer dbFile, Writer queryFile, String item) throws IOException {
        dbFile.write(item+"\n");
        queryFile.write(":add "+item+"\n");
    }

    public static void proces(Writer dbFile, Writer queryFile, Writer answer) throws IOException {
        int noActors = 5;
        int chooseProlific = r.nextInt(noActors);
        TrickedDiceOverOne prolificActor = new TrickedDiceOverOne(chooseProlific);
        String[] actorKeys = new String[noActors];
        int[] howManyFirstFilmPerActor = new int[noActors];
        HashSet<String> checkNoDuplicates = new HashSet<>();
        for (int i = 0; i<noActors; i++) {
            String name;
            String surname;
            do  {
                name = randomIdentifier();
                surname = randomIdentifier();
            } while (checkNoDuplicates.contains(name+" "+surname));
            checkNoDuplicates.add(name+" "+surname);
            String sex = r.nextBoolean() ? "female" : "male";
            writeBoth(dbFile, queryFile, "actor("+name+","+surname+","+sex+")");
            actorKeys[i] = name+" "+surname;
            howManyFirstFilmPerActor[i] = 0;
        }
        checkNoDuplicates.clear();

        int noGenre = 2;
        int chooseHighestRated = r.nextInt(noGenre);
        String[] genres = new String[noGenre];
        int[] moviesPerGenre = new int[noGenre];
        for (int i = 0; i<noGenre; i++) {
            moviesPerGenre[i] = 0;
            do {
                genres[i] = randomIdentifier();
            } while (checkNoDuplicates.contains(genres[i]));
            checkNoDuplicates.add(genres[i]);
        }
        checkNoDuplicates.clear();

        int noMovies = 10;
        String[] moviesTitle = new String[noMovies];
        int maxActor = -1;
        int bestActor = -1;
        HashMap<String,Integer> filmToGenreId = new HashMap<>();
        for (int i = 0; i<noMovies; i++) {
            String title;
            do {
                title = randomIdentifier(8);
            } while (checkNoDuplicates.contains(title));
            String date = generateRandomDate();
            int genreId = r.nextInt(noGenre);
            moviesPerGenre[genreId]++;
            String genre = genres[genreId];
            int actorId = prolificActor.nextInt(noActors, r);
            howManyFirstFilmPerActor[actorId]++;
            String actorKey = actorKeys[actorId];
            writeBoth(dbFile, queryFile, "movie("+title+","+date+","+genre+","+actorKey+")");
            moviesTitle[i] = title;
            filmToGenreId.put(title, genreId);
        }
        for (int i = 0; i<noActors; i++) {
            if (howManyFirstFilmPerActor[i] > maxActor) {
                maxActor = howManyFirstFilmPerActor[i];
                bestActor = i;
            }
        }

        checkNoDuplicates.clear();

        int noUsers = 2;
        int noScoresPerUser = 5;
        for (int i = 0; i<noUsers; i++) {
            String username;
            do {
                username = randomIdentifier(7)+r.nextInt(6);
            } while (checkNoDuplicates.contains(username));
            String email = username+"@"+(r.nextBoolean() ? "gmail.com" : "studio.unibo.it");
            writeBoth(dbFile, queryFile, "user("+username+","+email+")");
            for (int j = 0 ; j<noScoresPerUser; j++) {
                String movieName = moviesTitle[r.nextInt(noMovies)];
                int genre = filmToGenreId.get(movieName);
                int score = (genre == chooseHighestRated ? 10 : r.nextInt(10));
                writeBoth(dbFile, queryFile, "score("+username+","+movieName+","+score+")");
            }
        }
        queryFile.write("?genreWithHighestAverage\n");
        answer.write(genres[chooseHighestRated]+"\n");
        queryFile.write("?mostProlificActor\n");
        answer.write(actorKeys[bestActor]+"\n");
        for (int i = 0; i<noGenre; i++) {
            String genre = genres[i];
            queryFile.write("?noMoviesForGenre "+genre+"\n");
            answer.write(moviesPerGenre[i]+"\n");
        }
    }

    public static void main(String[] args) {
        try (Writer dbfile = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(db_file), "utf-8"))) {
            try (Writer queryFile = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(query_file), "utf-8"))) {
                try (Writer answer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(answer_file), "utf-8"))) {
                    proces(dbfile, queryFile, answer);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
