package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_18185 {
    private static int factoryAmount;
    private static int[] neededNoodle;

    public static void main(String[] args) throws IOException {
        getInput();
        int min = getMinCost();
        System.out.println(min);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        factoryAmount = Integer.parseInt(tokenizer.nextToken());
        neededNoodle = new int[factoryAmount];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < factoryAmount; i++) {
            neededNoodle[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static int getMinCost() {
        int cost = 0;

        cost += buy3Noodle();
        cost += buy2Noodle();
        cost += buy1Noodle();

        return cost;
    }

    private static int buy3Noodle() {
        int cost = 0;
        for (int i = 0; i < factoryAmount - 2; i++) {
            while (neededNoodle[i] > 0 && neededNoodle[i + 1] > 0 && neededNoodle[i + 2] > 0) {
                neededNoodle[i]--;
                neededNoodle[i + 1]--;
                neededNoodle[i + 2]--;
                cost += 7;
            }
        }
        return cost;
    }

    private static int buy2Noodle() {
        int cost = 0;
        for (int i = 0; i < factoryAmount - 1; i++) {
            while (neededNoodle[i] > 0 && neededNoodle[i + 1] > 0) {
                neededNoodle[i]--;
                neededNoodle[i + 1]--;
                cost += 5;
            }
        }
        return cost;
    }

    private static int buy1Noodle() {
        int cost = 0;
        for (int i = 0; i < factoryAmount; i++) {
            while (neededNoodle[i] > 0) {
                neededNoodle[i]--;
                cost += 3;
            }
        }
        return cost;
    }
}
