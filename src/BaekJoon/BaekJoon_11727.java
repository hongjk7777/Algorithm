package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_11727 {
    private static final int MOD = 10007;
    private static int n;
    private static int[] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        initializeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        dp = new int[n + 1];
    }

    private static void initializeDP() {
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2]) % MOD;
        }
    }

    private static void printAnswer() {
        System.out.println(dp[n]);
    }
}
