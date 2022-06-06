package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_10844 {
    private static int n;
    public static final int MOD = 1000000000;

    //dp[i][j]는 j로 시작하는 크기 i의 계단 수의 개수
    private static long[][] dp;

    public static void main(String[] args) throws IOException {
        getInput();
        makeDP();
        printAnswer();
    }

    private static void getInput() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer tokenizer;

        tokenizer = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(tokenizer.nextToken());
        dp = new long[n + 1][10];
    }

    private static void makeDP() {
        initializeDP();
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < 10; j++) {
                if (j > 0) {
                    dp[i][j] += dp[i - 1][j - 1];
                }
                if (j < 9) {
                    dp[i][j] += dp[i - 1][j + 1];
                }
                dp[i][j] %= MOD;
            }
        }
    }

    private static void initializeDP() {
        for (int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }
    }

    private static void printAnswer() {
        long sum = 0;
        for (int i = 1; i < 10; i++) {
            sum += dp[n][i];
        }
        long answer = sum % MOD;

        System.out.println(answer);
    }
}
