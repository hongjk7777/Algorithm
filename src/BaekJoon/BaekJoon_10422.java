package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10422 {
    private static final int MOD = 1000000007;
    private static int testcaseAmount;
    private static int[] length;
    private static final long[] dp = new long[5001];

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        testcaseAmount = Integer.parseInt(tokenizer.nextToken());
        length = new int[testcaseAmount];

        for (int i = 0; i < testcaseAmount; i++) {
            tokenizer = new StringTokenizer(reader.readLine());
            length[i] = Integer.parseInt(tokenizer.nextToken());
        }
    }

    private static void makeDP() {
        dp[0] = 1;
        dp[2] = 1;

        for (int i = 4; i <= 5000; i += 2) {
            for (int j = 2; j <= i; j += 2) {
                dp[i] += dp[j - 2] * dp[i - j];
                dp[i] %= MOD;
            }
        }
    }

    private static void printAnswer() {
        for (int i = 0; i < testcaseAmount; i++) {
            System.out.println(dp[length[i]]);
        }
    }
}
