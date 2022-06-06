package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_15988 {
    public static final int MOD = 1000000009, MAX = 1000000;
    private static int t;
    private static int[] input;
    private static final long[] dp = new long[MAX + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswers();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        t = Integer.parseInt(tokenizer.nextToken());
        input = new int[t];

        for (int i = 0; i < t; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        initializeDP();
        for (int i = 3; i <= MAX; i++) {
            calculateDP(i);
        }
    }

    private static void initializeDP() {
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
    }

    private static void calculateDP(int i) {
        dp[i] = (dp[i - 1] + dp[i - 2] + dp[i - 3]) % MOD;
    }

    private static void printAnswers() {
        for (int i = 0; i < t; i++) {
            printAnswer(i);
        }
    }

    private static void printAnswer(int testcaseNum) {
        int temp = input[testcaseNum];
        System.out.println(dp[temp]);
    }
}
