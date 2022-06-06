package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BaekJoon_12865 {
    private static int thingAmount, maxWeight, curMaxValue = 0;
    private static List<Thing> things = new ArrayList<>();
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        initialize();
        findMaxValue();
        System.out.println(dp[thingAmount][maxWeight]);
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        thingAmount = Integer.parseInt(tokenizer.nextToken());
        maxWeight = Integer.parseInt(tokenizer.nextToken());
        dp = new int[thingAmount + 1][maxWeight + 1];

        for (int thingNum = 0; thingNum < thingAmount; thingNum++) {
            tokenizer = new StringTokenizer(reader.readLine());
            int weight = Integer.parseInt(tokenizer.nextToken());
            int value = Integer.parseInt(tokenizer.nextToken());
            things.add(new Thing(weight, value));
        }
    }

    private static void initialize() {
        Collections.sort(things);
    }

    private static void findMaxValue() {
        for (int thingNum = 1; thingNum <= thingAmount; thingNum++) {
            for (int weight = 1; weight <= maxWeight; weight++) {
                Thing thing = things.get(thingNum - 1);
                if (weight >= thing.weight) {
                    dp[thingNum][weight] =
                            Math.max(dp[thingNum - 1][weight], dp[thingNum - 1][weight - thing.weight] + thing.value);
                } else {
                    dp[thingNum][weight] = dp[thingNum - 1][weight];
                }
            }
        }

    }

    private static class Thing implements Comparable<Thing>{
        int weight, value;
        double efficiency;

        public Thing(int weight, int value) {
            this.weight = weight;
            this.value = value;
            efficiency = (double) value / (double) weight;
        }

        @Override
        public int compareTo(Thing otherThing) {
            double efficiency = this.efficiency;
            double otherEfficiency = otherThing.efficiency;
            if (efficiency <= otherEfficiency) {
                return 1;
            } else {
                return -1;
            }

        }
    }
}
