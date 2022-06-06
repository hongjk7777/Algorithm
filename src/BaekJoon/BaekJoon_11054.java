package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11054 {
    private static final int DESCENDING = 0, ASCENDING = 1;
    private static int n;
    private static int[] input;
    private static int[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        findAndPrintAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        input = new int[n + 2];
        dp = new int[n + 2][2];

        tokenizer = new StringTokenizer(reader.readLine());
        for (int i = 1; i <= n; i++) {
            input[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        makeAscendingDP();
        makeDescendingDP();
    }

    private static void makeAscendingDP() {
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                if (input[i] > input[j]) {
                    dp[i][ASCENDING] =
                            Math.max(dp[i][ASCENDING], dp[j][ASCENDING] + 1);
                }
            }
        }
    }

    private static void makeDescendingDP() {
        for (int i = n; i > 0; i--) {
            for (int j = n + 1; j > i; j--) {
                if (input[i] > input[j]) {
                    dp[i][DESCENDING] =
                            Math.max(dp[i][DESCENDING], dp[j][DESCENDING] + 1);
                }
            }
        }
    }

    private static void findAndPrintAnswer() {
        int max = getMax();
        System.out.println(max);
    }

    private static int getMax() {
        int max = 0;
        for (int i = 1; i <= n; i++) {
            int temp = dp[i][0] + dp[i][1] - 1;
            max = Math.max(max, temp);
        }
        return max;
    }
}
