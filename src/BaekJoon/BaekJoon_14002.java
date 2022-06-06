package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon_14002 {
    private static final int NUM_LIMIT = 1000;
    private static int n;
    private static int[] input;
    private static ArrayList<Integer>[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        initializeDP();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        input = new int[n];
        dp = new ArrayList[n + 1][NUM_LIMIT + 1];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void initializeDP() {
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= NUM_LIMIT; j++) {
                dp[i][j] = new ArrayList<>();
            }
        }
    }

    private static void makeDP() {
        for (int i = 1; i <= n; i++) {
            int curNum = input[i - 1];
            for (int j = 1; j <= NUM_LIMIT; j++) {
                if (j - curNum >= 0) {
                    if (dp[i - 1][j].size() < dp[i - 1][curNum - 1].size() + 1) {
                        dp[i][j].addAll(dp[i - 1][curNum - 1]);
                        dp[i][j].add(i - 1);
                    } else {
                        dp[i][j].addAll(dp[i - 1][j]);
                    }
                } else {
                    dp[i][j].addAll(dp[i - 1][j]);
                }
            }
        }
    }

    private static void printAnswer() {
        StringBuilder builder = new StringBuilder();
        int size = dp[n][NUM_LIMIT].size();

        builder.append(size).append("\n");
        for (int i = 0; i < size; i++) {
            int temp = dp[n][NUM_LIMIT].get(i);
            builder.append(input[temp]).append(" ");
        }

        System.out.println(builder);
    }
}
