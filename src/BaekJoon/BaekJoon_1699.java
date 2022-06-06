package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon_1699 {
    private static final int INT_MAX = 987654321;
    private static int n;
    private static int[] dp;

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
        dp = new int[n + 1];
    }

    private static void makeDP() {
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = INT_MAX;

            for (int j = 1; j < INT_MAX; j++) {
                int pow = (int) Math.pow(j, 2);
                if (i - pow < 0) {
                    break;
                }

                dp[i] = Math.min(dp[i], dp[i - pow] + 1);
            }
        }
    }

    private static void printAnswer() {
        System.out.println(dp[n]);
    }
}
