package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16974 {
    private static int level;
    private static long eatAmount, eatenPatties;
    private static final int LIMIT = 50;
    private static final long[] patties = new long[LIMIT + 1];
    private static final long[] burgers = new long[LIMIT + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makePattiesAndBurgers();
        findEatenPatty(level);
        System.out.println(eatenPatties);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        level = Integer.parseInt(tokenizer.nextToken());
        eatAmount = Long.parseLong(tokenizer.nextToken());
    }

    private static void makePattiesAndBurgers() {
        makePatties();
        makeBurgers();
    }

    private static void makePatties() {
        patties[0] = 1;
        for (int i = 1; i <= 50; i++) {
            patties[i] = 2 * patties[i - 1] + 1;
        }
    }

    private static void makeBurgers() {
        burgers[0] = 1;
        for (int i = 1; i <= 50; i++) {
            burgers[i] = 2 * burgers[i - 1] + 3;
        }
    }

    private static void findEatenPatty(int level) {
        if (eatAmount == 0) {
            return;
        }

        if (level == 0) {
            checkAbleToEatPatty();
            return;
        }

        checkAbleToEatBurn();
        checkAbleToEatBurger(level);
        checkAbleToEatPatty();
        checkAbleToEatBurger(level);
        checkAbleToEatBurn();
    }

    private static void checkAbleToEatPatty() {
        if (eatAmount > 0) {
            eatAmount--;
            eatenPatties++;
        }
    }

    private static void checkAbleToEatBurn() {
        if (eatAmount > 0) {
            eatAmount--;
        }
    }

    private static void checkAbleToEatBurger(int level) {
        if (eatAmount > burgers[level - 1]) {
            eatAmount -= burgers[level - 1];
            eatenPatties += patties[level - 1];
        } else {
            findEatenPatty(level - 1);
        }
    }
}
