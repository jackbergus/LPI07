package Lez09.imdb.data_generator;

import java.util.Random;

public class TrickedDiceOverOne {
    int tricked;
    public TrickedDiceOverOne(int tricked) {
        this.tricked = tricked;
    }
    public int nextInt(int max, Random r) {
        if (r.nextDouble() <= 0.6) {
            return tricked;
        } else return r.nextInt(max);
    }
}
