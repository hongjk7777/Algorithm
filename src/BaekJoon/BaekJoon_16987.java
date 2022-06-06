package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_16987 {
    private static int size, max = 0;
    private static int[] durability, weight;

    public static void main(String[] args) throws IOException {
        getInput();
        bfs(0);
        System.out.println(max);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        size = Integer.parseInt(tokenizer.nextToken());
        durability = new int[size];
        weight = new int[size];

        for (int i = 0; i < size; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            durability[i] = Integer.parseInt(tokenizer.nextToken());
            weight[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void bfs(int curEgg) {
        if (curEgg == size) {
            int brokenEggs = getBrokenEggs();
            updateMax(brokenEggs);
            return;
        }

        boolean hit = false;
        for (int otherEgg = 0; otherEgg < size; otherEgg++) {
            if (otherEgg == curEgg) {
                continue;
            }

            if (hitEggs(curEgg, otherEgg)) {
                hit = true;
                bfs(curEgg + 1);
                healEggs(curEgg, otherEgg);
            }

        }

        if (!hit) {
            bfs(curEgg + 1);
        }

    }

    private static int getBrokenEggs() {
        int brokenEggs = 0;

        for (int i = 0; i < size; i++) {
            if (durability[i] <= 0) {
                brokenEggs++;
            }
        }

        return brokenEggs;
    }

    private static void updateMax(int tempMax) {
        max = Math.max(max, tempMax);
    }

    private static boolean hitEggs(int curEgg, int otherEgg) {
        if (durability[curEgg] <= 0 || durability[otherEgg] <= 0) {
            return false;
        }

        durability[curEgg] -= weight[otherEgg];
        durability[otherEgg] -= weight[curEgg];
        return true;
    }

    private static void healEggs(int curEgg, int otherEgg) {
        durability[curEgg] += weight[otherEgg];
        durability[otherEgg] += weight[curEgg];
    }
}
