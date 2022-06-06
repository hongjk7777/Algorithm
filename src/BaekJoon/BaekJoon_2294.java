package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_2294 {
    private static final int INT_MAX = 987654321, MAX_VALUE = 10000, MAX_COIN = 100;
    private static int coinAmount, goalValue;
    private static int[] coinValue;
    private static final int[][] dp = new int[MAX_COIN + 1][MAX_VALUE + 1];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        coinAmount = Integer.parseInt(tokenizer.nextToken());
        goalValue = Integer.parseInt(tokenizer.nextToken());
        coinValue = new int[coinAmount];

        for (int i = 0; i < coinAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            coinValue[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        for (int i = 0; i <= MAX_COIN; i++) {
            for (int j = 0; j <= MAX_VALUE; j++) {
                dp[i][j] = INT_MAX;
            }
        }

        dp[0][0] = 0;

        for (int i = 1; i <= coinAmount; i++) {
            for (int tempVal = 0; tempVal <= MAX_VALUE; tempVal++) {
                dp[i][tempVal] = dp[i - 1][tempVal];
                if (tempVal - coinValue[i - 1] >= 0) {
                    dp[i][tempVal] = Math.min(dp[i][tempVal], dp[i][tempVal - coinValue[i - 1]] + 1);
                }
            }
        }
    }

    private static void printAnswer() {
        int answer = dp[coinAmount][goalValue];
        if (answer == INT_MAX) {
            System.out.println(-1);
        } else {
            System.out.println(answer);
        }
    }
}
